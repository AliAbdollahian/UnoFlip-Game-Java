package src;
public class Card {
    private final Color lightBorder;
//    private final Color darkBorder;
    private final Value lightNum;
//    private final Value darkNum;
//    private final Side cardSide;



    private Color cardColor;

    public Card (Color lightBorder, Value lightNum){
        this.lightBorder = lightBorder;
//        this.darkBorder = darkBorder;
        this.lightNum = lightNum;
//        this.darkNum = darkNum;
//        this.cardSide = cardSide;
    }

    public Color  getLightBorder(){
        return this.lightBorder;
    }

//    public Color getDarkBorder() {
//        return this.darkBorder;
//    }

    public Value getLightNum(){
        return this.lightNum;
    }

//    public Value getDarkNum(){
//        return this.darkNum;
//    }

    public String toString(){

        //unogame if currentCard == lightBorder{ this.lightNum && this.l} else { this.darkBorder && this.darkNum};
        String str = this.lightBorder + ": " + this.lightNum;
//        if (cardColor==lightBorder){
//            str = this.lightBorder + ": " + this.lightNum;
//        }
//        else{
////            str = this.darkBorder + ": " + this.darkNum;
//        }
        return str;
    }
}

