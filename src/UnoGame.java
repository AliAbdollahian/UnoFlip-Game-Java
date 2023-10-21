package src;
import java.util.ArrayList;
import java.util.List;

public class UnoGame {
    public List<Player> players;
    public Deck deck;
    public Side cardSide;
    public Card currentCard;
    public boolean clockwise;

    public UnoGame() { // Antonio
        cardSide = Side.LIGHT;
        deck = new Deck();
        deck.UNODeck();
        clockwise = true;
    }

    public void Play() { // Ali
        dealHand();
        currentCard = deck.removeFromDeck();
        // place first card in discard pile
    }

    public void dealHand() {
    } // Antonio

    public void drawCard(Player player) {
        Card drawnCard = deck.removeFromDeck();
        player.addToPile(drawnCard);
    } // Ali

    public void calculateScore(Player winnerOfRound) {
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
        winnerOfRound.score = winnerOfRound.score +  totalScore;
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

    public void endGame(){} // Antonio

}
