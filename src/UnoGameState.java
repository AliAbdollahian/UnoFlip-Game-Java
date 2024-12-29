import javax.json.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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



    public UnoGameState(UnoDeck deck, List<UnoCard> discardPile, List<UnoPlayer> players, int currentPlayerIndex, UnoCard topCard, UnoSide currentSide, boolean clockwise, List<UnoCard> hand) {
        this.deck = new UnoDeck(deck);
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

    public JsonObject saveAttributesToJson(String filePath) {
//        // Build the initial JSON object
        JsonObjectBuilder mainJsonObjectBuilder = Json.createObjectBuilder()
                .add("Deck", deck.saveAttributesToJson_ArrayList())
                .add("Player Index", currentPlayerIndex)
                .add("Current Side", currentSide.toString())
                .add("Player's direction", clockwise);

        // Build the JSON array for the discard pile
        JsonArrayBuilder jsonArrayBuilder1 = Json.createArrayBuilder();
        for (int i = 0; i < this.discardPile.size(); i++) {
            JsonObject cardJsonObject = this.discardPile.get(i).saveAttributesToJson();
            jsonArrayBuilder1.add(cardJsonObject);
        }

        // Build the JSON array for the players and hand
        JsonArrayBuilder jsonArrayBuilder2 = Json.createArrayBuilder();
        for (int i = 0; i < this.players.size(); i++) {
            JsonObject playerJsonObject = this.players.get(i).saveAttributesToJson();
            jsonArrayBuilder2.add(playerJsonObject);
        }

        // Build the JSON array for the hand
        JsonArrayBuilder jsonArrayBuilder3 = Json.createArrayBuilder();
        for (int i = 0; i < this.hand.size(); i++) {
            JsonObject cardJsonObject = this.hand.get(i).saveAttributesToJson();
            jsonArrayBuilder3.add(cardJsonObject);
        }

        // Add arrays to the main JSON object
        JsonArray jsonArrayList1 = jsonArrayBuilder1.build();
        JsonArray jsonArrayList2 = jsonArrayBuilder2.build();
        JsonArray jsonArrayList3 = jsonArrayBuilder3.build();
        mainJsonObjectBuilder.add("Discard Deck", jsonArrayList1)
                .add("Players", jsonArrayList2)
                .add("Hand", jsonArrayList3);

        // Build the final JSON object
        JsonObject finalJsonObject = mainJsonObjectBuilder.build();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(finalJsonObject);
            System.out.println("JSON saved successfully to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing JSON to file: " + e.getMessage());
        }

        return finalJsonObject;
    }

}

