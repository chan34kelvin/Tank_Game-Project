package TankGame;

public class GameInfoConstants {
    /*
    (comments)(GameInfoConstants)
        finished.
        a place to store all default values of each individual element.
     */

    public final static String NAME= "Tank Game";
    public final static String BACKGROUND_COLOR= "#4b5320";
    public final static String GAME_FONT= "Courier New";
    public final static int PIXELS_BTW_BUTTONS =100;
    public final static int GAME_FONT_SIZE= 50;
    public final static int CURRENT_GAMEMAP_ROWS= 64;
    public final static int CURRENT_GAMEMAP_COLS= 64;
    public final static int GAME_PIXELS= 32;
    public final static int SCREEN_WIDTH= 1024; // 32 * 32
    public final static int SCREEN_HEIGHT= 672;//(SCREEN_WIDTH-SCREEN_WIDTH/3+(32-(SCREEN_WIDTH-SCREEN_WIDTH/3)%32)); // (32 * 32) = int = int + int/3
    public final static int GAME_SCREEN_WIDTH= (CURRENT_GAMEMAP_COLS)*GAME_PIXELS;
    public final static int GAME_SCREEN_HEIGHT= (CURRENT_GAMEMAP_COLS)*GAME_PIXELS;
}
