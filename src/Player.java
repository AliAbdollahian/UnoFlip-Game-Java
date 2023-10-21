package src;

import java.util.List;
// represents indiviual players currently in a game
public class Player {
    private List<Card> hand;
    private String name;
    private int score;
    public Player(String name) {
        this.hand = hand;
        this.name = name;
        score = 0;
    }
    // retrieves indivual players name
    public String getName() {
        return this.name;
    }
    // add card to an indivual players hand
    public void addCard(Card card) {
        hand.add(card);
    }
    // gets current score for a player
    public Integer getScore() {
        return this.score;
    }
    // sets score and updates for each indivual player
    public void setScore(int score) {
        this.score = score;
    }
    // retrives hand
    public List<Card> getHand() {
        return this.hand;
    }
    // calculates score of players hand
//    public int scoreofHand() {
//        int scoreHand = 0;
//        int scoreofHand;
//        for (Card card: hand) {
//            scoreofHand = scoreofHand + card.getScore();
//        }
//        return scoreofHand;
//    }
    // player chooses and plays a card
    public Card playCard(int index) {
        return (Card) this.hand.remove(index);
    }
    // player draws a card from the deck
//    public void drawCard(Card card) {
//        this.hand.add(card);
//    }

    public void clearHand() {
        hand.clear();
    }
}
