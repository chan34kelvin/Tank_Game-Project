package TankGame.Models.Static;

import TankGame.Models.TankGameModel;

import java.awt.*;

public abstract class Static extends TankGameModel {
    /*
    (comments)(DisplayObject)(Static)
        finished.
        a static DisplayObject with static elements.
        it must tell if the object needs to be check for collision
        its modified x,y,width and height for static objects
        static hitBox for detecting collision
        debug paint method for displaying the hitBox and the object.
     */

    private final int modifiedBy= 0;
    protected boolean toCheck;
    protected int modifiedX;
    protected int modifiedY;
    protected int modifiedWidth;
    protected int modifiedHeight;

    public boolean getToCheck(){
        return toCheck;
    }

    //a modified hitBox to make the hitBox relate to the size of the image.
    @Override
    public void loadObjectHitBox() {
        modifiedX= (objectX*objectWidth)+modifiedBy;
        modifiedY= (objectY*objectHeight)+modifiedBy;
        modifiedWidth= (objectWidth);
        modifiedHeight= (objectHeight);
        objectHitBox= new Rectangle(modifiedX,modifiedY,modifiedWidth,modifiedHeight);
    }

    @Override
    public void drawImage(Graphics2D graphics) {
        graphics.drawImage(objectImage,objectX*objectWidth,objectY*objectHeight,null);
        //this.paintObjectHitBox(graphics);
    }

    @Override
    public void paintObjectHitBox(Graphics2D graphics) {
        graphics.setColor(Color.red);
        graphics.drawRect(modifiedX,modifiedY,modifiedWidth,modifiedHeight);
    }
}
