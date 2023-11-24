//import java.util.Scanner;
//
//public class AIPlayer extends UnoPlayer {
//
//    public AIPlayer(String name) {
//        super(name);
//    }
//
//    @Override
//    void takeTurn(UnoGame game) {
//        Scanner sc = new Scanner(System.in);
//        int choice = sc.nextInt();
//        System.out.println(getName() + "'s Turn.");
//
//        UnoCard topCard = game.discardPile.get(game.discardPile.size() - 1);
//        UnoCard chosenCard = chooseCardToPlay(topCard);
//
//        if (chosenCard != null && game.isValidPlay(chosenCard, topCard)) {
//            UnoCard playedCard = (UnoCard) this.getHand().get(choice - 1);
//            game.discardPile.add(playedCard);
//            game.temporaryChosenColor = null;
//            System.out.println(getName() + " played: " + playedCard);
//            game.handleSpecialCard(playedCard);
//
//            if (getHand().isEmpty()) {
//                System.out.println(getName() + " has won!");
//                game.calculateScores(this);
//                System.exit(0);
//            }
//        } else {
//            drawCard(game);
//            System.out.println(getName() + " drew a card.");
//        }
//    }
//
//    private UnoCard chooseCardToPlay(UnoCard topCard) {
//        for (UnoCard card : getHand()) {
//            if (cardMatchesTopCard(card, topCard)) {
//                return card;
//            }
//        }
//        return null;  // If no matching card is found, return null to draw a card.
//    }
//
//    private boolean cardMatchesTopCard(UnoCard card, UnoCard topCard) {
//        return card.getLightColor() == topCard.getLightColor() ||
//                card.getDarkColor() == topCard.getDarkColor() ||
//                card.getLightValue() == topCard.getLightValue() ||
//                card.getDarkValue() == topCard.getDarkValue();
//    }
//
//    private void drawCard(UnoGame game) {
//        if (!game.deck.isEmpty()) {
//            UnoCard drawnCard = game.deck.removeFromDeck();
//            drawUnoCard(drawnCard);
//        } else {
//            game.refillDeckFromDiscard();
//            UnoCard drawnCard = game.deck.removeFromDeck();
//            drawUnoCard(drawnCard);
//        }
//    }
//}
//
//
//
////import java.util.*;
////
////public class AIPlayer extends UnoPlayer{
////
////    private List<UnoPlayer> players;
////    private List<UnoCard> hand;
////    private UnoPlayer AIplayer;
////    private UnoGame game;
////    private UnoCard topCard;
////    private UnoDeck deck;
////    private UnoColor color;
////    private UnoValue value;
////
////    public AIPlayer(){
////        super("AI Player");
////        hand = new ArrayList<>();
////        deck = new UnoDeck();
////        deck.shuffleDeck();
////        topCard = new UnoCard(color, value, color, value);
////        AIplayer = new AIPlayer();
////
////        //iterate over hand of AIPlayer, if the AIPlayer card the matches
////
////        //the card on table then play it, otherwise draw a card
////        //if playing a wildcard, choose a color
////
////    }
////
////    public void iterAIHand(){
////        UnoPlayer player = new UnoPlayer(AIplayer.name);
////        if(AIplayer.getHand().isEmpty()){
////            for(int j = 0; j < 7; ++j) {
////                player.drawUnoCard(this.deck.removeFromDeck());
////            }
////        }else {
////            hand = AIplayer.getHand();
////            for (int i = 0; i < hand.size(); i++) {
////                System.out.println((i + 1) + ". " + hand.get(i));
////
////                for(UnoCard card: deck.getAllCards()){
//////                    while(game.isValidPlay(card, topCard)) {
//////                        //make statements to match the different cards
//////                        String color;
//////                        String drawnCard;
//////                        if (UnoGame.getCurrentSide() == UnoSide.LIGHT) {
//////                            if (card.getLightColor().equals(topCard) || card.getLightValue().equals(topCard)){
//////                                AIplayer.playedCard(card);
//////                            }else if(card.getDarkColor().equals(topCard) || card.getDarkValue().equals(topCard){
//////                                AIplayer.playedCard(card);
//////                            }else{
//////                                AIplayer.drawUnoCard(card);
//////                                deck.removeFromDeck();
//////                            }
//////                        }
//////                    UnoGame.getCurrentSide()==UnoValue.valueOf(card.))
//////                        card.getImageFileName();
////                    game.isValidPlay(card, topCard);
////
////                    game.handleSpecialCard(card);
////                    game.calculateScores(AIplayer);
////                    }
//////                    if(game.isValidPlay(card, topCard)==false){
//////                        game.handleSpecialCard(card);
//////
//////                    }
////                }
//////                if(AIplayer.getHand().isEmpty()){
//////                    System.out.println("Calculating score: ");
//////
//////                    if(currPlayer.getScore() >= 500) {
//////                        System.out.println(currPlayer.getName());
//////                    }
//////                }else{
//////                    //next player turn
//////                }
////            }
////        }
////
//////        //wild cards
//////        Value value = topCard.getLightNum();
//////        Color color = topCard.getLightBorder();
//////        while (topCard instanceof Card){
////////            Random random = new Random();
////////            random.nextInt();
//////            //switch (random.nextInt()){
//////            if(card.getLightBorder().equals(color) || card.getLightNum().equals(value)){
//////                game.handleWildCardColorSelection(card,color);
////                //break;
////
//////                case 2:
//////                    game.handleWildCardColorSelection(card,Color.RED);
//////                    break;
//////                case 3:
//////                    game.handleWildCardColorSelection(card,Color.GREEN);
//////                    break;
//////                case 4:
//////                    game.handleWildCardColorSelection(card,Color.YELLOW);
//////                    break;
//////                default:
//////                    System.out.println("Invalid color choice. try again");
//////                    game.handleWildCardColorSelection(card, color);
////}
////
