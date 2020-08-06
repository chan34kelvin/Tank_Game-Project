package TankGame.DisplayObjects.Static;

import TankGame.GameInfoConstants;

import java.awt.*;
import java.io.InputStreamReader;
import java.util.Scanner;

public class StaticObjects {
    /*
    (comments)(StaticObjects)
        finished.
        the place to load every static object in the game map
        loads the game map and organize which static object applies to the needed location
        everything is organized by 2 dimensional array.
        checks if it collides with a moveable object.
        allow the image process for all static objects depend on the game panel.
     */

    //abbreviations of game constants
    private final int rows= GameInfoConstants.CURRENT_GAMEMAP_ROWS;
    private final int cols= GameInfoConstants.CURRENT_GAMEMAP_COLS;

    private Static [][] staticObjects;
    private Integer [][] staticObjectLayout;

    public StaticObjects(){
        staticObjectLayout= new Integer[rows][cols];
        staticObjects= new Static[rows][cols];
    }

    //load the respected map using a inputStream scanner, then use nextInt to make a 2 dimensional array map.
    public void loadLayout(){
        try {
            Scanner mapFile = new Scanner(new InputStreamReader((StaticObjects.class.getClassLoader().getResourceAsStream("GameMap.txt"))));
            for(int i = 0; i< rows; i++){
                for(int j=0;j<cols;j++){
                    staticObjectLayout[i][j]= mapFile.nextInt();
                }
            }
        }catch(Exception error){
            System.out.println("GameMap Load: "+error);
        }
    }

    //using the 2 dimensional array map, make a 2 dimensional static object map.
    public void loadObjects(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                switch(staticObjectLayout[i][j]){
                    case 9:
                        staticObjects[i][j]= new StaticWall(j,i);
                        break;
                    case 2:
                        staticObjects[i][j]= new StaticSheildUP(j,i);
                        break;
                    case 3:
                        staticObjects[i][j]= new StaticRocketUP(j,i);
                        break;
                    case 8:
                        staticObjects[i][j]= new StaticBrokenWall(j,i);
                        break;
                    default:
                        staticObjects[i][j]= new StaticBackground(j,i);
                }
            }
        }
    }

    //gets the hitBox from the moveable object, then divides its pos by 32 pixels to get pos in the map
    //check 2 objects after the first object to give time for the compiler to detect collision.
    //once it does collide, return the static object, if not return null.
    public Static ifCollide(Rectangle imageHitBox){
        int i= ((int)(imageHitBox.getY()/32));
        int j= ((int)(imageHitBox.getX()/32));
        int checkAreaAmt= 2;
        for (int row = i; row < rows&& row < i + checkAreaAmt; row++) {
            for (int col = j; col < cols&& col < j + checkAreaAmt; col++) {
                if((staticObjects[row][col]).getToCheck()) {
                    return staticObjects[row][col];
                }
            }
        }
        return null;
    }

    //draw all objects requested by game panel.
    public void drawStaticObjects(Graphics2D graphics){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                staticObjects[i][j].drawImage(graphics);
            }
        }
    }
}
