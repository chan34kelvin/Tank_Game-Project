package TankGame.Panels.ScreenPanels;

import TankGame.GameInfoConstants;
import TankGame.TankGameFrameControlCenter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ScreenPanel extends JPanel implements Runnable {
    /*
    (comments)(ScreenPanel)
        finished.
        this contains the overall layout of a screenPanel, it is a thread and a panel
        it must contain the names of buttons and destinations
        it must tell the default button height
        it must have a control center to contact with the frame
        an image and the label.
        all screenPanels have the same sources and buttons loading.
        and the paint component.
        new screenPanels must
        tell how your panel is loaded.
        tell how your displaying the message.
     */

    protected String[] namesOfButton,namesOfDestinations;
    protected int currentButtonHeight;
    protected TankGameFrameControlCenter controlCenter;
    protected String nameOfImg;
    protected BufferedImage image;
    protected JLabel jLabel;

    public abstract void initializePanel();

    public abstract void loadPanel();

    public abstract void loadMessage(String message);

    //load the source like images and its width and height
    public void loadSources(){
        try{
            image= ImageIO.read(this.getClass().getClassLoader().getResource(nameOfImg));
            currentButtonHeight= currentButtonHeight+GameInfoConstants.SCREEN_HEIGHT/6+image.getHeight();
        }catch(Exception error){
            System.out.println("ScreenPanel: "+error);
            currentButtonHeight=0;
        }
    }

    //load buttons from the name arrays
    public void loadButtons(){
        for(int i=0;i<namesOfButton.length;i++){
            JButton button= new JButton(namesOfButton[i]);
            button.setFont(new Font(GameInfoConstants.GAME_FONT,1,GameInfoConstants.GAME_FONT_SIZE));
            currentButtonHeight= currentButtonHeight+GameInfoConstants.PIXELS_BTW_BUTTONS;
            button.setBounds((GameInfoConstants.SCREEN_WIDTH/4),currentButtonHeight,GameInfoConstants.GAME_FONT_SIZE*10,GameInfoConstants.GAME_FONT_SIZE);
            String nameOfDestination= namesOfDestinations[i];
            button.addActionListener((ActionEvent->{controlCenter.switchPanels(nameOfDestination,"");}));
            this.add(button);
        }
    }

    //default panel method needed to implement, to draw the image in the center.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        g2.drawImage(image,GameInfoConstants.SCREEN_WIDTH/4,GameInfoConstants.SCREEN_HEIGHT/6,null);
    }

    //thread method to implement
    @Override
    public void run() {
        this.loadPanel();
    }
}
