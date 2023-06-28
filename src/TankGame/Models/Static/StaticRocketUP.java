package TankGame.Models.Static;

import TankGame.Models.Moveable.Moveable;
import TankGame.Models.Moveable.MoveableTank;

public class StaticRocketUP extends Static{
    /*
    (comments)(DisplayObject)(Static)(StaticRocketUP)
        finished.
        a static display object power up for rocket
        it must be checked.
        if it collides with a tank, activate the upgrade from bullet to rocket
        if it collides a bullet, nothing happens.
     */

    public StaticRocketUP(int x, int y){
        super.toCheck= true;
        this.initializeTankGameModel(x,y,"Pickup.gif");
        this.loadObjectImage();
        this.loadObjectHitBox();
    }

    //needs the moveable object to load effects and the key to specify the moveable object
    @Override
    public boolean loadCollisionEffect(Moveable moveable, String key) {
        switch(key){
            case "tank":
                super.nameOfResource= "Background.bmp";
                this.loadObjectImage();
                super.toCheck=false;
                ((MoveableTank)moveable).setShootRockets();
                return false;
            default:
                return false;
        }
    }
}
