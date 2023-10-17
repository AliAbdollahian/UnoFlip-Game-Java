package src;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Deck {
    private List<Card> deck;
    private List<Card> pile;
    private Card crd;


    private int currCD;
    private int currCP;

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

            for (int m=1; m==4; m++){
                deck.add(new Card(Color.special, Color.special, Value.WILD, Value.WILD));
                deck.add(new Card(Color.special,  Color.special, Value.WILD_DRAW_2, Value.WILD_DRAW_COLOR));
                //deck.add(new Card(Color.red, Color.orange, Value.values()[j], Value.values()[j]));
                //deck.add(new Card(Color.yellow, Color.purple, Value.values()[j], Value.values()[j]));
            }

            // DRAW_1, DRAW_5, REVERSE, SKIP, SKIP_EVERYONE, FLIP,
            for (int m=1; m==8; m++) {
                deck.add(new Card(Color.special, Color.special, Value.DRAW_1, Value.DRAW_5));
                deck.add(new Card(Color.special, Color.special, Value.SKIP, Value.SKIP_EVERYONE));
                //deck.add(new Card(Color.special, Color.special, Value.FLIP, Value.REVERSE));
                //deck.add(new Card(Color.yellow, Color.purple, Value.values()[j], Value.values()[j]));
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
