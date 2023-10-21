package src;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Deck {
    private List<Card> deck;
    private List<Card> pile;
    private Card crd;

    //private int currCD;
    //private int currCP;

    public Deck(){
        this.deck = new ArrayList<Card>(112);
        this.pile = new ArrayList<Card>();
    }

    //check if the deck is empty
    public boolean isEmpty(){
        if(deck.isEmpty()){
            pile=deck;
            shuffleDeck();
        }
        //continue game
        //play a card immidiately after
        return true;
    }
    public void shuffleDeck(){
        Collections.shuffle(deck, new Random());
    }

    public void shufflePile(){
        Collections.shuffle(pile, new Random());
    }

    public void UNODeck(){
        //deck.Colo = deck.Color.Values();

        int i;
        for (i=0; i<deck.size(); i++){
            for (int j=1; j==9; j++){
                deck.add(new Card(Color.blue, Color.pink, Value.values()[j], Value.values()[j]));//, Color.pink, Value.values([j]));
                deck.add(new Card(Color.green,  Color.teal, Value.values()[j], Value.values()[j]));
                deck.add(new Card(Color.red, Color.orange, Value.values()[j], Value.values()[j]));
                deck.add(new Card(Color.yellow, Color.purple, Value.values()[j], Value.values()[j]));
            }

            for (int m=1; m<=2; m++){
                deck.add(new Card(Color.special, Color.special, Value.WILD, Value.WILD));
                deck.add(new Card(Color.special,  Color.special, Value.WILD_DRAW_2, Value.WILD_DRAW_COLOR));
                //deck.add(new Card(Color.red, Color.orange, Value.values()[j], Value.values()[j]));
                //deck.add(new Card(Color.yellow, Color.purple, Value.values()[j], Value.values()[j]));
            }

            // DRAW_1, DRAW_5, REVERSE, SKIP, SKIP_EVERYONE, FLIP, WILD
            for (int m=1; m<=2; m++) {
                deck.add(new Card(Color.blue, Color.pink, Value.REVERSE, Value.DRAW_5));
                deck.add(new Card(Color.green, Color.teal, Value.REVERSE, Value.REVERSE));
                deck.add(new Card(Color.red, Color.orange, Value.REVERSE, Value.SKIP_EVERYONE));
                deck.add(new Card(Color.yellow, Color.purple, Value.REVERSE, Value.FLIP));
                deck.add(new Card(Color.blue, Color.pink, Value.SKIP, Value.DRAW_5));
                deck.add(new Card(Color.green, Color.teal, Value.SKIP, Value.REVERSE));
                deck.add(new Card(Color.red, Color.orange, Value.SKIP, Value.SKIP_EVERYONE));
                deck.add(new Card(Color.yellow, Color.purple, Value.SKIP, Value.FLIP));
            }
        }

    }
     public void addToPile(){
        this.pile.add(crd);
     }

     public Card removeFromDeck(){
        return crd = this.deck.remove(this.deck.size()-1);
        //print statement

     }

}
