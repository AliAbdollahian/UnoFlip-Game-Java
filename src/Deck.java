/**
 * This is the Deck class that initializes  the cards in the deck
 * to their values, colors, and special values.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck;

    /**
     * Constructor for the Deck Class
     */
    public Deck(){
        this.deck = new ArrayList<Card>(112);
        this.UNODeck();
    }


    /**
     *Returns true if the deck is empty
     * @return boolean true
     */
    public boolean isEmpty(){
        if(deck.isEmpty()){
            //deck=pile;
            shuffleDeck();
        }
        return true;
    }

    /**
     * Shuffling the deck by importing the Collections library
     */
    public void shuffleDeck(){
        Collections.shuffle(deck, new Random());
    }

    /**
     * Initialize the cards in the deck
     */
    public void UNODeck(){

        int i,k;
        for (i=0; i<112; i++) {
            //for cards numbered 1-9
            for (k = 1; k <= 9; k++) {
                deck.add(new Card(Color.blue, Value.values()[k]));
                deck.add(new Card(Color.green, Value.values()[k]));
                deck.add(new Card(Color.red, Value.values()[k]));
                deck.add(new Card(Color.yellow, Value.values()[k]));
            }
        }

        //for special wild cards
        for (int m=1; m<=2; m++){
            deck.add(new Card(Color.special, Value.WILD));
            deck.add(new Card(Color.special, Value.DRAW_1));
            deck.add(new Card(Color.special, Value.WILD_DRAW_2));
        }

        // for two special operations, reverse and skip for every card
        for (int m=1; m<=2; m++) {
            deck.add(new Card(Color.blue, Value.REVERSE));
            deck.add(new Card(Color.green, Value.REVERSE));
            deck.add(new Card(Color.red, Value.REVERSE));
            deck.add(new Card(Color.yellow, Value.REVERSE));
            deck.add(new Card(Color.blue, Value.SKIP));
            deck.add(new Card(Color.green, Value.SKIP));
            deck.add(new Card(Color.red, Value.SKIP));
            deck.add(new Card(Color.yellow, Value.SKIP));

        }

        this.shuffleDeck();
    }


    /**
     * This method for drawn card from the deck
     * @return Card removed from deck
     */
     public Card removeFromDeck(){
        return (Card)this.deck.remove(this.deck.size()-1);
        //print statement

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
    public void discardedPile(List<Card> pile){
        this.deck.add((Card) pile);
        Collections.shuffle(this.deck);
    }


}
