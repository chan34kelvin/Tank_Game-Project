package TankGame;

import javax.swing.*;

public class TankGameFrame extends JFrame {
    /*
    (comments)(TankGameFrame)
        finished.
        this is the frame where the game is going to be displayed
        have custom title, size, and the main panel to control other panels.
     */

    //needs the name of the frame
    public TankGameFrame(String nameOfGame){
        //make gameFrame from JFrame
        this.setTitle(nameOfGame);
        //make sure JVM closes when Frame is closed.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //initialize the frame
    public void initializeGameFrame(){
        //display in the middle of the screen
        this.setLocationRelativeTo(null);
        //not resizeable
        this.setResizable(false);
        //set size, +17 because of the borders on both sides take up 10-15 pixels, +40 because the bar above takes 40 pixels.
        this.setSize(GameInfoConstants.SCREEN_WIDTH+17,GameInfoConstants.SCREEN_HEIGHT+40);
    }

    //to add a main panel to the frame
    public void setMainPanel(JPanel mainPanel){
        this.add(mainPanel);
    }

    //where the changes of the frame can be seen or not decided by the 2 methods
    public void updateVisibilityToTrue(){
        this.setVisible(true);
    }

    public void updateVisibilityToFalse(){
        this.setVisible(false);
    }

}
