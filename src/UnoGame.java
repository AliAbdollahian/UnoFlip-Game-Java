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
        cardSide = Side.lightSide;

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
        boolean skipNext = false;

        while (!roundWon) {
            Player currentPlayer = players.get(currentPlayerIndex);

            System.out.println("Current side: " + cardSide);
            System.out.println(currentPlayer.getName() + "'s Turn.");
            System.out.println("Your cards:");
            displayPlayerHand(currentPlayer);
            System.out.println("Top card: " + currentCard);

            boolean isValidChoice = false;

            do {
                System.out.print("Enter card index to play or 0 to draw a card: ");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                if (choice == 0) {
                    Card drawnCard = deck.removeFromDeck();
                    currentPlayer.addCard(drawnCard);
                    System.out.println("Drew a card: " + drawnCard);
                    isValidChoice = true;
                } else if (choice >= 1 && choice <= currentPlayer.getHand().size() && isValid(currentPlayer.getHand().get(choice - 1))) {
                    Card playedCard = currentPlayer.playCard(choice - 1);
                    currentCard = playedCard;
                    System.out.println("Played: " + playedCard);
                    isValidChoice = true;

                    if (playedCard.getLightNum() == Value.DRAW_1) {
                        // Draw 1 logic
                        draw(players.get((currentPlayerIndex + 1) % players.size()), 1);
                        skipNext = true;
                    } else if (playedCard.getLightNum() == Value.SKIP) {
                        // Skip logic
                        skipNext = true;
                    } else{
                        invokeEffect(playedCard);
                    }
                } else {
                    System.out.println("Invalid choice. Please enter 0 to draw a card or a valid card index.");
                }
            } while (!isValidChoice);

            if (isHandEmpty(currentPlayer)) {
                roundWon = true;
                System.out.println(currentPlayer.getName() + " wins!");

                for (Player player : players) {
                    System.out.println("The scores are: ");
                    int score = calculateScore(player);
                    System.out.println(player.getName() + ": " + score);
                    player.setScore(score);
                    if(player.getScore() >= 500){
                        System.out.println("Game over! The winner is " + player.getName());
                        System.exit(0);
                    }
                }

            }

            // Check if the next player should be skipped
            if (skipNext) {
                currentPlayerIndex = (currentPlayerIndex + 2) % players.size();
                skipNext = false; // Reset the skip flag
            } else {
                // Advance to the next player
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
        Card colorCard;
        switch (colorChoice) {
            case 1:
                colorCard = new Card(Color.red, null);
                currentCard = colorCard;
                break;
            case 2:
                colorCard = new Card(Color.blue, null);
                currentCard = colorCard;
                break;
            case 3:
                colorCard = new Card(Color.green, null);
                currentCard = colorCard;
                break;
            case 4:
                colorCard = new Card(Color.yellow, null);
                currentCard = colorCard;
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


    /**
    * invokes other effects that do not deal with skipping a player's turn. 
    *
    * @param card The card that has its effect being invoked.
    */
    public void invokeEffect(Card card){
        switch (card.getLightNum()) {
            case REVERSE:
                reverse();
                break;
            case WILD:
                wild(card);
                break;
            default:
                // Handle other special cards if needed
        }
}// Antonio

public void wild(Card card) {
    handleWildCardColorSelection(card);
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
    }//Ali


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
        if (cardSide == Side.lightSide) {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of players: ");
        int numPlayers = sc.nextInt();
        UnoGame unoGame = new UnoGame(numPlayers);
        unoGame.play();
    }
}
