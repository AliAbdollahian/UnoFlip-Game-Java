public class UnoController {

    UnoModel model;

    UnoView view;

    int numPlayers;

    public UnoController(){
        model = new UnoModel();
        view = new UnoView(this);
    }

    public void start(){
        view.setCurrPlayer(1);

        view.startGame();
        numPlayers = view.promptNumPlayers();
        model.createPlayers(numPlayers);
        model.dealToPlayers();

        this.handleNewRound();
    }

    public void handleDrawCard(){}

    public void handlePlayCard(){}

    public void handleNewRound(){
        view.setCurrPlayer(1);
        model.setCurrPlayer(1);
        model.dealToPlayers();
        while(!model.roundWon()){
            view.showHand(model.getCurrPlayer(), model.getCurrSide());

        }
    }

    public void handleEndGame(){}

    public static void main(String[] args) {
        UnoController controller = new UnoController();
        controller.start();

    }

}
