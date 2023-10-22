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

            if (!clockwise) {
                currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
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
    public void draw(Player player, int num){
        for (int i = 0; i < num; i++) {
            Card drawnCard = deck.removeFromDeck();
            player.addCard(drawnCard);
        }
    }//Ali

    public void reverse(){ // Antonio
        clockwise = !clockwise;
    }

    /**
     * Checks if a played card is valid according to UNO rules.
     *
     * @param card The card to check for validity.
     * @return true if the card is valid, false otherwise.
     */
    public boolean isValid(Card card){ // Ali
        return card.getLightNum() == currentCard.getLightNum() || currentCard.getLightBorder() == card.getLightBorder();
    }

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
        // Clear the hands of all players
        for (Player player : players) {
            player.clearHand(); // implement a method to clear a player's hand
        }

        // Shuffle the deck and deal new hands to players
        deck.shuffleDeck(); // Implement a shuffle method in your Deck class
        dealHand();

        // Reset any round-specific variables or settings
        currentCard = deck.removeFromDeck();
        cardSide = Side.LIGHT;
        clockwise = true;

    } // Ali

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

