import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The UnoPlayer class represents a player in the Uno card game.
 */
public class UnoPlayer {

    public String name;
    public boolean AIFlag = false;
    public List<UnoCard> hand;

    /**
     * Constructs an UnoPlayer with the given name and initializes an empty hand.
     *
     * @param name The name of the player.
     */
    public UnoPlayer(String name){
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public UnoPlayer(int AiID) {
        this.name = String.valueOf(AiID);
        this.hand = new ArrayList<>();
    }


    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the player's hand, which holds Uno cards.
     *
     * @return The player's hand.
     */
    public List<UnoCard> getHand() {
        return hand;
    }

    /**
     * Draws an Uno card and adds it to the player's hand.
     *
     * @param unocard The Uno card to be drawn.
     */
    public void drawUnoCard(UnoCard unocard){
        hand.add(unocard);
    }

    /**
     * Plays a card from the player's hand at the specified index.
     *
     * @param index The index of the card to be played.
     * @return The Uno card that was played.
     */
    public UnoCard playedACard(int index){
        return hand.remove(index);
    }

    /**
     * Returns a string representation of the player's hand, including the index and details of each card.
     *
     * @return A string representation of the player's hand.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        Iterator I = this.hand.iterator();
        while (I.hasNext()){
            UnoCard unoCard = (UnoCard)I.next();
            stringBuilder.append(i++).append(". ").append(unoCard).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Removes the specified Uno card from the player's hand after it has been played.
     *
     * @param unoCard The Uno card to be removed from the hand.
     */
    public void playedCard(UnoCard unoCard) {
        int indexToRemove = -1;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).equals(unoCard)) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1) {
            hand.remove(indexToRemove);
        }
    }
}
