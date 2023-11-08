package src;

import javax.swing.*;
import javax.swing.text.html.ListView;
import java.awt.*;

public class UnoGameGUI extends JFrame{
    private MenuBar newMenu;
    private static final String numOfPlayersValues[] = {"2","3","4"};
    private GridLayout firstLayout;
    private UnoGameGUI frame;
    private JComboBox playerSelection;
    private JButton OKButton;
    private JButton CancelButton;

    public void setDropDownMenus(){
        playerSelection = new JComboBox(numOfPlayersValues);
    }

    public UnoGameGUI(String name){
        super(name);
        firstLayout = new GridLayout(3,2);
        setResizable(false);
    }

    public void addComponentsToPanel(){
        newMenu = new MenuBar();
        Container pane = this.getContentPane();
        final JPanel components = new JPanel();
        components.setLayout(firstLayout);
        JPanel controls = new JPanel();
        setDropDownMenus();
        controls.add(new Label("Choose the number of players: "));
        controls.add(playerSelection);
        controls.add(new Label("            "));
        OKButton = new JButton("OK");
        CancelButton = new JButton("Cancel");
        controls.add(OKButton);
        controls.add(CancelButton);
        this.add(controls);
        pane.add(components, BorderLayout.CENTER);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.CENTER);
    }
    public void showGrid(){
        frame = new UnoGameGUI("Select Players");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 150);
        frame.addComponentsToPanel();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public UnoGameGUI() {
    }
    public static void main(String[] args) {
        UnoGameGUI unoGameGUI = new UnoGameGUI();
        unoGameGUI.showGrid();

    }
}
