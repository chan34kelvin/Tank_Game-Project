package TankGame.Panels;

import TankGame.Panels.ScreenPanels.ScreenPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainPanel extends JPanel{
    /*
    (comments)(MainPanel)
        finished.
        the panel to control all other panels in the game.
        needs a cardLayout for swapping panels.
        a hashmap to record existing panels.
        and a function to allow users to switch panels.
     */

    private CardLayout swappingTool;
    private HashMap<String,ScreenPanel> screenPanelHashMap;

    public MainPanel(){
        //make a cardLayout
        this.setLayout(swappingTool= new CardLayout());
        //a new hashmap to store panel information
        this.screenPanelHashMap = new HashMap<String,ScreenPanel>();
    }

    //a function to add any new panels into the main panel, need the panel and the name of the panel
    public void addPanelsIntoMainPanel(ScreenPanel panel, String name){
        //record the information into map
        screenPanelHashMap.put(name,panel);
        //add the panel into the main panel
        this.add(panel,name);
    }

    //user to switch the panel and returns the switching panel, need the name of the panel.
    public ScreenPanel switchPanels(String nameOfPanel){
        //ask the cardLayout tool to show the needed panel
        this.swappingTool.show(this,nameOfPanel);
        //ask the map to return the screen panel to control center
        return screenPanelHashMap.get(nameOfPanel);
    }
}
