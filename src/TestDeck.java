
package src;


import com.sun.source.tree.AssertTree;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class TestDeck {

   /** Deck deck;
    boolean empty;
    List<Card> cards;


    @Before
    public void setUp(){
        deck = new Deck();
        cards = new ArrayList<Card>();
        deck.UNODeck();
    }

    @Test
    public void testDeckSize(){

        assertEquals(112, cards.size());
    }

    @Test
    public void testEmptyDeck(){
        deck.UNODeck();
        if(cards.size() == 0){
            assertTrue(deck.isEmpty());
        }
        else{
            assertFalse(deck.isEmpty());
        }
    }

    /**@Test
    public void testDeckIsNotEmpty(){
        deck.UNODeck();
        assertFalse(deck.isEmpty());
    }*/


    /**@Test
    public void testShuffledPile(){
        deck.UNODeck();
        Card card = deck.removeFromDeck();

        deck.shufflePile();
        Card sCard = deck.addToPile();

        assertNotEquals(card, sCard);

    }

    @Test
    public void testAddToPile() {
        Card card = deck.addToPile();
        assertEquals(1, cards.size());
        assertNotNull(card);
    }
    @Test
    public void testRemoveFromDeck() {
        Card topCard = deck.removeFromDeck();
        assertEquals(111, cards.size());
        assertNotNull(topCard);
    }*/
    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void testIsEmptyWhenDeckNotEmpty() {
        assertFalse(deck.isEmpty());
    }

    @Test
    public void testIsEmptyWhenDeckEmpty() {
        // Make the deck empty for testing
        //deck.getDeckSize();
        assertTrue(deck.getDeckSize() == 0);
    }

    @Test
    public void testShuffleDeck() {
        // Test whether shuffleDeck method runs without errors
        deck.UNODeck();
        Card card = deck.removeFromDeck();

        deck.shuffleDeck();
        Card sCard = deck.removeFromDeck();

        assertNotEquals(card, sCard);
    }

    /**@Test
    public void testShufflePile() {
        // Test whether shufflePile method runs without errors
    //    deck.shufflePile();
      //  deck.UNODeck();
//        Card card = deck.addToPile();

  //      deck.shufflePile();
    //    Card sCard = deck.addToPile();

      //  assertNotEquals(card, sCard);

        List<Card> pile = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            pile.add(new Card(Color.red, Color.blue, Value.values()[i], Value.values()[i]));
        }

        // Set the pile in the deck
        pile.addToPile();

        // Create a copy of the pile for later comparison
        List<Card> originalPile = new ArrayList<>(pile);

        // Shuffle the pile
        deck.shufflePile();

        // Check that the pile has been shuffled
        assertNotEquals(originalPile, deck.getPileSize());

        // To ensure it's a valid shuffle, sort the pile and compare it to the original
        Collections.sort(pile, (card1, card2) -> card1.toString().compareTo(card2.toString()));
        Collections.sort(deck.getPile(), (card1, card2) -> card1.toString().compareTo(card2.toString()));

        assertEquals(pile, deck.getPile());
    }*/



    @Test
    public void testUNODeck() {
        // Test whether UNODeck method runs without errors
        deck.UNODeck();
    }

    @Test
    public void testAddToPile() {
        Card card = new Card(Color.red, Color.blue, Value.ONE, Value.TWO);
        deck.addToPile();
        assertEquals(1, deck.getPileSize());
        assertEquals(card, deck.getPileSize());
    }

    @Test
    public void testRemoveFromDeck() {
        // Assuming there is at least one card in the deck
        Card removedCard = deck.removeFromDeck();
        assertNotNull(removedCard);
        assertEquals(removedCard, deck.getDeckSize()-1);
    }
}

