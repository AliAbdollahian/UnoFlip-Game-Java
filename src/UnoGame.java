package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The UnoGame class represents the core logic of a UNO card game.
 */
public class UnoGame {
    public List<Player> players;
    public Deck deck;
    public Side cardSide;
    public Card currentCard;
    public boolean clockwise;
    private int currentPlayerIndex;

    /**
     * Constructs a new UnoGame with the specified number of players.
     *
     * @param numPlayers The number of players in the game.
     */
    public UnoGame(int numPlayers) {
        players = new ArrayList<>();
        deck = new Deck();
        deck.getDeckSize();
        deck.shuffleDeck();
        clockwise = true;
        cardSide = Side.LIGHT;

        for (int i = 1; i <= numPlayers; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter name for Player " + i + ": ");
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
        }

        currentCard = deck.removeFromDeck();
    }

    /**
     * Starts the UNO game and manages game rounds until a player wins.
     */
    public void play() { // Ali
        boolean roundWon = false;
        int currentPlayerIndex = 0;
        this.dealHand();

        while (!roundWon) {
            Player currentPlayer = players.get(currentPlayerIndex);

            System.out.println("Current side: " + cardSide);
            System.out.println(currentPlayer.getName() + "'s Turn.");
            System.out.println("Your cards:");
            displayPlayerHand(currentPlayer);
            System.out.println("Top card: " + currentCard);

            boolean isValidChoice = false; // Flag to track if the choice is valid

            do {
                System.out.print("Enter card index to play or 0 to draw a card: ");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                if (choice == 0) {
                    Card drawnCard = deck.removeFromDeck();
                    currentPlayer.addCard(drawnCard);
                    System.out.println("Drew a card: " + drawnCard);
                    isValidChoice = true; // Valid choice, exit the loop.
                } else if (choice >= 1 && choice <= currentPlayer.getHand().size() && isValid(currentPlayer.getHand().get(choice-1))) {

                        if (currentPlayer.getHand().get(choice-1).getLightNum() == Value.DRAW_1) {
                            draw(players.get((currentPlayerIndex + 1) % players.size()), 1);
                            skipNextPlayer();
                        } else if (currentPlayer.getHand().get(choice-1).getLightNum() == Value.SKIP) {
                            skipNextPlayer();
                        } else if (currentPlayer.getHand().get(choice-1).getLightNum() == Value.WILD) {

                            handleWildCardColorSelection(currentPlayer.getHand().get(choice-1));
                            currentCard.setColor(currentPlayer.getHand().get(choice-1).getLightBorder());
                        }
                    Card playedCard = currentPlayer.playCard(choice - 1);
                        currentCard = playedCard;
                        System.out.println("Played: " + playedCard);
                        isValidChoice = true; // Valid choice, exit the loop.
                } else {
                    System.out.println("Invalid choice. Please enter 0 to draw a card or a valid card index.");
                }
            } while (!isValidChoice);

            if (isHandEmpty(currentPlayer)) {
                roundWon = true;
                System.out.println(currentPlayer.getName() + " wins!");
            }

            if (currentCard.getLightNum() != Value.SKIP) {
                // Advance to the next player, but only if no "Skip" card was played
                if (!clockwise) {
                    currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
                } else {
                    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                }
            }
        }
    }


    /**
     * Handles the color selection for a Wild card.
     *
     * @param wildCard The played Wild card.
     */
    private void handleWildCardColorSelection(Card wildCard) {
        System.out.println("Select a color for the Wild card: ");
        System.out.println("1. Red");
        System.out.println("2. Blue");
        System.out.println("3. Green");
        System.out.println("4. Yellow");

        Scanner scanner = new Scanner(System.in);
        int colorChoice = scanner.nextInt();

        switch (colorChoice) {
            case 1:
                wildCard.setColor(Color.RED);
                break;
            case 2:
                wildCard.setColor(Color.BLUE);
                break;
            case 3:
                wildCard.setColor(Color.green);
                break;
            case 4:
                wildCard.setColor(Color.yellow);
                break;
            default:
                System.out.println("Invalid color choice. try again");
                handleWildCardColorSelection(wildCard);
        }
    }

    /**
     * Displays the cards in a player's hand, including their position and the card itself.
     *
     * @param player The player whose hand will be displayed.
     */
    private void displayPlayerHand(Player player) {
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
    }


    /**
     * Deals an initial hand of 7 cards to each player.
     */
    public void dealHand() {
        for(Player p : players){
            for(int i = 0; i < 7; i++) {
                p.addCard(deck.removeFromDeck());
            }
        }
    } // Antonio


    /**
     * Draws a card from the deck and adds it to the player's hand.
     *
     * @param player The player who will draw the card.
     */
    public void drawCard(Player player) {
        Card drawnCard = deck.removeFromDeck();
        player.addCard(drawnCard);
    } // Ali



