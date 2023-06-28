package TankGame;

import TankGame.Models.Moveable.MoveableTank;
import TankGame.Models.Static.StaticObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameMapDisplay{
    /*
    (comments)(GameMapDisplay)
        finished.
        decide what to be display in the game panel.
        a gameMapOne for tank 1, gameMapTwo for tank 2 and a miniMap.
        create graphics for the gameMap to make the above elements.
     */

    private BufferedImage gameMap, gameMapOne, gameMapTwo, miniMap;
    private Graphics2D gameMapGraphics;

    //abbreviations for the long constant names
    private final int screenWidth = GameInfoConstants.SCREEN_WIDTH;
    private final int screenHeight = GameInfoConstants.SCREEN_HEIGHT;
    private final int gameScreenWidth = GameInfoConstants.GAME_SCREEN_WIDTH;
    private final int gameScreenHeight = GameInfoConstants.GAME_SCREEN_HEIGHT;

    public GameMapDisplay(){
        gameMap= new BufferedImage(gameScreenWidth +17, gameScreenHeight +40,BufferedImage.TYPE_INT_RGB);
        gameMapGraphics= gameMap.createGraphics();
    }

    public void loadObjectImage(StaticObjects staticObjects, MoveableTank tankPlayerOne, MoveableTank tankPlayerTwo) {
        staticObjects.drawStaticObjects(gameMapGraphics);
        tankPlayerOne.drawImage(gameMapGraphics);
        tankPlayerTwo.drawImage(gameMapGraphics);
        miniMap= gameMap.getSubimage(0,0, gameScreenWidth, gameScreenHeight);
        gameMapOne= gameMap.getSubimage(modX(tankPlayerOne.getDisplayObjectX()),modY(tankPlayerOne.getDisplayObjectY()), screenWidth /2, screenHeight);
        gameMapTwo= gameMap.getSubimage(modX(tankPlayerTwo.getDisplayObjectX()),modY(tankPlayerTwo.getDisplayObjectY()), screenWidth /2, screenHeight);
    }

    //to check either the displayX and Ys are out of range for its width and height, if is adjust it with minus the current w or h
    public int modX(int objectX){
        return objectX>(gameScreenWidth -512)? gameScreenWidth -512:objectX;
    }

    public int modY(int objectY){
        return objectY>(gameScreenHeight - screenHeight)? gameScreenHeight - screenHeight :objectY;
    }

    public void drawImage(Graphics2D graphics){
        graphics.drawImage(gameMap,0,0,null);
        graphics.drawImage(gameMapTwo,0,0,null);
        graphics.drawImage(gameMapOne, screenWidth /2,0,null);
        //scale the whole map to 0.1 to make a miniMap
        graphics.scale(0.1,0.1);
        graphics.drawImage(miniMap, gameScreenWidth *2, gameScreenHeight *2,null);
    }
}
