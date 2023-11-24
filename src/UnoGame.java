import javax.swing.*;
import java.util.*;

/**
 * Represents the Uno game, managing the deck, players, and game state.
 */
public class UnoGame {
    
    public UnoDeck deck = new UnoDeck();
    public List<UnoCard> discardPile = new ArrayList();
    public List<UnoPlayer> players = this.initializePlayers();
    public int currentPlayerIndex = 0;
    public UnoColor chosenColor = null;
    public static UnoSide currentSide;
    int numOfPlayer;
    public boolean clockwise;

    /**
     * Constructor for the UnoGame class.
     * Initializes the game by setting up the deck, players, and starting card.
     */
    public UnoGame() {
        try {
            UnoCard startingCard;
            clockwise = true;
            do {
                startingCard = this.deck.removeFromDeck();
                if (startingCard.getLightValue() != UnoValue.wild && startingCard.getLightValue() != UnoValue.wild_draw_two) {
                    break;
                }
                this.deck.addToBottom(startingCard);
            } while (true);

            this.discardPile.add(startingCard);
        } catch (NoSuchElementException e) {
            JOptionPane.showMessageDialog(null,"Error: No non-wild cards in the deck.");
        }
    }

    /**
     * Checks if any player has an empty hand, indicating a winner.
     *
     * @return true if a player has an empty hand, false otherwise.
     */
    public boolean isWinner() {
        for (UnoPlayer player : players) {
            if (player.getHand().isEmpty()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Initializes the players by taking the number of players and their names from user input.
     *
     * @return A list of initialized Uno players.
     */
    public List<UnoPlayer> initializePlayers() {
        boolean validInput = false;
        do {
            int playerCount = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players (2-4): "));

            if (playerCount > 1 && playerCount < 5) {
                numOfPlayer = playerCount;
                List<UnoPlayer> players = new ArrayList<>();

                for (int i = 0; i < playerCount; ++i) {
                    String playerName = JOptionPane.showInputDialog("Enter name for Player " + (i + 1) + ": ");
                    UnoPlayer player = new UnoPlayer(playerName);

                    for (int j = 0; j < 7; ++j) {
                        player.drawUnoCard(this.deck.removeFromDeck());
                    }

                    players.add(player);
                }
                return players;
            } else {
                JOptionPane.showMessageDialog(null, "Not a valid number of players!");
            }
        } while (!validInput);
        return null;
    }


    /**
     * Checks if a chosen card can be played on the top card of the discard pile.
     *
     * @param chosenCard The Uno card chosen to play.
     * @param topCard    The top card of the discard pile.
     * @return true if the play is valid, false otherwise.
     */
    public boolean isValidPlay(UnoCard chosenCard, UnoCard topCard) {
        if (currentSide == UnoSide.LIGHT) {
            if (chosenCard.getLightValue() != UnoValue.wild && chosenCard.getLightValue() != UnoValue.wild_draw_two) {
                if (topCard.getLightValue() != UnoValue.wild && topCard.getLightValue() != UnoValue.wild_draw_two) {
                    return chosenCard.getLightColor() == topCard.getLightColor() || chosenCard.getLightValue() == topCard.getLightValue();
                } else {
                    return chosenCard.getLightColor() == this.chosenColor;
                }
            } else {
                return true;
            }
        } else if (chosenCard.getDarkValue() != UnoValue.wild && chosenCard.getDarkValue() != UnoValue.wild_draw_two) {
            if (topCard.getDarkValue() != UnoValue.wild && topCard.getDarkValue() != UnoValue.wild_draw_two) {
                return chosenCard.getDarkColor() == topCard.getDarkColor() || chosenCard.getDarkValue() == topCard.getDarkValue();
            } else {
                return chosenCard.getDarkColor() == this.chosenColor;
            }
        } else {
            return true;
        }
    }


    /**
     * Calculates scores for all players, excluding the winning player.
     *
     * @param winningPlayer The player who won the round.
     */
    public void calculateScores(UnoPlayer winningPlayer) {
        int totalScore = 0;

        for (UnoPlayer player : this.players) {
            if (player != winningPlayer) {
                totalScore += calculatePlayerScore(player);
            }
        }
        displayWinnerScore(winningPlayer, totalScore);
    }

    /**
     * Calculates the score for a given Uno player based on the values of the cards in their hand.
     *
     * @param player The Uno player for whom the score is calculated.
     * @return The calculated score for the player.
     */
    private int calculatePlayerScore(UnoPlayer player) {
        int playerScore = 0;

        for (UnoCard card : player.getHand()) {
            UnoValue value = getCurrentSide() == UnoSide.LIGHT ? card.getLightValue() : card.getDarkValue();
            playerScore += calculateScoreForCard(value);
        }
        return playerScore;
    }

    /**
     * Calculates the score contribution of a specific Uno card based on its value.
     *
     * @param value The UnoValue of the card.
     * @return The calculated score for the card.
     */
    private int calculateScoreForCard(UnoValue value) {
        switch (value) {
            case one:
            case two:
            case three:
            case four:
            case five:
            case six:
            case seven:
            case eight:
            case nine:
                return value.ordinal() + 1;
            case draw_one:
                return 10;
            case draw_five:
            case reverse:
            case skip:
            case flip:
                return 20;
            case skip_everyone:
                return 30;
            case wild:
                return 40;
            case wild_draw_two:
                return 50;
            case wild_draw_color:
                return 60;
            default:
                return 0;
        }
    }

    /**
     * Displays a message dialog showing the score of the winning player for the current round.
     *
     * @param winningPlayer The Uno player who won the round.
     * @param totalScore    The total score calculated for all players except the winner.
     */
    private void displayWinnerScore(UnoPlayer winningPlayer, int totalScore) {
        String winner = winningPlayer.getName();
        JOptionPane.showMessageDialog(null, winner + " scored " + totalScore + " points this round.");
    }

    /**
     * Toggles the current side of the Uno game (LIGHT to DARK or vice versa).
     */
    public void toggleSide() {
        if (currentSide == UnoSide.LIGHT) {
            currentSide = UnoSide.DARK;
        } else {
            currentSide = UnoSide.LIGHT;
        }
    }


    /**
     * Handles the special effect of a specific Uno card.
     *
     * @param card The Uno card with a special effect.
     */
    public void handleSpecialCard(UnoCard card) {
        UnoValue currentValue = currentSide == UnoSide.LIGHT ? card.getLightValue() : card.getDarkValue();

        switch (currentValue) {
            case draw_one:
                handleDrawOne(card);
                break;
            case draw_five:
                handleDrawFive(card);
                break;
            case reverse:
                handleReverse(card);
                break;
            case skip:
                handleSkip(card);
                break;
            case flip:
                handleFlip(card);
                break;
            case skip_everyone:
                break;
            case wild:
                handleWild(card);
                break;
            case wild_draw_two:
                handleWildDrawTwo(card);
                break;
            default:
                break;
        }
    }

    /**
     * Handles the special effect of a Draw One Uno card.
     *
     * @param card The Draw One Uno card.
     */
    private void handleDrawOne(UnoCard card) {
        players.get(currentPlayerIndex).playedCard(card);
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        UnoPlayer targetForDrawOne = this.players.get(this.currentPlayerIndex);
        UnoCard drawnCard = this.deck.removeFromDeck();
        targetForDrawOne.drawUnoCard(drawnCard);
        JOptionPane.showMessageDialog(null,targetForDrawOne.getName() + " has to draw one card due to Draw One: " + drawnCard);
    }


    /**
     * Handles the special effect of a Draw Five Uno card.
     *
     * @param card The Draw Five Uno card.
     */
    private void handleDrawFive(UnoCard card) {
        players.get(currentPlayerIndex).playedCard(card);
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        UnoPlayer targetForDrawFive = this.players.get(this.currentPlayerIndex);
        StringBuilder drawnCards = new StringBuilder();

        for (int i = 0; i < 5; ++i) {
            UnoCard drawnCard = this.deck.removeFromDeck();
            targetForDrawFive.drawUnoCard(drawnCard);
            drawnCards.append(drawnCard).append(i < 4 ? ", " : "");
        }

        JOptionPane.showMessageDialog(null,targetForDrawFive.getName() + " has to draw five cards due to Draw Five: " + drawnCards);
    }

    /**
     * Handles the special effect of a Reverse Uno card by reversing the order of players.
     */
    private void handleReverse(UnoCard card) {
        players.get(currentPlayerIndex).playedCard(card);
        Collections.reverse(this.players);
        this.currentPlayerIndex = this.players.size() - 1 - this.currentPlayerIndex;
    }

    /**
     * Handles the special effect of a Skip Uno card by skipping the next player's turn.
     */
    private void handleSkip(UnoCard card) {
        players.get(currentPlayerIndex).playedCard(card);
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
    }

    /**
     * Handles the special effect of a Flip Uno card by toggling the current side of the game (LIGHT to DARK or vice versa).
     */
    private void handleFlip(UnoCard card) {
        players.get(currentPlayerIndex).playedCard(card);
        toggleSide();
    }

    /**
     * Handles the special effect of a Wild Uno card by allowing the player to choose a color.
     */
    private void handleWild(UnoCard card) {
        players.get(currentPlayerIndex).playedCard(card);
        chooseColorForWild();
    }

    /**
     * Handles the special effect of a Wild Draw Two Uno card.
     * Draws two cards for the next player and allows the player to choose a color.
     */
    private void handleWildDrawTwo(UnoCard card) {
        players.get(currentPlayerIndex).playedCard(card);
        chooseColorForWild();
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.size();
        UnoPlayer nextPlayer = this.players.get(this.currentPlayerIndex);
        UnoCard firstDrawnCard = this.deck.removeFromDeck();
        UnoCard secondDrawnCard = this.deck.removeFromDeck();
        nextPlayer.drawUnoCard(firstDrawnCard);
        nextPlayer.drawUnoCard(secondDrawnCard);
        JOptionPane.showMessageDialog(null,nextPlayer.getName() + " has to draw two cards due to Wild Draw Two: " + firstDrawnCard + ", " + secondDrawnCard);
    }

    /**
     * Allows the player to choose a color for a Wild Uno card.
     * Displays a dialog until a valid color is chosen (RED, YELLOW, GREEN, BLUE).
     */
    public void chooseColorForWild() {
        while(true) {
            try {
                String chosenColorStr = JOptionPane.showInputDialog("Choose a color from these colors -> (red , yellow , green , blue): ");
                if (Arrays.asList("red", "yellow", "green", "blue").contains(chosenColorStr)) {
                    this.chosenColor = UnoColor.valueOf(chosenColorStr);
                    JOptionPane.showMessageDialog(null,"Color "+ chosenColorStr +" was chosen");
                    return;
                }

                JOptionPane.showMessageDialog(null,"Invalid color. Choose again.");
            } catch (Exception var3) {
                JOptionPane.showMessageDialog(null,"Error in choosing color. Try again.");
            }
        }
    }

    /**
     * Refills the Uno deck from the discard pile.
     */
     public void refillingDeckFromDiscard() {
        UnoCard topCard = (UnoCard)this.discardPile.remove(this.discardPile.size() - 1);
        this.deck.fillDiscardedPile(this.discardPile);
        this.discardPile.clear();
        this.discardPile.add(topCard);
    }

    /**
     * Gets the current side (LIGHT or DARK) of the Uno game.
     *
     * @return The current side of the game.
     */
    public static UnoSide getCurrentSide() {
        return currentSide;
    }
    static {
        currentSide = UnoSide.LIGHT;
    }


    /**
     * Gets the entire deck of Uno cards.
     *
     * @return A list containing all Uno cards in the deck.
     */
    public List<UnoCard> getDeck() {
        UnoDeck unoDeck = new UnoDeck();
        return unoDeck.getAllCards();
    }

    /**
     * Moves to the next player in the game based on the current direction (clockwise or counterclockwise).
     *
     * @param currentPlayerIndex The index of the current player.
     */
    public void nextPlayer(int currentPlayerIndex) {
        if (clockwise) {
            this.currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            this.currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }

    /**
     * Gets the Uno player who is currently taking their turn.
     *
     * @return The current Uno player.
     */
    public UnoPlayer getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }


    /**
     * Resets the Uno game by reinitializing the deck, players, and game state.
     */
    public void resetGame() {
        // Reset deck
        this.deck = new UnoDeck();

        // Clear discard pile
        this.discardPile.clear();

        // Reset players
        this.players = initializePlayers();

        // Reset current player index
        this.currentPlayerIndex = 0;

        // Reset chosen color
        this.chosenColor = null;

        // Reset current side
        currentSide = UnoSide.LIGHT;

        // Reset clockwise direction
        clockwise = true;

        // Deal initial cards
        UnoCard startingCard;
        for(startingCard = this.deck.removeFromDeck(); startingCard.getLightValue() == UnoValue.wild || startingCard.getLightValue() == UnoValue.wild_draw_two; startingCard = this.deck.removeFromDeck()) {
            this.deck.addToBottom(startingCard);
        }
        this.discardPile.add(startingCard);
        System.out.println("Starting card: " + this.discardPile.get(0));
    }
}
