package TankGame.Panels.ScreenPanels;

import TankGame.GameInfoConstants;
import TankGame.TankGameFrameControlCenter;

import javax.swing.*;
import java.awt.*;

public class EndScreenPanel extends ScreenPanel{
    /*
    (comments)(ScreenPanel)(EndScreenPanel)
        finished.
        the end screen display, a ScreenPanel.
        it has a control center to contact the frame
        and the elements an end screen needs
        a custom loadMessage method.
     */

    public EndScreenPanel(TankGameFrameControlCenter controlCenter){
        super.controlCenter=controlCenter;
    }

    @Override
    public void initializePanel() {
        super.namesOfButton= new String[]{"restart game"};
        super.namesOfDestinations= new String[]{"play"};
        super.nameOfImg= "Title.bmp";
        this.setBackground(Color.decode(GameInfoConstants.BACKGROUND_COLOR));
        this.setLayout(null);
    }

    @Override
    public void loadPanel() {
        System.out.println("EndScreenPanel activated");
    }

    //implement from ScreenPanel, need message from the user for display purpose.
    @Override
    public void loadMessage(String message) {
        //checks if the previous label exist, if yes remove it
        if(jLabel!=null)
            this.remove(jLabel);
        //make a new label with the message.
        jLabel= new JLabel(message);
        //give the location of the label.
        jLabel.setBounds(GameInfoConstants.SCREEN_WIDTH/3-80,GameInfoConstants.SCREEN_HEIGHT/2,0,0);
        jLabel.setSize(1000,250);
        //font of the label and the size.
        jLabel.setFont(new Font(GameInfoConstants.GAME_FONT,Font.CENTER_BASELINE,50));
        //add the label to the panel.
        this.add(jLabel);
    }

}
