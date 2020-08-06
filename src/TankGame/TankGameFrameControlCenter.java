package TankGame;

import TankGame.Panels.MainPanel;
import TankGame.Panels.ScreenPanels.EndScreenPanel;
import TankGame.Panels.ScreenPanels.GameExecutionPanel;
import TankGame.Panels.ScreenPanels.ScreenPanel;
import TankGame.Panels.ScreenPanels.StartScreenPanel;

import java.awt.event.KeyListener;

public class TankGameFrameControlCenter{
    /*
    (comments)(TankGameFrameControlCenter)
        finished.
        a control center to manipulate the actions between the frame and the panels
        a place where you make new panels and add the to the frame
        where you can be asked to change the panel for you.
     */

    private TankGameFrame gameFrame;
    private MainPanel mainPanel;
    private ScreenPanel currentPanel;

    //method to initialize the game
    public void initializeTankGame(){
        //make a game frame object
        gameFrame= new TankGameFrame(GameInfoConstants.NAME); //window to play tank game.
        gameFrame.initializeGameFrame();
        //make a main panel
        mainPanel= new MainPanel();
        //add the sub panels into main panel
        mainPanel.addPanelsIntoMainPanel(new StartScreenPanel(this),"start");
        mainPanel.addPanelsIntoMainPanel(new GameExecutionPanel(this),"play");
        mainPanel.addPanelsIntoMainPanel(new EndScreenPanel(this),"end");
        //now add the main panel into frame
        gameFrame.setMainPanel(mainPanel);
    }

    //method to switch panels, needs the name of the panel to switch and the message wanted to display to user.
    public void switchPanels(String name, String message){
        gameFrame.updateVisibilityToFalse(); // lock current frame
        //tell the main panel to find your selected panel
        currentPanel= mainPanel.switchPanels(name);
        //unlock the frame
        gameFrame.updateVisibilityToTrue();
        //now initialize this panel
        currentPanel.initializePanel();
        //load the resources
        currentPanel.loadSources();
        currentPanel.loadButtons();
        currentPanel.loadMessage(message);
        //make sure to repaint so it displays correctly
        currentPanel.repaint();
        //launch the panel
        (new Thread(this.currentPanel)).start();
    }

    //method to add tank controls to the frame, takes a keyListener.
    public void addKeyListenersToFrame(KeyListener key){
        gameFrame.addKeyListener(key);
    }
}
