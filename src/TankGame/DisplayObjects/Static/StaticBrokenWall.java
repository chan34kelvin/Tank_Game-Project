package TankGame.DisplayObjects.Static;

import TankGame.DisplayObjects.Moveable.Moveable;
import TankGame.DisplayObjects.Moveable.MoveableBullet;

public class StaticBrokenWall extends Static{
    /*
    (comments)(DisplayObject)(Static)(StaticBrokenWall)
        finished.
        this is a static DisplayObject broken wall
        need to check for collision
        if a tank collides, no changes
        if a bullet collides, just replace the image with background and set to check to false
     */

    public StaticBrokenWall(int x, int y){
        super.toCheck= true;
        this.initializeDisplayObject(x,y,"Wall2.gif");
        this.loadObjectImage();
        this.loadObjectHitBox();
    }

    //takes a moveable object and the key of the moveable object
    //if is a tank, apply affects for tank, bullet otherwise.
    @Override
    public boolean loadCollisionEffect(Moveable moveable, String key) {
        switch(key){
            case "tank":
                return true;
            default:
                super.nameOfResource= "Background.bmp";
                this.loadObjectImage();
                super.toCheck=false;
                MoveableBullet bullet= (MoveableBullet)moveable;
                bullet.setDestroyed(true);
                return true;
        }
    }
}
