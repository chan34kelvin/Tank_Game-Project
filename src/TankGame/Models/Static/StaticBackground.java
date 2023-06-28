package TankGame.Models.Static;

import TankGame.Models.Moveable.Moveable;

public class StaticBackground extends Static {
    /*
    (comments)(DisplayObject)(Static)(StaticBackground)
        finished.
        this is a static DisplayObject background
        defines the image for background and always no collision
     */

    public StaticBackground(int x, int y){
        this.initializeTankGameModel(x,y,"Background.bmp");
        this.loadObjectImage();
        this.loadObjectHitBox();
    }

    @Override
    public boolean loadCollisionEffect(Moveable moveable, String key) {
        return false;
    }
}
