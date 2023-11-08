package src; /**
 * Testing player Class relating to current Players in the Uno Flip Card game
 * Name: Arkan Slabi
 * Student #101154754
 */



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private Player player;
    private List<Card> testingHand;

    @Before
    // Initializes environment of the tests
    public void testStarting() {
        List<Card> testHand = new ArrayList<>();
        player = new Player("Player For Test");
    }

    @Test
    public void testGetName() {
        // method checking whether the player has a name
        assertEquals("Player For test", player.getName());
    }

    @Test
    public void testAddCard() {
        // method checking if cards are able to be added to players hand
        Card card = new Card(Color.blue, Value.THREE);
        player.addCard(card);
        assertTrue(player.getHand().contains(card));
    }

    @Test
    public void testGetScore() {
        // method getting current score of a particular player
        assertEquals(Integer.valueOf(0), player.getScore());
    }

    @Test
    public void testSetScore() {
        // method seeing whether scores can be set
        player.setScore(10);
        assertEquals(Integer.valueOf(10), player.getScore());
    }

    @Test
    public void testGetHand() {
        // checks if hand is accessible via get
        assertNotNull(player.getHand());
        assertTrue(player.getHand().isEmpty());
    }

    @Test
    public void testPlayCard() {
        // method ensures whether cards can be played by a player
        Card card = new Card(Color.blue, Value.EIGHT);
        player.addCard(card);

        Card playedCard = player.playCard(0);

        assertNotNull(playedCard);
        assertEquals(card, playedCard);
        assertTrue(player.getHand().isEmpty());
    }

    public void testScoreOfHand() {
        // method adds scores of card and tests to see if outputted score is correct
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Color.red, Value.ONE));
        hand.add(new Card(Color.blue, Value.EIGHT));

        Player player = new Player("Player For Test");


        Integer expectedScore = 4 + 5;


        assertEquals(expectedScore, player.getScore());
    }
    @Test
    public void testDrawCard() {
        // ensures player can draw cards
        Card card = new Card(Color.yellow, Value.FOUR);
        player.playCard(0);

        assertTrue(player.getHand().contains(card));
    }
}

