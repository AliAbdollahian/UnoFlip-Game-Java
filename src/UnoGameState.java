import java.util.ArrayList;
import java.util.List;

public class UnoGameState {
    public UnoDeck deck;
    public List<UnoCard> discardPile;
    public List<UnoPlayer> players;
    public int currentPlayerIndex;



    public UnoCard topCard;
    public UnoSide currentSide;
    public boolean clockwise;

    public List<UnoCard> hand;
    public List<UnoCard> handCopy;



    public UnoGameState(UnoDeck deck, List<UnoCard> discardPile, List<UnoPlayer> players, int currentPlayerIndex, UnoCard topCard, UnoSide currentSide, boolean clockwise, List<UnoCard> hand) {
        this.deck = new UnoDeck(deck); // Assuming UnoDeck has a copy constructor
        this.discardPile = new ArrayList<>(discardPile);
        this.players = new ArrayList<>(players);
        this.currentPlayerIndex = currentPlayerIndex;
        this.topCard = topCard;
        this.currentSide = currentSide;
        this.clockwise = clockwise;
        this.hand = new ArrayList<>(hand);
    }

    public UnoDeck getDeck() {
        return deck;
    }

    public List<UnoCard> getDiscardPile() {
        return discardPile;
    }

    public List<UnoPlayer> getPlayers() {
        return players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    public UnoCard getTopCard() {
        return topCard;
    }

    public UnoSide getCurrentSide() {
        return currentSide;
    }

    public boolean isClockwise() {
        return clockwise;
    }
    public List<UnoCard> getHand() {
        return hand;
    }
}

