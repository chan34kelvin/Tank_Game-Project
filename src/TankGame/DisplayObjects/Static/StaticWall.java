package TankGame.DisplayObjects.Static;

import TankGame.DisplayObjects.Moveable.Moveable;
import TankGame.DisplayObjects.Moveable.MoveableBullet;

import java.awt.*;

public class StaticWall extends Static {
    /*
    (comments)(DisplayObject)(Static)(StaticWall)
        finished.
        a static display object wall
        must check collision and always return the effect of true to prevent any moveable to continue.
     */

    public StaticWall(int x, int y){
        super.toCheck= true;
        this.initializeDisplayObject(x,y,"Wall1.gif");
        this.loadObjectImage();
        this.loadObjectHitBox();
    }

    @Override
    public boolean loadCollisionEffect(Moveable moveable, String key) {
        switch(key){
            case "tank":
                return true;
            default:
                ((MoveableBullet) moveable).setDestroyed(true);
                return true;
        }
    }

    @Override
    public void paintObjectHitBox(Graphics2D graphics) {
        graphics.setColor(Color.pink);
        graphics.fillRect(modifiedX,modifiedY,modifiedWidth,modifiedHeight);
    }
}
