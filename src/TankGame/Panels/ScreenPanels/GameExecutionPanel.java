package TankGame.Panels.ScreenPanels;

//import TankGame.DisplayObjects.Static.StaticObjects;
import TankGame.GameMapDisplay;
import TankGame.TankGameFrameControlCenter;
//import TankGame.TankPlayerControls.TankPlayerControls;
//import TankGame.DisplayObjects.Moveable.MoveableTank;

import java.awt.*;

public class GameExecutionPanel extends ScreenPanel{
    /*
    (comments)(ScreenPanel)(GameExecutionPanel)
        finished.
        this is the where the game is displayed, is a ScreenPanel.
        it has a control center that contacts with the frame.
        static Objects, gameMap Display and the tankPlayers.
        has its own loadSource, loadPanel, and paintComponent
        a private method to load the tank players
     */

    private TankGameFrameControlCenter controlCenter;
//    private StaticObjects staticObjects;
    private GameMapDisplay gameMapDisplay;
//    private MoveableTank tankPlayerOne, tankPlayerTwo;

    public GameExecutionPanel(TankGameFrameControlCenter controlCenter){
        this.controlCenter=controlCenter;
    }

    //implementing methods to be a ScreenPanel
    @Override
    public void initializePanel() {
        super.namesOfButton= new String[]{};
//        staticObjects= new StaticObjects();
        gameMapDisplay= new GameMapDisplay();
    }

    //load the map and static objects along with two tank players.
    @Override
    public void loadSources() {
        System.out.println("Loading... need one minute to load");
//        staticObjects.loadLayout();
//        staticObjects.loadObjects();
        controlCenter.loadTankPlayers();
    }

    //where there's a loop to update the status of the game until theres an exception caused by the tank player.
    @Override
    public void loadPanel() {

        while(true) {
            if(!controlCenter.updateTankPlayers()){
                break;
            }
            gameMapDisplay.loadObjectImage(controlCenter.getStaticObjects(), controlCenter.getTankPlayerOne(), controlCenter.getTankPlayerTwo());
            this.repaint();
            try{
                Thread.sleep(1000 / 144);
            }catch (Exception error){
                System.out.println(error);
                controlCenter.switchPanels("end", error.getMessage());
                break;
            }
        }
//        try{
//            while(true) {
//                MoveableTank tankPlayerOne = controlCenter.getTankPlayerOne();
//                MoveableTank tankPlayerTwo = controlCenter.getTankPlayerTwo();
//                //to make try catch a little easier, going do updates here.
//                tankPlayerOne.update();
//                tankPlayerTwo.update();
//                gameMapDisplay.loadObjectImage(controlCenter.getStaticObjects(), tankPlayerOne, tankPlayerTwo);
//                this.repaint();
////                Thread.sleep(1000/144);
//            }
//        }catch(Exception error){
//            System.out.println(error.getMessage());
//            controlCenter.switchPanels("end",error.getMessage());
//        }
    }

    @Override
    public void loadMessage(String message) {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        gameMapDisplay.drawImage(g2);
    }

    //load the two tank players, tell the controls needed and add it to frame.
//    private void loadTankPlayers(){
//        tankPlayerOne= new MoveableTank();
//        tankPlayerTwo= new MoveableTank();
//        tankPlayerOne.initializeMoveableTank(2, staticObjects, tankPlayerTwo);
//        tankPlayerTwo.initializeMoveableTank(1, staticObjects, tankPlayerOne);
//        controlCenter.addKeyListenersToFrame(new TankPlayerControls(1, tankPlayerOne));
//        controlCenter.addKeyListenersToFrame(new TankPlayerControls(2, tankPlayerTwo));
//    }
}
