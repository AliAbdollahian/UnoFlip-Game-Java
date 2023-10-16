package src;
import java.util.ArrayList;

public class UnoGame {
    public List<Player> players;
    public Deck deck;
    public Boolean cardSide;
    public Card currentCard;

    public UnoGame(){
        cardSide = true;
    }

    public void Play(){}

    public void dealHand(){}

    public void drawCard(Player player){}

    public int calculateScore(Player){}

    public void invokeEffect(Card){}

    public void flip(){
        cardSide = !cardSide;
    }

    public void draw(Player player, int num){
    }

    public boolean isValid(Card card){}

    public boolean isHandEmpty(){}

    public boolean isDeckEmpty(){
    }

    public void newRound(){}

    public void endGame(){}

}
