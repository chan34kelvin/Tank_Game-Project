package TankGame.Models.Static;

import TankGame.Models.Moveable.Moveable;
import TankGame.Models.Moveable.MoveableTank;

public class StaticSheildUP extends Static{
    /*
    (comments)(DisplayObject)(Static)(StaticSheildUP)
        finished.
        this is a static display object sheild power up
        once it collides with a tank, just upgrade the reduce damage.
        if is a bullet, nothing happens.
     */

    public StaticSheildUP(int x, int y){
        super.toCheck= true;
        this.initializeTankGameModel(x,y,"Shield2.gif");
        this.loadObjectImage();
        this.loadObjectHitBox();
    }

    //gets the moveable object for the effects, and the key to specify what kind of moveable object it's colliding with.
    @Override
    public boolean loadCollisionEffect(Moveable moveable, String key) {
        switch(key){
            case "tank":
                super.nameOfResource= "Background.bmp";
                this.loadObjectImage();
                super.toCheck=false;
                ((MoveableTank)moveable).setReduceDamage();
                return false;
            default:
                return false;
        }
    }
}
