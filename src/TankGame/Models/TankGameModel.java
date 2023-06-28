package TankGame.Models;

import TankGame.Models.Moveable.Moveable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class TankGameModel {
    /*
    (comments)(DisplayObject)
        finished.
        this is the overall layout of any display object.
        it has a x,y,width and height
        a hitBox, and an image.
        can loadObjectImage, return x,y,width and height
        must define how to make a hitBox, collisionEffects and painting the object/debug
     */

    protected int objectX, objectY, objectWidth, objectHeight;
    protected String nameOfResource;
    protected Rectangle objectHitBox;
    protected BufferedImage objectImage;

    public void initializeTankGameModel(int objectX, int objectY, String nameOfResource){
        this.objectX= objectX;
        this.objectY= objectY;
        this.nameOfResource= nameOfResource;
    }

    public void loadObjectImage(){
        try{
            objectImage= ImageIO.read(this.getClass().getClassLoader().getResource(nameOfResource));
            objectWidth= objectImage.getWidth();
            objectHeight= objectImage.getHeight();
        }catch(Exception error){
            System.out.println("DisplayObject :"+objectX+"|"+objectY+" "+error);
        }
    }

    //get methods for x,y,hitBox,width and height
    public int getObjectX(){
        return objectX;
    }

    public int getObjectY(){
        return objectY;
    }

    public int getObjectWidth(){
        return objectWidth;
    }

    public int getObjectHeight(){
        return objectHeight;
    }

    public Rectangle getObjectHitBox(){
        return objectHitBox;
    }

    //abstract methods to be a displayObject
    public abstract void loadObjectHitBox();

    public abstract boolean loadCollisionEffect(Moveable moveable, String key);

    public abstract void drawImage(Graphics2D graphics);

    public abstract void paintObjectHitBox(Graphics2D graphics);
}
