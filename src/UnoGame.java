package src;
import java.util.ArrayList;

public class UnoGame {
    public List<Player> players;
    public Deck deck;
    public Side cardSide;
    public Card currentCard;
    public boolean clockwise;

    public UnoGame(){ // Antonio
        cardSide = Side.LIGHT;
        deck = new Deck();
        clockwise = true;
    }

    public void Play(){ // Ali
        dealHand();
        // place first card in discard pile
    }

    public void dealHand(){} // Antonio

    public void drawCard(Player player){} // Ali

    public int calculateScore(Player player){} // Ali

    public void invokeEffect(Card card){} // Antonio

    public void flip(){ // Antonio
        if(cardSide.equals(Side.LIGHT)){
            cardSide = Side.DARK;
        }
        else{
            cardSide = Side.LIGHT;
        }
    }

    public void draw(Player player, int num){ // Ali
    }

    public void reverse(){ // Antonio
        clockwise = !clockwise;
    }

    public boolean isValid(Card card){ // Ali
    }

    public boolean isHandEmpty(Player player){ // Antonio
    }

    public boolean isDeckEmpty(){ // Ali
    }

    public void newRound(){} // Ali

    public void endGame(){} // Antonio

    public static void main(String[] args) {
    }

}
