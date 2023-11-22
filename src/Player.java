/**
 * This class represents individual players currently in a game.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Constructor for Player class
 */
public class Player {
    private List<Card> hand;
    private int id;
    private int score;
    public Player(int id) {
        this.hand = new ArrayList<>();
        this.id = id;
        score = 0;
    }

    /**
     * retrieves indivual players name
     * @return String name
     */
    public int getID() {
        return this.id;
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
     * @return card played
     */
    public Card removeCard(Card card) {
        this.hand.remove(card);
        return card;
    }

    /**
     * empties cards in hand
     */
    public void clearHand() {
        hand.clear();
    }
}
