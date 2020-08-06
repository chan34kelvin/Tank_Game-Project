package TankGame.DisplayObjects.Static;

import TankGame.DisplayObjects.Moveable.Moveable;

public class StaticBackground extends Static {
    /*
    (comments)(DisplayObject)(Static)(StaticBackground)
        finished.
        this is a static DisplayObject background
        defines the image for background and always no collision
     */

    public StaticBackground(int x, int y){
        this.initializeDisplayObject(x,y,"Background.bmp");
        this.loadObjectImage();
        this.loadObjectHitBox();
    }

    @Override
    public boolean loadCollisionEffect(Moveable moveable, String key) {
        return false;
    }
}
