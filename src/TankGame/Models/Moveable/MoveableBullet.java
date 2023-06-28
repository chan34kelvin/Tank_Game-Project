package TankGame.Models.Moveable;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class MoveableBullet extends Moveable{
    /*
    (comments)(DisplayObject)(Moveable)(MoveableBullet)
        finished.
        a moveable display object bullet
        must define if is destroyed, a rocket and its damage.
        access if is destroyed, or change its status.
        update the bullet's pos and check for collision
        load any collision effects with the moveable object if collided.
     */

    private boolean isDestroyed;
    private boolean isRocket;
    private final int modifiedBy= 0;
    private double damage;

    public MoveableBullet(MoveableTank tankObject){
        this.isRocket= tankObject.getShootRockets();
        this.initializeTankGameModel((tankObject.getObjectX())+modifiedBy,(tankObject.getObjectY())+modifiedBy,this.getResource());
        this.loadObjectImage();
        this.loadObjectHitBox();
        this.initializeMoveablePart1(4, tankObject.objectAngle, tankObject.tankOpponent);
        this.initializeMoveablePart2("bullet", tankObject.staticObjects);
    }

    public void setDestroyed(boolean status){
        isDestroyed= status;
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

    //always goes forward
    @Override
    public void update() {
        this.moveObject("+");
        this.checkCollision();
    }

    //if is a tank, make sure isDestroy is set to true and does nothing
    //if is a bullet, deal damage to the tank opponent.
    @Override
    public boolean loadCollisionEffect(Moveable moveable, String key) {
        switch(key){
            case "tank":
                isDestroyed=true;
                return true;
            default:
                isDestroyed=true;
                MoveableTank tankObject= (MoveableTank)moveable;
                tankObject.setDamage(damage);
                return true;
        }
    }

    //decide either rocket or bullet and its damage
    private String getResource(){
        if(isRocket) {
            damage= 0.1;
            return "Rocket.png";
        }
        damage= 0.05;
        return "Shell.png";
    }

    @Override
    public void drawImage(Graphics2D graphics) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(objectX, objectY);
        rotation.rotate(Math.toRadians(objectAngle), objectWidth / 2.0, objectHeight / 2.0);
        graphics.drawImage(objectImage, rotation, null);
        //this.paintObjectHitBox(graphics);
    }

    @Override
    public void paintObjectHitBox(Graphics2D graphics) {
        graphics.setColor(Color.orange);
        graphics.drawRect(objectX,objectY,objectWidth,objectHeight);
    }
}
