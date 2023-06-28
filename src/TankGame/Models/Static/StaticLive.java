package TankGame.Models.Static;

import TankGame.Models.Moveable.Moveable;
import TankGame.Models.Moveable.MoveableTank;

import java.awt.*;

public class StaticLive extends Static{
    /*
    (comments)(DisplayObject)(Static)(StaticLive)
        finished.
        always update the position of the object along with its tank.
        no need to check for collision.
     */

    private int pos;

    public StaticLive(MoveableTank tankObject){
        super.toCheck= false;
        this.initializeTankGameModel(0,0,"heart.png");
        this.loadObjectImage();
        this.loadObjectHitBox();
    }

    public void updatePos(MoveableTank tankObject, int pos){
        super.objectX= tankObject.getObjectX();
        super.objectY= tankObject.getObjectY();
        this.pos= pos;
    }

    @Override
    public boolean loadCollisionEffect(Moveable moveable, String key) {
        //no effects needed.
        return false;
    }

    @Override
    public void drawImage(Graphics2D graphics) {
        graphics.drawImage(objectImage,(objectX)+(objectWidth*pos),objectY,null);
    }
}
