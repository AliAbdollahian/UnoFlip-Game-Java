/**
* This class represents individual players currently in a game.
*/
package src;

import java.util.ArrayList;
import java.util.List;

/**
* Constructor for Player class
*/
public class Player {
    private List<Card> hand;
    private String name;
    private int score;
    public Player(String name) {
        this.hand = new ArrayList<>();
        this.name = name;
        score = 0;
    }

    /**
    * retrieves indivual players name
    * @return String name
    */
    public String getName() {
        return this.name;
    }

    /**
    * add card to an indivual players hand
    */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
    * gets current score for a player
    * @return Integer score
    */
    public Integer getScore() {
        return this.score;
    }

     /**
    * sets score and updates for each indivual player
    */
    public void setScore(int score) {
        this.score = score;
    }

    /**
    * retrives cards in hand
    * @return List<Card> in hand
    */
    public List<Card> getHand() {
        return this.hand;
    }
    
    /**
    * player chooses and plays a card
    * @param index of the played card
    * @return card played
    */
    public Card playCard(int index) {
        return (Card) this.hand.remove(index);
    }

     /**
    * empties cards in hand
    */
    public void clearHand() {
        hand.clear();
    }
}