    /**
     * Calculates the score for the winner of the round and updates their total score.
     *
     * @param winnerOfRound The player who won the round.
     * @return The total score for the winner in the current round.
     */
    public int calculateScore(Player winnerOfRound) {
        int totalScore = 0;
        for (Player player : players) {
            if (player != winnerOfRound)
                for (Card card : player.getHand()) {
                    Value value = card.getLightNum();

                    switch (value) {
                        case ONE:
                        case TWO:
                        case THREE:
                        case FOUR:
                        case FIVE:
                        case SIX:
                        case SEVEN:
                        case EIGHT:
                        case NINE:
                            totalScore += value.ordinal() + 1;
                            break;


                        case REVERSE:
                        case SKIP:
                        case FLIP:
                            totalScore += 20;
                            break;
                        case SKIP_EVERYONE:
                            totalScore += 30;
                            break;
                        case WILD:
                            totalScore += 40;
                            break;

                        case WILD_DRAW_COLOR:
                            totalScore += 60;
                            break;
                    } // Ali
                }
        }
        System.out.println(winnerOfRound.getName() + " scored " + totalScore + " points this round.");
        winnerOfRound.setScore(winnerOfRound.getScore() + totalScore);
        return totalScore;
    }



    public void invokeEffect(Card card){
    } // Antonio



    /**
     * Flips the card side from LIGHT to DARK or vice versa.
     */
    public void flip(){ // Antonio
        if(cardSide.equals(Side.LIGHT)){
            cardSide = Side.DARK;
        }
        else{
            cardSide = Side.LIGHT;
        }
    }


    /**
     * Draws a specified number of cards from the deck and adds them to the player's hand.
     *
     * @param player The player who will draw the cards.
     * @param num The number of cards to draw.
     */
    public void draw(Player player, int num) {
        for (int i = 0; i < num; i++) {
            Card drawnCard = deck.removeFromDeck();
            player.addCard(drawnCard);
            System.out.println(player.getName() + " drew a card: " + drawnCard);
        }
    }//ALi


    /**
     * Reverses the game's direction from clockwise to counterclockwise or vice versa.
     */
    public void reverse(){ // Antonio
        clockwise = !clockwise;
    }



    /**
     * Checks if a played card is valid according to UNO rules.
     *
     * @param card The card to check for validity.
     * @return true if the card is valid, false otherwise.
     */
    public boolean isValid(Card card) {
        if (cardSide == Side.LIGHT) {
            if (card.getLightNum() != Value.WILD && card.getLightNum() != Value.WILD_DRAW_2) {
                if (currentCard.getLightNum() != Value.WILD && currentCard.getLightNum() != Value.WILD_DRAW_2) {
                    return card.getLightBorder() == currentCard.getLightBorder() || card.getLightNum() == currentCard.getLightNum();
                }
            } else {
                return true;
            }
        }
        return false;
    }




    /**
     * Checks if the specified player's hand is empty.
     *
     * @param player The player whose hand is being checked.
     * @return true if the player's hand is empty, false otherwise.
     */
    public boolean isHandEmpty(Player player){// Antonio
        return player.getHand().isEmpty();
    }




    /**
     * Checks if the game deck is empty.
     *
     * @return true if the game deck is empty, false otherwise.
     */
    public boolean isDeckEmpty(){ // Ali
        return deck.isEmpty();
    }



    /**
     * Starts a new round of the game by clearing hands, shuffling the deck, and dealing new hands.
     */
    public void newRound(){
        for (Player player : players) {
            player.clearHand();
        }
        deck.shuffleDeck();
        dealHand();

        currentCard = deck.removeFromDeck();
        cardSide = Side.LIGHT;
        clockwise = true;
    } // Ali

    /**
     * Handles special actions associated with a given card, such as reversing the game direction,
     * skipping the next player's turn, or implementing color selection logic for a wild card.
     *
     * @param card The card for which special actions need to be handled.
     */
    private void handleSpecialCardActions(Card card) {
        switch (card.getLightNum()) {
            case REVERSE:
                reverse();
                break;
            case SKIP:
                skipNextPlayer();
                break;
            case WILD:
                // Implement color selection logic
                break;
            default:
                // Handle other special cards if needed
        }
    }


    /**
     * Skips to the next player in the game, taking into account the game's direction (clockwise or counterclockwise).
     */
    private void skipNextPlayer() {
        if (clockwise) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }


    /**
     * Ends the UNO game and calculates the final scores for all players.
     */
    public void endGame(){
        System.out.println("Game over! Final scores:");

        for (Player player : players) {
            int score = calculateScore(player);
            System.out.println(player.getName() + ": " + score);
            player.setScore(score);
        }
    } // Antonio



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of players: ");
        int numPlayers = sc.nextInt();
        UnoGame unoGame = new UnoGame(numPlayers);
        unoGame.play();
    }
}

