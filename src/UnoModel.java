import java.lang.reflect.Array;
import java.util.ArrayList;

public class UnoModel {

    private int numPlayers;
    private ArrayList<Player> players;
    private Deck deck;
    private Player currPlayer;
    private Side side;

    public UnoModel(){
        deck = new Deck();
        this.side = Side.lightSide;
    }

    public void createPlayers(int numPlayers){
        this.numPlayers = numPlayers;
        players = new ArrayList<>();
        for(int i = 0; i < numPlayers-1; i++){
            players.add(new Player(i+1));
        }
    }

    public Player getPlayer(int id){
        for(Player p : players){
            if(p.getID() == id){
                return p;
            }
        }
        return null;
    }

    public Player getCurrPlayer(){
        return currPlayer;
    }

    public Side getCurrSide(){
        return this.side;
    }

    public void setCurrPlayer(int playerID){
        this.currPlayer = getPlayer(playerID);
    }

    public void dealToPlayers(){
        for(int i = 0; i < 7; i++) {
            for (Player p : players) {
                p.addCard(deck.removeFromDeck());
            }
        }
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public Boolean roundWon(){
        // if a player has no cards in their hand
        return false;
    }
}
