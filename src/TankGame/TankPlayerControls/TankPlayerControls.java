package TankGame.TankPlayerControls;

import TankGame.DisplayObjects.Moveable.MoveableTank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankPlayerControls implements KeyListener {

    private final int [] controlsChoiceOne = {KeyEvent.VK_UP,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_ENTER};
    private final int [] controlsChoiceTwo = {KeyEvent.VK_W,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_SPACE};
    private int [] controls;
    private MoveableTank tankPlayer;

    public TankPlayerControls(int playerChoice, MoveableTank tankPlayer){
        this.tankPlayer=tankPlayer;
        switch (playerChoice){
            case 1:
                controls= controlsChoiceOne;
                break;
            default:
                controls= controlsChoiceTwo;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed= e.getKeyCode();
        if(keyPressed==controls[0]){
            tankPlayer.setMoveForward(true);
        }if(keyPressed==controls[1]){
            tankPlayer.setMoveBackward(true);
        }if(keyPressed==controls[2]){
            tankPlayer.setTurnLeftAngle(true);
        }if(keyPressed==controls[3]){
            tankPlayer.setTurnRightAngle(true);
        }if(keyPressed==controls[4]){
            tankPlayer.setShootBullets(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyReleased= e.getKeyCode();
        if(keyReleased==controls[0]){
            tankPlayer.setMoveForward(false);
        }if(keyReleased==controls[1]){
            tankPlayer.setMoveBackward(false);
        }if(keyReleased==controls[2]){
            tankPlayer.setTurnLeftAngle(false);
        }if(keyReleased==controls[3]){
            tankPlayer.setTurnRightAngle(false);
        }if(keyReleased==controls[4]){
            tankPlayer.setShootBullets(false);
        }
    }
}
