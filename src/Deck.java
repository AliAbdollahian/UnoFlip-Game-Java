package src;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Deck {
    private List<Card> deck;
    private List<Card> pile;
    private Card crd;


    public Deck(){
        this.deck = new ArrayList<Card>(112);
        this.pile = new ArrayList<Card>(112);
        this.UNODeck();
    }

    //check if the deck is empty
    public boolean isEmpty(){
        if(deck.isEmpty()){
            deck=pile;
            shuffleDeck();
        }
        else {
            //continue game
            //play a card immidiately after

        }
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
        //for (i=0; i<deck.size(); i++){
        for (i=1; i<=9; i++){
            deck.add(new Card(Color.blue, Value.values()[i]));
            deck.add(new Card(Color.green, Value.values()[i]));
            deck.add(new Card(Color.red, Value.values()[i]));
            deck.add(new Card(Color.yellow, Value.values()[i]));
        }

        for (int m=1; m<=2; m++){
            deck.add(new Card(Color.special, Value.WILD));
            deck.add(new Card(Color.special, Value.WILD_DRAW_2));
            //deck.add(new Card(Color.red, Color.orange, Value.values()[j], Value.values()[j]));
            //deck.add(new Card(Color.yellow, Color.purple, Value.values()[j], Value.values()[j]));
        }

        // DRAW_1, DRAW_5, REVERSE, SKIP, SKIP_EVERYONE, FLIP,
        for (int m=1; m<=2; m++) {
            deck.add(new Card(Color.blue, Value.REVERSE));
            deck.add(new Card(Color.green, Value.REVERSE));
            deck.add(new Card(Color.red, Value.REVERSE));
            deck.add(new Card(Color.yellow, Value.REVERSE));
            deck.add(new Card(Color.blue, Value.SKIP));
            deck.add(new Card(Color.green, Value.SKIP));
            deck.add(new Card(Color.red, Value.SKIP));
            deck.add(new Card(Color.yellow, Value.SKIP));

        }
        //}
        this.shuffleDeck();

        //return null;
    }
    public void addToPile(){
        this.pile.add(crd);

    }

    public Card removeFromDeck(){
        return (Card)this.deck.remove(this.deck.size()-1);
        //print statement

    }

    public int getDeckSize(){
        return deck.size();
    }

    public int getPileSize(){
        return pile.size();
    }

}
