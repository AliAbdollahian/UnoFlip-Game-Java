import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * The controller class for the UNO game, responsible for handling user input,
 * updating the game state, and interacting with the view.
 */
public class UnoGameController implements ActionListener {
    public UnoGame unoGame;
    public UnoView unoView;

    /**
     * Constructs a new UnoGameController.
     *
     * @param unoGame The UNO game model.
     * @param unoView The associated view for the game.
     */
    public UnoGameController(UnoGame unoGame,UnoView unoView) {
        this.unoGame = unoGame;
        this.unoView = unoView;
    }

    /**
     * Handles action events triggered by UI components.
     *
     * @param e The ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == unoView.nextPlayer){
            nextPlayerClicked();
        }else if(e.getSource() == unoView.drawCard){
            drawCardClicked();
        }else if(e.getSource() == unoView.handPanel){
            playerChoseACard(unoView.cardPath);
        }else if(e.getSource() == unoView.replay) {
            replayClicked();
        }else if(e.getSource() == unoView.redo) {
            redoClicked();
        }else if(e.getSource() == unoView.undo) {
            undoClicked();
        }else if(e.getSource() == unoView.saveGame) {
            saveGameClicked();
        }else if(e.getSource() == unoView.loadGame) {
            loadGameClicked();
        }
    }

    /**
     * Checks for a winner and takes appropriate actions if the game is won.
     */
    private void checkForWinner() {
        if (unoGame.isWinner()) {
            UnoPlayer winningPlayer = unoGame.getCurrentPlayer();
            unoGame.calculateScores(winningPlayer);
            JOptionPane.showMessageDialog(null,"    Congrats you won the first round :))))))   ");
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Do you want to exit or continue?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Exit", "Continue"},
                    "Continue"
            );
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                unoGame.resetGame();
                unoView.displaySampleCards(UnoGame.currentSide);
                unoView.frame.pack();
            }
        }
    }

    /**
     * Handles the draw card action.
     */
    public void drawCardClicked() {
        unoGame.saveCurrentState();
        UnoCard drawnCard;
        if (unoGame.deck.isEmpty()){
            unoGame.refillingDeckFromDiscard();}
        drawnCard = unoGame.deck.removeFromDeck();
        unoGame.players.get(unoGame.currentPlayerIndex).drawUnoCard(drawnCard);
        unoView.status.setText("drawn "+drawnCard.toString()+" card");
        unoView.displaySampleCards(UnoGame.getCurrentSide());
        unoView.frame.pack();
        unoView.nextPlayer.setEnabled(true);
        disableButtons();
        unoView.undo.setEnabled(true);

    }

    /**
     * Handles the next player action.
     */
    public void nextPlayerClicked() {
        unoGame.saveCurrentState();
        checkForWinner();
        unoView.drawCard.setEnabled(true);
        unoGame.nextPlayer(unoGame.currentPlayerIndex);
        setPlayerName();
        unoView.displaySampleCards(UnoGame.getCurrentSide());
        unoView.frame.pack();
        unoView.status.setText(unoGame.getCurrentPlayer().getName() + "'s turn to play!");
        unoView.nextPlayer.setEnabled(false);
        if (checkForAI()){
            handleAIPlayerTurn();
            AIHandleGui();
        }
        unoView.undo.setEnabled(true);
    }

    public void handleAIPlayerTurn(){
        disableButtons();
        UnoPlayer ai = unoGame.getCurrentPlayer();
        List<UnoCard> hand = ai.getHand();
        for (UnoCard card : hand) {
            if (unoGame.isValidPlay(unoGame.topCard,card)) {
                if (notASpecialCard(card)){
                    handleRegularCards(card);
                }
                else {
                    handleSpecialCards(card);
                }
                JOptionPane.showMessageDialog(null,"ai player number "+ai.getName()+" played a card: "+card);
                unoView.status.setText(ai.getName()+" has played press next player");
                break;
            }else {
                UnoCard drawnCard;
                drawnCard = unoGame.deck.removeFromDeck();
                ai.drawUnoCard(drawnCard);
                JOptionPane.showMessageDialog(null,"ai player number "+ai.getName()+" drawn a card");
                unoView.status.setText(ai.getName()+" has played press next player");
                break;
            }
        }
    }

    public void AIHandleGui(){
        setPlayerName();
        unoView.displaySampleCards(UnoGame.currentSide);
        unoView.frame.pack();
        disableButtons();
        unoView.nextPlayer.setEnabled(true);
    }

    private boolean checkForAI() {
        return unoGame.getCurrentPlayer().AIFlag;
    }

    /**
     * Sets the player name in the view.
     */
    public void setPlayerName() {
        unoView.playerName.setText(unoGame.players.get(unoGame.currentPlayerIndex).name);
    }

    /**
     * Extracts UnoCard object from the given card path.
     *
     * @param cardPath The path to the card image file.
     * @return The corresponding UnoCard.
     */
    public UnoCard extractUnoCard(String cardPath) {
        UnoSide currentSide = UnoGame.getCurrentSide();
        String fileName = new File(cardPath).getName();
        for (UnoCard card : unoGame.getDeck()) {
            if (card.getImageFileName(currentSide).equals(fileName)) {
                return card;
            }
        }
        for (UnoCard card : unoGame.players.get(unoGame.currentPlayerIndex).getHand()) {
            if (card.getImageFileName(currentSide).equals(fileName)) {
                return card;
            }
        }
        String errorMessage = "Error: The card with path " + cardPath + " was not found in the deck or the player's hand.";
        JOptionPane.showMessageDialog(null, errorMessage);
        return null;
    }

    /**
     * Checks if the played card is not a special card.
     *
     * @param playedCard The played UnoCard.
     * @return True if the card is not a special card, false otherwise.
     */
    public boolean notASpecialCard(UnoCard playedCard){
        if (UnoGame.currentSide == UnoSide.LIGHT) {
            return
                    playedCard.getLightValue() != UnoValue.draw_five &&
                            playedCard.getLightValue() != UnoValue.draw_one &&
                            playedCard.getLightValue() != UnoValue.wild &&
                            playedCard.getLightValue() != UnoValue.reverse &&
                            playedCard.getLightValue() != UnoValue.skip &&
                            playedCard.getLightValue() != UnoValue.wild_draw_two &&
                            playedCard.getLightValue() != UnoValue.wild_draw_color &&
                            playedCard.getLightValue() != UnoValue.flip;
        }else return
                playedCard.getDarkValue() != UnoValue.draw_five &&
                        playedCard.getDarkValue() != UnoValue.reverse &&
                        playedCard.getLightValue() != UnoValue.skip_everyone &&
                        playedCard.getDarkValue() != UnoValue.flip &&
                        playedCard.getDarkValue() != UnoValue.wild_draw_color &&
                        playedCard.getDarkValue() != UnoValue.wild;
    }

    /**
     * Handles the player choosing a card.
     *
     * @param cardPath The path to the chosen card image file.
     */
    public void playerChoseACard(String cardPath) {
        unoGame.saveCurrentState();
        UnoCard playedCard = extractUnoCard(cardPath);
        if (!notASpecialCard(playedCard)){
            handleSpecialCards(playedCard);
        }
        if (unoGame.isValidPlay(playedCard,unoGame.discardPile.get(unoGame.discardPile.size() - 1))){
            handleRegularCards(playedCard);
        }else{
            unoView.status.setText("Not a valid card to play with");
        }
        unoView.nextPlayer.setEnabled(true);
        unoView.undo.setEnabled(true);

    }

    /**
     * Handles playing regular Uno cards.
     *
     * @param playedCard The played UnoCard.
     */
    public void handleRegularCards(UnoCard playedCard){
        unoGame.players.get(unoGame.currentPlayerIndex).playedCard(playedCard);
        unoGame.discardPile.add(playedCard);
        unoView.status.setText("played: " + playedCard);
        unoView.setTopCardImage(playedCard);
        unoGame.topCard = playedCard;
        disableButtons();
    }

    /**
     * Handles playing special Uno cards.
     *
     * @param playedCard The played UnoCard.
     */
    public void handleSpecialCards(UnoCard playedCard){
        unoGame.handleSpecialCard(playedCard);
        unoGame.players.get(unoGame.currentPlayerIndex).playedCard(playedCard);
        unoGame.discardPile.add(playedCard);
        unoView.status.setText("played: " + playedCard);
        unoView.setTopCardImage(playedCard);
        unoGame.topCard = playedCard;
        disableButtons();
    }

    /**
     * Disables relevant buttons in the UI after player,
     * plays a card or draw a card.
     */
    private void disableButtons() {
        Component[] components = unoView.handPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                component.setEnabled(false);
            }
        }
        unoView.drawCard.setEnabled(false);
    }

    public void redoClicked() {
        unoGame.redo();
        setPlayerName();
        unoView.setTopCardImage(unoGame.topCard);
        unoView.status.setText(unoGame.getCurrentPlayer().getName() + " pressed redo button");
        unoView.displaySampleCards(UnoGame.getCurrentSide());
        unoView.frame.pack();
        disableButtons();
    }

    public void undoClicked() {
        unoGame.undo();
        setPlayerName();
        unoView.setTopCardImage(unoGame.topCard);
        unoView.status.setText(unoGame.getCurrentPlayer().getName() + " pressed undo button");
        unoView.displaySampleCards(UnoGame.getCurrentSide());
        unoView.frame.pack();
        unoView.redo.setEnabled(true);
        unoView.drawCard.setEnabled(true);
    }

    public void replayClicked() {
        unoGame.replay();
        setPlayerName();
        unoView.status.setText(unoGame.getCurrentPlayer().getName() + " pressed replay button");
        unoView.displaySampleCards(unoGame.getCurrentSide());
        unoView.frame.pack();
        unoView.setTopCardImage(null);
    }

    public void saveGameClicked() {
        unoGame.saveCurrentStateInVariable();
        JOptionPane.showMessageDialog(null,"State of the game has been saved successfully!!");
    }

    public void loadGameClicked() {
        unoGame.LoadState(unoGame.tempToSaveState,unoGame.undoStackTemp,unoGame.redoStackTemp);
        setPlayerName();
        unoView.setTopCardImage(unoGame.topCard);
        unoView.displaySampleCards(UnoGame.getCurrentSide());
        unoView.frame.pack();
    }
}
