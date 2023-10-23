package src;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
public class TestDeck {

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
            assertTrue(deck.isEmpty());
        }

        @Test
        public void testShuffleDeck() {
            // Test whether shuffleDeck method runs without errors
            //deck.shuffleDeck();
            deck.UNODeck();
            Card card = deck.removeFromDeck();

            deck.shuffleDeck();
            Card sCard = deck.removeFromDeck();

            assertNotEquals(card, sCard);
        }

        @Test
        public void testUNODeck() {
            deck.UNODeck();
        }

        @Test
        public void testRemoveFromDeck() {
            // Assuming there is at least one card in the deck
            Card removedCard = deck.removeFromDeck();
            assertNotNull(removedCard);
            assertEquals(removedCard, deck.getDeckSize() - 1);
        }

        @Test
        public void testDiscardedPile(){
            List<Card> discardedPile = new ArrayList<>();
            discardedPile.add(new Card(Color.red, Value.FIVE));
            discardedPile.add(new Card(Color.blue, Value.REVERSE));
            discardedPile.add(new Card(Color.green, Value.SKIP));
            discardedPile.add(new Card(Color.yellow, Value.EIGHT));

            int initialDeckSize = deck.getDeckSize();

            deck.discardedPile(discardedPile);

            int finalDeckSize = deck.getDeckSize();

            int discardedPileSize = discardedPile.size();
            assertTrue((finalDeckSize - initialDeckSize) == discardedPileSize);
    }

}
