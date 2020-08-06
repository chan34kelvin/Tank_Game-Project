package TankGame.Panels.ScreenPanels;

import TankGame.GameInfoConstants;
import TankGame.TankGameFrameControlCenter;

import javax.swing.*;
import java.awt.*;

public class StartScreenPanel extends ScreenPanel{
    /*
    (comments)(ScreenPanel)(StartScreenPanel)
        finished.
        this is the panel displaying starting screen, a ScreenPanel
        it has a control center to contact with the frame.
        the needed elements to display in the start screen.
        a custom loadMessage method.
     */

    public StartScreenPanel(TankGameFrameControlCenter controlCenter){
        super.controlCenter= controlCenter;
    }

    //implement methods from the ScreenPanel
    @Override
    public void initializePanel() {
        super.namesOfButton= new String[]{"start game"};
        super.namesOfDestinations= new String[]{"play"};
        super.nameOfImg= "Title.bmp";
        this.setBackground(Color.decode(GameInfoConstants.BACKGROUND_COLOR));
        this.setLayout(null);
    }

    @Override
    public void loadPanel() {
        System.out.println("StartScreenPanel activated");
    }

    //needs a message from the user and load it.
    @Override
    public void loadMessage(String message) {
        //checks if the previous label exist, if does remove it.
        if(jLabel!=null)
            this.remove(jLabel);
        //make a new label with the message.
        jLabel= new JLabel(message);
        //set where the label is going to appear.
        jLabel.setBounds(GameInfoConstants.SCREEN_WIDTH/7,GameInfoConstants.SCREEN_HEIGHT/2,0,0);
        jLabel.setSize(1000,280);
        //the font of the label and appearance.
        jLabel.setFont(new Font(GameInfoConstants.GAME_FONT,Font.CENTER_BASELINE,30));
        //add the label to the panel.
        this.add(jLabel);
    }
}
