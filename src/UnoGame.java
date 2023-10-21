package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UnoGame {
    public List<Player> players;
    public Deck deck;
    public Side cardSide;
    public Card currentCard;
    public boolean clockwise;

//    public UnoGame() { // Antonio
//        cardSide = Side.LIGHT;
//        deck = new Deck();
//        deck.UNODeck();
//        clockwise = true;
//
//    }
public UnoGame(int numPlayers) {
    players = new ArrayList<>();
    deck = new Deck();
    deck.shuffleDeck();
    clockwise = true;

    for (int i = 1; i <= numPlayers; i++) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name for Player " + i + ": ");
        String playerName = scanner.nextLine();
        players.add(new Player(playerName));
    }

    currentCard = deck.removeFromDeck();
}

    public void Play() { // Ali
        boolean roundWon = false;
        int currentPlayerIndex = 0;

        while (!roundWon) {
            Player currentPlayer = players.get(currentPlayerIndex);

            System.out.println("Current side: " + currentCard.getLightBorder());
            System.out.println(currentPlayer.getName() + "'s Turn.");
            System.out.println("Your cards:");
            displayPlayerHand(currentPlayer);
            System.out.println("Top card: " + currentCard);

            System.out.print("Enter card index to play or 0 to draw a card: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 0) {
                Card drawnCard = deck.removeFromDeck();
                currentPlayer.addCard(drawnCard);
                System.out.println("Drew a card: " + drawnCard);
            } else {
                Card playedCard = currentPlayer.playCard(choice - 1);

                if (playedCard != null && isValid(playedCard)) {
                    currentCard = playedCard;
                    System.out.println("Played: " + playedCard);
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            if (currentPlayer.getHand().isEmpty()) {
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
    private void displayPlayerHand(Player player) {
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
    }

    public void dealHand() {
    } // Antonio

    public void drawCard(Player player) {
        Card drawnCard = deck.removeFromDeck();
        player.drawCard(drawnCard);
    } // Ali

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
    public void invokeEffect(Card card){} // Antonio

    public void flip(){ // Antonio
        if(cardSide.equals(Side.LIGHT)){
            cardSide = Side.DARK;
        }
        else{
            cardSide = Side.LIGHT;
        }
    }

    public void draw(Player player, int num){
        for (int i = 0; i < num; i++) {
            Card drawnCard = deck.removeFromDeck();
            player.addCard(drawnCard);
        }
    }//Ali

    public void reverse(){ // Antonio
        clockwise = !clockwise;
    }

    public boolean isValid(Card card){ // Ali
        return card.getLightNum() == currentCard.getLightNum() || currentCard.getLightBorder() == card.getLightBorder();
    }

    public boolean isHandEmpty(Player player){ // Antonio
    }

    public boolean isDeckEmpty(){ // Ali
        return deck.isEmpty();
    }

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

    public void endGame(){
        System.out.println("Game over! Final scores:");

        for (Player player : players) {
            int score = calculateScore(player);
            System.out.println(player.getName() + ": " + score);
            player.setScore(score);
        }
    } // Antonio

    public class MainClass {
        public static void main(String[] args) {

        }
    }
}

