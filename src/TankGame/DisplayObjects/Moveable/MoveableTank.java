package TankGame.DisplayObjects.Moveable;

import TankGame.DisplayObjects.Static.StaticLive;
import TankGame.DisplayObjects.Static.StaticObjects;
import TankGame.GameInfoConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class MoveableTank extends Moveable{
    /*
    (comments)(DisplayObject)(Moveable)(MoveableTank)
        finished.
        a moveable display object tank
        contains option 1 or 2 for player to choose its tank and controls
        option to move back, forward, left, right and shoot
        indicate the health, damage, lives, rocket limit, rotation speed
        the player's option and the ticks to shoot bullets.
        use arrayList to store the live and bullet objects
     */

    //player options to choose
    private final int [] tankOption1Ints= {100,100,0};
    private final String tankOption1Str= "tank1.png";
    private final int [] tankOption2Ints= {GameInfoConstants.GAME_SCREEN_WIDTH-100,GameInfoConstants.GAME_SCREEN_HEIGHT-100,180};
    private final String tankOption2Str= "tank2.png";
    private int [] tankOptionInts;
    private String tankOptionStr;
    private int option;

    //reduce the hitBox to fit through the gaps smoother
    private double modifiedBy=3;

    private boolean moveForward,moveBackward,turnLeftAngle,turnRightAngle,shootBullets,shootRockets;
    private final int totalHealth= 100;
    private int totalLives= 3, limitOfRockets=5;
    private double percentOfHealth= 1;
    private final float rotationSpeed= 2.0f;
    private double reduceDamage= 0;
    private long ticks= 0;
    private ArrayList<MoveableBullet> moveableBullets;
    private final StaticLive [] staticLives= {new StaticLive(this),new StaticLive(this),new StaticLive(this)};

    public void initializeMoveableTank(int option, StaticObjects staticObjects, MoveableTank tankOpponent){
        switch(this.option=option){
            case 1:
                tankOptionInts= tankOption1Ints;
                tankOptionStr= tankOption1Str;
                break;
            default:
                tankOptionInts= tankOption2Ints;
                tankOptionStr= tankOption2Str;
        }
        this.initializeDisplayObject(tankOptionInts[0],tankOptionInts[1],tankOptionStr);
        this.loadObjectImage();
        super.objectWidth-= objectWidth/modifiedBy;
        super.objectHeight-= objectHeight/modifiedBy;
        this.loadObjectHitBox();
        this.initializeMoveablePart1(2,tankOptionInts[2],tankOpponent);
        this.initializeMoveablePart2("tank",staticObjects);
        moveableBullets= new ArrayList<MoveableBullet>();
    }

    //set methods to change the tank's behaviour
    public void setMoveForward(boolean status){
        moveForward= status;
    }

    public void setMoveBackward(boolean status){
        moveBackward= status;
    }

    public void setTurnLeftAngle(boolean status){
        turnLeftAngle= status;
    }

    public void setTurnRightAngle(boolean status){
        turnRightAngle= status;
    }

    public void setShootBullets(boolean status){
        shootBullets= status;
    }

    public void setShootRockets(){
        shootRockets=true;
    }

    //check if the reduce damage is higher than the bullet damage's - 0.1. if is stop reducing.
    //activate through shield pick up.
    public void setReduceDamage(){
        reduceDamage=(reduceDamage<0.39)?reduceDamage+0.013:reduceDamage+0;
    }

    //activate when bullet hits the tank, the bullet will decide how much damage to dealt
    //must check if is lower then percentHealth, or else decrease its live count until 0.
    //otherwise, just subtract the damage from the percent health.
    public void setDamage(double damage){
        if(percentOfHealth<0) {
            percentOfHealth = 1;
            totalLives--;
        } else
            percentOfHealth-= (damage-reduceDamage);
    }

    //get methods to allow objects with tank to know its status on different booleans
    public boolean getShootRockets(){
        return shootRockets;
    }

    //gets the display x and y so the split screens can display the tank in the middle.
    //need to subtract the x by screen w/4 so the tank can be in the middle. for y divide by 2.
    public int getDisplayObjectX(){
        int displayObjectX= objectX-(GameInfoConstants.SCREEN_WIDTH/4);
        return (displayObjectX<0)?0:displayObjectX;
    }

    public int getDisplayObjectY(){
        int displayObjectY= objectY-GameInfoConstants.SCREEN_HEIGHT/2;
        return (displayObjectY<0)?0:displayObjectY;
    }

    //private methods only tank can use
    private void turnLeftAngle(){
        objectAngle-=rotationSpeed;
    }

    private void turnRightAngle(){
        objectAngle+=rotationSpeed;
    }

    //delay every bullet by 8 ticks, check if is a rocket, if is then give a limit of 5 rockets only
    private void shootBullet(){
        int delaySecs= 8;
        if(ticks%delaySecs==0){
            if(shootRockets){
                if(limitOfRockets<=0){
                    limitOfRockets=5;
                    shootRockets=false;
                }else{
                    limitOfRockets--;
                }
            }
            MoveableBullet bullet= new MoveableBullet(this);
            moveableBullets.add(bullet);
        }
    }

    //display the bullets and update their positions, detect if is destroyed,
    // if is then store the position and delete it after drawing.
    private void paintBullets(Graphics2D graphics){
        try{
            ArrayList<Integer> deletion= new ArrayList<>();
            for(int i=0;i<moveableBullets.size();i++){
                MoveableBullet bullet= moveableBullets.get(i);
                if(bullet.isDestroyed())
                    deletion.add(i);
                bullet.update();
                bullet.drawImage(graphics);
            }
            for(int i:deletion){
                moveableBullets.remove(i);
            }
        }catch(Exception error){
            System.out.println("Bullet failed to delete");
        }
    }

    //draw lives and update its position with tank's current position.
    //make a rectangle and put it 10 pixels above the tank, indicating the health.
    private void paintLives(Graphics2D graphics){
        for(int pos=0;pos<totalLives;pos++){
            staticLives[pos].updatePos(this, pos);
            staticLives[pos].drawImage(graphics);
        }
        graphics.fillRect(objectX,objectY-10,(int)(totalHealth*percentOfHealth),10);
        graphics.setColor(Color.green);
    }

    private boolean checkAlive(){
        return totalLives>0;
    }

    @Override
    public void update() throws Exception{
        ticks+=1;
        if(moveForward){
            this.moveObject("+");
            this.checkCollision();
        }if(moveBackward){
            this.moveObject("-");
            this.checkCollision();
        }if(turnLeftAngle){
            this.turnLeftAngle();
        }if(turnRightAngle){
            this.turnRightAngle();
        }if(shootBullets){
            this.shootBullet();
        }if(!checkAlive()){
            throw new Exception("Tank "+option+": defeated");
        }
    }

    @Override
    public boolean loadCollisionEffect(Moveable moveable, String key) {
        return key.equals("tank");
    }

    //adjust the x and y so the hitBox and the image matches
    //divide the width and height by modified in half so it rotates correctly
    @Override
    public void drawImage(Graphics2D graphics) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(objectX-4, objectY-4);
        rotation.rotate(Math.toRadians(objectAngle), objectWidth/(modifiedBy/2), objectHeight/(modifiedBy/2));
        graphics.drawImage(objectImage,rotation,null);
        this.paintBullets(graphics);
        this.paintLives(graphics);
        //this.paintObjectHitBox(graphics);
    }

    @Override
    public void paintObjectHitBox(Graphics2D graphics) {
        graphics.drawRect(addVelocityX-15,addVelocityY-15,(objectWidth*2),(objectHeight*2));
    }
}
