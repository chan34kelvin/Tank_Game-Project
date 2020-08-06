package TankGame;

public class TankGameMain {
    /*
    (comments)(TankGameMain)
        finished.
        initialize the game,
        run the game.
     */
    public static void main(String [] args){
        //make a control center that can control the JFrame
        TankGameFrameControlCenter controlCenter= new TankGameFrameControlCenter();
        //initialize the tank game and set up
        controlCenter.initializeTankGame();
        //tell the control center to switch to start panel, a message telling user about some potential situations of the game.
        controlCenter.switchPanels("start", "Loading game takes 1 min, Please be patient");
    }
}
