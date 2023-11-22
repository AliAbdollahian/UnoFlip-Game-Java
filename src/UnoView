import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class UnoView {

    private JFrame frame;
    private JPanel playerIDPanel;
    private JPanel topCardPanel;
    private JPanel statusPanel;
    private JPanel buttonsPanel;
    private JPanel handPanel;
    private JLabel currPlayerLabel;
    private JLabel topCardTextLabel;
    private JLabel topCardImageLabel;
    private JLabel statusLabel;
    private JButton nextPlayerButton;
    private JButton drawCardButton;
    private String status;
    private String currPlayer;
    private int numPlayers;
    private UnoController controller;

    public UnoView(UnoController controller){
        this.controller = controller;
    }

    public int promptNumPlayers(){
        numPlayers = Integer.parseInt(JOptionPane.showInputDialog("How many players will play?"));
        return numPlayers;
    }

    public void startGame(){
        frame = new JFrame("UNO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(3000,3000);


        playerIDPanel = new JPanel();
        topCardPanel = new JPanel();
        statusPanel = new JPanel();
        buttonsPanel = new JPanel(new GridLayout());
        handPanel = new JPanel();

        currPlayerLabel = new JLabel("Player: " + currPlayer);
        playerIDPanel.add(currPlayerLabel);

        topCardPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        topCardTextLabel = new JLabel("Top Card", SwingConstants.CENTER);
        topCardImageLabel = new JLabel();
        topCardImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topCardPanel.add(topCardTextLabel);
        topCardPanel.add(topCardImageLabel);

        statusLabel = new JLabel("Status: " + status);
        statusPanel.add(statusLabel);

        nextPlayerButton = new JButton("Next Player");
        buttonsPanel.add(nextPlayerButton, BorderLayout.WEST);

        JScrollPane scroll = new JScrollPane(handPanel);
        handPanel.setLayout(new GridLayout(0, 5));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        buttonsPanel.add(scroll, BorderLayout.CENTER);

        drawCardButton = new JButton("Draw Card");
        buttonsPanel.add(drawCardButton, BorderLayout.EAST);

        frame.add(playerIDPanel, BorderLayout.EAST);
        frame.add(topCardPanel, BorderLayout.CENTER);
        frame.add(statusPanel, BorderLayout.WEST);
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

    }

    public void setCurrPlayer(int playerID){
        this.currPlayer = String.valueOf(playerID);
    }

    public void showHand(Player player, Side side){
        String directoryPath;
        if(side == Side.lightSide){
            directoryPath = "src/Light";
        }
        else{
            directoryPath = "src/Dark";
        }

        File folder = new File(directoryPath);
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles != null){
            for(Card c : player.getHand()) {
                for (File file : listOfFiles) {
                    String[] cardType = file.getName().split("_", 2);
                    if (file.isFile() && isImageFile(file.getName()) && c.getLightBorder().toString().equals(cardType[0]) && c.getLightNum().toString().equals(cardType[1])) {
                        String imagePath = directoryPath + "/" + file.getName();
                        JButton cardButton = createCardButton(imagePath);
                        handPanel.add(cardButton);
                    }
                }
            }

        }
    }

    private boolean isImageFile(String fileName){
        return fileName.toLowerCase().endsWith(".png") || fileName.toLowerCase().endsWith(".jpg") ||fileName.toLowerCase().endsWith(".jpeg");
    }

    private JButton createCardButton(String cardPath){
        JButton cardButton = new JButton();
        try{
            BufferedImage img = ImageIO.read(new File(cardPath));
            ImageIcon icon = new ImageIcon(img);
            cardButton.setIcon(icon);
        } catch (IOException e){
            e.printStackTrace();
        }
        cardButton.setEnabled(true);
        return cardButton;
    }

}
