package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UnoView extends JFrame {

    private JFrame frame;
    private JButton drawCard;
    private JButton nextPlayer;

    private JLabel status;
    private JLabel playerName;
    private JPanel hand;

    public UnoView (){
        //set the game frame
        frame = new JFrame("UNO GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //player's cards display
        hand = new JPanel();
        hand.setLayout(new GridLayout(0, 5));
        for (Component button : hand.getComponents()) { //get component for all cards in hand
            button.setEnabled(false); //initially false
        }

        JScrollPane scroll = new JScrollPane(hand);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scroll, BorderLayout.CENTER);

        //show player's name and status
        playerName = new JLabel();
        status = new JLabel();

        //Enable card and next player button
        drawCard = new JButton();
        drawCard.setEnabled(false);

        nextPlayer = new JButton();
        nextPlayer.setEnabled(false);

        //choose how many player
        JFrame choose = new JFrame("Choose number of players: ");
        choose.setLocationRelativeTo(null);


    }

    public void choosePlayersNum(){
        int numPlayers[] = {2,3,4};

        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);


    }
    public void activateDrawCard(){
        drawCard.setEnabled(true);
    }

    public void activateNextPlayer(){
        nextPlayer.setEnabled(true);

    }

    public void activateHandCard(){
        for (Component button : hand.getComponents()) { //get component for all cards in hand
            button.setEnabled(true);
        }
    }
    public void updateTopCard(){}

    public void updateHand(){}

    public void displayEndRound(){}

    public void displayEndGame(){}

    public void displayStartMenu(){}

    public void updatePlayer(){}

}
