import javax.json.*;
import java.util.*;

/**
 * Represents the Uno deck, which consists of Uno cards.
 */
public class UnoDeck {
    private List<UnoCard> deck;

    /**
     * Constructor for the UnoDeck class.
     * Initializes the deck and populates it with Uno cards.
     */
    public UnoDeck() {
        this.deck = new ArrayList<>(112);
        this.UNODeck();
    }

    public UnoDeck(UnoDeck originalDeck) {
        this.deck = new ArrayList<>(originalDeck.deck.size());

        for (UnoCard originalCard : originalDeck.deck) {
            UnoCard copiedCard = new UnoCard(originalCard);
            this.deck.add(copiedCard);
        }
    }


    /**
     * Adds a Uno card to the bottom of the deck.
     *
     * @param unoCard The Uno card to be added.
     */
    public void addToBottom(UnoCard unoCard){
        this.deck.add(unoCard);
    }

    /**
     *Returns true if the deck is empty
     * @return boolean true
     */
    public boolean isEmpty(){
        return deck.isEmpty();
    }

    /**
     * Shuffling the deck by importing the Collections library
     */
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    /**
     * Initializes the Uno deck with standard Uno cards.
     * Includes numbered cards, special wild cards, and special operation cards.
     */
    public void UNODeck(){

        int i;
        for (i=0; i<2; i++) {
            //for cards numbered 1-9
            for (int k = 1; k <= 9; k++) {
                deck.add(new UnoCard(UnoColor.blue, UnoValue.values()[k], UnoColor.pink,UnoValue.values()[k]));
                deck.add(new UnoCard(UnoColor.green, UnoValue.values()[k], UnoColor.teal,UnoValue.values()[k]));
                deck.add(new UnoCard(UnoColor.red, UnoValue.values()[k], UnoColor.orange,UnoValue.values()[k]));
                deck.add(new UnoCard(UnoColor.yellow, UnoValue.values()[k], UnoColor.purple,UnoValue.values()[k]));
            }
        }

        //for special wild cards
        for (int m = 0; m< 8 ; m++){
            deck.add(new UnoCard(UnoColor.red, UnoValue.draw_one, UnoColor.pink, UnoValue.draw_five));
            deck.add(new UnoCard(UnoColor.yellow, UnoValue.reverse, UnoColor.teal, UnoValue.reverse));
            deck.add(new UnoCard(UnoColor.green, UnoValue.skip, UnoColor.orange, UnoValue.skip_everyone));
        }

        // for two special operations, reverse and skip for every card
        for (int m = 0; m < 4; m++) {
            deck.add(new UnoCard(UnoColor.wild, UnoValue.wild, UnoColor.wild, UnoValue.wild));
            deck.add(new UnoCard(UnoColor.wild, UnoValue.wild_draw_two, UnoColor.wild, UnoValue.wild_draw_color));
        }

        for (int p = 0 ; p < 2 ; p++){
            deck.add(new UnoCard(UnoColor.red,UnoValue.flip,UnoColor.pink,UnoValue.flip));
            deck.add(new UnoCard(UnoColor.yellow,UnoValue.flip,UnoColor.teal,UnoValue.flip));
            deck.add(new UnoCard(UnoColor.green,UnoValue.flip,UnoColor.orange,UnoValue.flip));
            deck.add(new UnoCard(UnoColor.blue,UnoValue.flip,UnoColor.purple,UnoValue.flip));
        }

        this.shuffleDeck();
    }


    /**
     * This method for drawn card from the deck
     * @return Card removed from deck
     */
    public UnoCard removeFromDeck(){
        return (UnoCard)this.deck.remove(this.deck.size()-1);
    }

    /**
     * calculate the size of the deck
     * @return int the size
     */
    public int getDeckSize(){
        return deck.size();
    }


    /**
     * the cards drawn are added to a different pile and shuffeled
     * @param pile of discarded cards
     */
    public void fillDiscardedPile(List<UnoCard> pile){
        this.deck.add((UnoCard) pile);
        Collections.shuffle(this.deck);

    }

    /**
     * Retrieves all cards in the Uno deck.
     *
     * @return A list containing all Uno cards in the deck.
     */
    public List<UnoCard> getAllCards() {
        return deck;
    }

    public JsonObject saveAttributesToJson_ArrayList(){
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

        for (int i = 0; i < this.deck.size() ; i++){
            JsonObject jsonObject = this.deck.get(i).saveAttributesToJson();
            jsonArrayBuilder.add(jsonObject);
        }
        // Build the JSON array
        JsonArray jsonArrayList = jsonArrayBuilder.build();

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("Deck ", jsonArrayList)
                .build();

        return jsonObject;
    }
}
