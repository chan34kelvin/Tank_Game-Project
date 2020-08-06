package TankGame.DisplayObjects.Moveable;

import TankGame.DisplayObjects.DisplayObject;
import TankGame.DisplayObjects.Static.Static;
import TankGame.DisplayObjects.Static.StaticObjects;

import java.awt.*;

public abstract class Moveable extends DisplayObject {
    /*
    (comments)(DisplayObject)(Moveable)
        finished.
        the layout of a moveable
        must have the addVelocity X and Y, its target
        objectSpeed, angle, hitBox and the key
        and the loaded static objects for them to interact with
        must have a moveObject, checkCollision and fixedPos method.
        must define how to update the moveable.
     */

    protected int addVelocityX, addVelocityY;
    protected double objectSpeed;
    protected float objectAngle;
    protected MoveableTank tankOpponent;
    protected String objectKey;
    protected StaticObjects staticObjects;

    //initialize methods to be a moveable
    public void initializeMoveablePart1(double objectSpeed, float objectAngle, MoveableTank tankOpponent){
        this.objectSpeed= objectSpeed;
        this.objectAngle= objectAngle;
        this.tankOpponent= tankOpponent;
    }

    public void initializeMoveablePart2(String objectKey, StaticObjects staticObjects){
        this.objectKey= objectKey;
        this.staticObjects= staticObjects;
    }

    //it must provide the direction the object is moving to decide whether to + or -
    //addVelocityX and Y for the updated positions
    //update the hitBox
    protected void moveObject(String direction){
        addVelocityX= ((int) Math.round(objectSpeed* Math.cos((Math.toRadians(objectAngle)))));
        addVelocityY= ((int) Math.round(objectSpeed* Math.sin((Math.toRadians(objectAngle)))));
        switch(direction){
            case "-":
                addVelocityX= objectX- addVelocityX;
                addVelocityY= objectY- addVelocityY;
                break;
            default:
                addVelocityX=  objectX+addVelocityX;
                addVelocityY=  objectY+addVelocityY;
        }
        objectHitBox.setBounds(addVelocityX,addVelocityY,objectWidth,objectHeight);
    }

    //check if it collides with any static objects
    //change the hitBox with the changed positions and adjust it for a tank collision
    //return the status of collision to fixedPos by loading the effects.
    protected void checkCollision(){
        Static object= staticObjects.ifCollide(this.objectHitBox);
        objectHitBox.setBounds(addVelocityX-15,addVelocityY-15,(objectWidth*2),(objectHeight*2));
        if(object!=null){
            this.fixedPos(object.loadCollisionEffect(this,objectKey));
        }else if(tankOpponent.getObjectHitBox().intersects(objectHitBox)){
            this.fixedPos(this.loadCollisionEffect(tankOpponent,objectKey));
        }else {
            this.fixedPos(false);
        }
    }

    //if is true, just use the same x and y. otherwise update.
    protected void fixedPos(boolean status){
        if(status) {
            objectHitBox.setBounds(objectX, objectY, objectWidth, objectHeight);
        }else{
            objectX= addVelocityX;
            objectY= addVelocityY;
        }
    }

    public abstract void update() throws Exception;

    @Override
    public void loadObjectHitBox() {
        objectHitBox= new Rectangle(objectX,objectY,objectWidth,objectHeight);
    }
}
