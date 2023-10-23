package src;
public class Card {
    private final Color lightBorder;

    private final Value lightNum;

    private Color cardColor;

    public Card (Color lightBorder, Value lightNum){
        this.lightBorder = lightBorder;

        this.lightNum = lightNum;

        this.cardColor = lightBorder;

    }

    public Color  getLightBorder(){
        return this.lightBorder;
    }


    public void setColor(Color color) {
        this.cardColor = color;
    }

    public Value getLightNum(){
        return this.lightNum;
    }


    public String toString(){

        String str = this.lightBorder + ": " + this.lightNum;

        return str;
    }
}

