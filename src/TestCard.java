package src;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCard {

    private Card card;

    @Before
    public void setUp() {
        card = new Card(Color.red, Value.ONE);
    }

    @Test
    public void testGetLightBorder() {
        assertEquals(Color.red, card.getLightBorder());
    }

    @Test
    public void testGetLightNum() {
        assertEquals(Value.ONE, card.getLightNum());
    }

    @Test
    public void testToString() {
        assertEquals("red: ONE", card.toString());
    }
}
