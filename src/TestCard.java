package src;

import org.junit.Test;
import org.junit.Before;



import static org.junit.Assert.*;
public class TestCard {
/**
    @Test
    public void testGetLightBorder() {
        Card card = new Card(Color.red, Color.blue, Value.ONE, Value.TWO);
        assertEquals(Color.red, card.getLightBorder());
    }

    @Test
    public void testGetDarkBorder() {
        Card card = new Card(Color.red, Color.blue, Value.ONE, Value.TWO);
        assertEquals(Color.blue, card.getDarkBorder());
    }

    @Test
    public void testGetLightNum() {
        Card card = new Card(Color.red, Color.blue, Value.ONE, Value.TWO);
        assertEquals(Value.ONE, card.getLightNum());
    }

    @Test
    public void testGetDarkNum() {
        Card card = new Card(Color.red, Color.blue, Value.ONE, Value.TWO);
        assertEquals(Value.TWO, card.getDarkNum());
    }

    @Test
    public void testToStringLightBorder() {
        Card card = new Card(Color.RED, Color.BLUE, Value.ONE, Value.TWO);
        card.setCardColor(Color.RED);
        assertEquals("RED: ONE", card.toString());
    }

    @Test
    public void testToStringDarkBorder() {
        Card card = new Card(Color.red, Color.blue, Value.ONE, Value.TWO);
        card.setCardColor(Color.blue;
        assertEquals("BLUE: TWO", card.toString());
    }

    @Test
    public void testToStringUnspecifiedColor() {
        Card card = new Card(Color.red, Color.blue, Value.ONE, Value.TWO);
        // Assuming you haven't set the card color here
        assertEquals("RED: ONE", card.toString()); // Default to lightBorder
    }
}*/

private Card card;

    @Before
    public void setUp() {
        card = new Card(Color.red, Color.blue, Value.ONE, Value.TWO);
    }

    @Test
    public void testGetLightBorder() {
        assertEquals(Color.red, card.getLightBorder());
    }

    @Test
    public void testGetDarkBorder() {
        assertEquals(Color.blue, card.getDarkBorder());
    }

    @Test
    public void testGetLightNum() {
        assertEquals(Value.ONE, card.getLightNum());
    }

    @Test
    public void testGetDarkNum() {
        assertEquals(Value.TWO, card.getDarkNum());
    }

    @Test
    public void testToStringLightBorder() {
        card.setColor(Color.red);
        assertEquals("red: ONE", card.toString());
    }

    @Test
    public void testToStringDarkBorder() {
        card.setColor(Color.blue);
        assertEquals("blue: TWO", card.toString());
    }

    /**@Test
    public void testToStringUnspecifiedColor() {
        // Assuming you haven't set the card color here
        assertEquals("red: ONE", card.toString()); // Default to lightBorder
    }*/
}

