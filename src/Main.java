import java.io.IOException;
import java.util.ArrayList;

public class Main {

    /**
     * The main method to run the UNO game by creating an instance of the `UnoView` class.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) throws IOException {
        new UnoView();

        UnoGame game = new UnoGame();
        try {
            game.saveAttributesToJson_ArrayList("Game.json\"");
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        //restore
        ArrayList<UnoPlayer> players = UnoGame.restoreFromJsonFile("Game.json\"");
        UnoGame game1 = new UnoGame();
        game1.printPlayers();
    }
}
