package src;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnoGameTest {
    private static int counter;
    private static UnoGame unoGame;

    @BeforeAll
    public static void setUp() {
        counter = 0;
        unoGame = new UnoGame(2);
    }

    @AfterEach
    public void summary() {
        counter++;
        System.out.println("Number of tests run: " + counter);
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("All tests are done");
    }

    @Test
    public void testDealHand() {
        unoGame.dealHand();
        for (Player player : unoGame.players) {
            assertEquals(7, player.getHand().size());
        }
    }
}


