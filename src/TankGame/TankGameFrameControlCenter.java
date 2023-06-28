package TankGame;

import TankGame.Models.Static.StaticObjects;
import TankGame.Panels.MainPanel;
import TankGame.Panels.ScreenPanels.EndScreenPanel;
import TankGame.Panels.ScreenPanels.GameExecutionPanel;
import TankGame.Panels.ScreenPanels.ScreenPanel;
import TankGame.Panels.ScreenPanels.StartScreenPanel;
import TankGame.Models.Moveable.MoveableTank;
import TankGame.TankPlayerControls.TankPlayerControls;

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

    //models
    private StaticObjects staticObjects;
    private MoveableTank tankPlayerOne, tankPlayerTwo;

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
        staticObjects= new StaticObjects();
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

    //load the two tank players, tell the controls needed and add it to frame.
    public void loadTankPlayers(){
        tankPlayerOne= new MoveableTank();
        tankPlayerTwo= new MoveableTank();
        staticObjects.loadLayout();
        staticObjects.loadObjects();
        tankPlayerOne.initializeMoveableTank(2, staticObjects, tankPlayerTwo);
        tankPlayerTwo.initializeMoveableTank(1, staticObjects, tankPlayerOne);
        this.addKeyListenersToFrame(new TankPlayerControls(1, tankPlayerOne));
        this.addKeyListenersToFrame(new TankPlayerControls(2, tankPlayerTwo));
    }

    public boolean updateTankPlayers(){
        try{
            this.tankPlayerOne.update();
            this.tankPlayerTwo.update();
            return true;
        }catch(Exception error){
            System.out.println(error.getMessage());
            this.switchPanels("end",error.getMessage());
            return false;
        }
    }

    public MoveableTank getTankPlayerOne() {
        return tankPlayerOne;
    }

    public MoveableTank getTankPlayerTwo() {
        return tankPlayerTwo;
    }

    public StaticObjects getStaticObjects(){
        return staticObjects;
    }
}
