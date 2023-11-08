/**
 * This is the Card class that defines which cards in the deck will
 * be used in the UNOFlip game.
 */

package src;

public class Card {
    private final Color lightBorder;
    private final Value lightNum;

    /**
     * Constructor for Card class
     * @param lightBorder the colors on the light side
     * @param lightNum the value on the light side
     */
    public Card (Color lightBorder, Value lightNum){
        this.lightBorder = lightBorder;
        this.lightNum = lightNum;

    }

    /**
     * getter method for the color of the light side of the cards.
     * @return Color the color on the light side of the card.
     */
    public Color  getLightBorder(){
        return this.lightBorder;
    }


    /**
     * getter method for the value of the light side of the cards.
     * @return Valye the value on the light side of the card.
     */
    public Value getLightNum(){
        return this.lightNum;
    }


    /**
     * Returns the color and value description of the card played.
     * @return      String
     */
    public String toString(){
        
        String str =  this.lightBorder + ": " + this.lightNum;;
        
        return str;
    }
}
