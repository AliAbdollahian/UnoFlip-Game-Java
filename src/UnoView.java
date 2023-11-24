import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The `UnoView` class represents the graphical user interface (GUI) for the UNO game.
 * It extends `JFrame` and includes components such as buttons, labels, and panels
 * to display the game's current state and allow user interactions.
 */
public class UnoView extends JFrame {
    public UnoGameController unoGameController;
    public JFrame frame;
    public JButton drawCard;
    public JButton nextPlayer;
    public JLabel status;
    public JLabel playerName;
    public JPanel handPanel;
    public JLabel topCardTextLabel;
    public JLabel topCardImageLabel;
    public JLabel currentPlayerLabel;
    public ImageIcon topCardImage;
    public String cardPath;

    /**
     * Constructs an instance of the `UnoView` class, initializing the UNO game controller
     * and creating the GUI frame by calling the `makeFrame` method.
     */
    public UnoView(){
        unoGameController = new UnoGameController(new UnoGame(),this);
        makeFrame();
    }

    /**
     * Creates and initializes the UNO game GUI frame, including player information,
     * top card display, buttons for drawing and moving to the next player,
     * and the player's hand display.
     */
    public void makeFrame() {
        //set the game frame
        frame = new JFrame();
        this.setTitle("UNO GAME");
        frame.setLayout(new BorderLayout());


        //show player's name and status
        playerName = new JLabel();


        // Layout for main content
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        // Add a panel for the current player at the top-left
        JPanel currentPlayerPanel = new JPanel(new GridBagLayout());
        currentPlayerLabel = new JLabel("Player: ");
        currentPlayerPanel.add(currentPlayerLabel);
        currentPlayerPanel.add(playerName);
        unoGameController.setPlayerName();

        // Add the currentPlayerPanel to the LINE_START region
        add(currentPlayerPanel, BorderLayout.LINE_START);

        // Add a panel for the top card at the top
        JPanel topCardPanel = new JPanel(new GridBagLayout());
        topCardPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Add a label for the top card text
        topCardTextLabel = new JLabel("Top Card", SwingConstants.CENTER);

        // Add a label for the top card image
        topCardImageLabel = new JLabel();
        topCardImageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Set up GridBagConstraints for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        topCardPanel.add(topCardTextLabel, gbc);

        gbc.gridy = 1;
        topCardPanel.add(topCardImageLabel, gbc);

        // Add the topCardPanel to the NORTH region
        add(topCardPanel, BorderLayout.NORTH);

        // Add a panel for buttons at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        drawCard = new JButton("Draw Card");
        buttonPanel.add(drawCard, BorderLayout.EAST);
        drawCard.addActionListener(e->unoGameController.drawCardClicked());


        nextPlayer = new JButton("Next Player");
        nextPlayer.addActionListener(e->unoGameController.nextPlayerClicked());
        buttonPanel.add(nextPlayer, BorderLayout.WEST);
        nextPlayer.setEnabled(false);

        handPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(handPanel);
        handPanel.setLayout(new GridLayout(0, 5));
        frame.add(scroll, BorderLayout.CENTER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        setTopCardImage(null);
        // Add sample cards buttons to the playerCardsPanel
        displaySampleCards(UnoGame.getCurrentSide());

        JScrollPane scrollPane = new JScrollPane(handPanel);
        buttonPanel.add(scrollPane, BorderLayout.CENTER);

        // Add a textbox for displaying "Status" at the bottom-right
        status = new JLabel("Status");
        buttonPanel.add(status, BorderLayout.PAGE_START);

        // Set the JFrame visible
        add(buttonPanel, BorderLayout.SOUTH);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Displays sample cards in the player's hand based on the current game side.
     * Clears the existing handPanel and adds buttons for each card in the player's hand.
     *
     * @param side The current game side (LIGHT or DARK).
     */
    public void displaySampleCards(UnoSide side) {
        UnoPlayer currentPlayer = unoGameController.unoGame.players.get(unoGameController.unoGame.currentPlayerIndex);
        handPanel.removeAll();
        if (handPanel.getComponentCount() == 0) {
            for (UnoCard card : currentPlayer.getHand()) {
                String cardPath = "src/" + (side == UnoSide.LIGHT ? "Light/" : "Dark/") +
                        card.getImageFileName(side);

                JButton cardButton = createCardButton(cardPath);
                handPanel.add(cardButton);
            }
        }
        handPanel.revalidate();
        handPanel.repaint();
    }

    /**
     * Creates a button for a UNO card with the specified image file path.
     * Sets up the button's icon and action listener for user interaction.
     *
     * @param cardPath The file path to the image of the UNO card.
     * @return The created JButton for the UNO card.
     */
    private JButton createCardButton(String cardPath) {
        JButton cardButton = new JButton();
        try {
            File file = new File(cardPath);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + cardPath);
            }
            BufferedImage img = ImageIO.read(file);
            ImageIcon icon = new ImageIcon(img);
            cardButton.setIcon(icon);
            cardButton.addActionListener(e->unoGameController.playerChoseACard(cardPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading image: " + e.getMessage());
        }
        cardButton.setEnabled(true);
        return cardButton;
    }

    /**
     * Sets the image of the top UNO card in the topCardImageLabel based on the specified UnoCard.
     * If the provided UnoCard is null, it uses the top card from the discard pile.
     *
     * @param top The UnoCard to set the top card image, or null to use the top discard pile card.
     */
    public void setTopCardImage(UnoCard top) {
        UnoCard topCard;

        if (top != null) {
            topCard = top;
        } else {
            topCard = unoGameController.unoGame.discardPile.get(0);
        }

        UnoSide currentSide = UnoGame.getCurrentSide();

        String cardPath = "src/" + (currentSide == UnoSide.LIGHT ? "Light/" : "Dark/") + topCard.getImageFileName(currentSide);

        try {
            File file = new File(cardPath);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + cardPath);
            }

            BufferedImage img = ImageIO.read(file);
            topCardImage = new ImageIcon(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading image: " + e.getMessage());
        }
        topCardImageLabel.setIcon(topCardImage);
    }

    /**
     * The main method to run the UNO game by creating an instance of the `UnoView` class.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new UnoView();
    }
}
