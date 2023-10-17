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
        return deck.isEmpty();
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

            for (int m=1; m==12; m++){
                deck.add(new Card(Color.blue, Color.pink, Value.values()[j], Value.values()[j]));//, Color.pink, Value.values([j]));
                deck.add(new Card(Color.green,  Color.teal, Value.values()[j], Value.values()[j]));
                deck.add(new Card(Color.red, Color.orange, Value.values()[j], Value.values()[j]));
                deck.add(new Card(Color.yellow, Color.purple, Value.values()[j], Value.values()[j]));
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
