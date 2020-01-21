/** GameScene.java
 *  Description: The actual Game. Have the game loop inside this file.
 *               pretty much all files in the GameElement folder is for
 *               the game loop inside this java file.
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.Scene;

import org.alexyuan.Main;
import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import static org.alexyuan.Input.PlayerAction.*;
import static org.alexyuan.Output.GameElement.Bullets.shootBullets;
import static org.alexyuan.Output.GameElement.Enemies.spawnEnemy;
import static org.alexyuan.Output.GameElement.GameInfoDisplay.drawGameInfoDisplay;
import static org.alexyuan.Output.GameElement.GameInfoDisplay.*;
import static org.alexyuan.Output.GameElement.GameReactionAnimation.*;
import static org.alexyuan.Output.Scene.GameOver.getGameOverScene;
import static org.alexyuan.Output.Scene.PauseScene.resumeGame;
import static org.alexyuan.Processing.RemoveGameObject.*;

public class GameScene{

    /**         VARIABLES         **/

    public static Group gameRoot;
    public static Scene gameScene;

    //Time
    public static long currentTime = 0;

    //game loop
    public static AnimationTimer timer;

    //background and player image
    public static ImageView player = new ImageView(new Image(GameScene.class.getResourceAsStream("/Player.png")));
    public static ImageView menuBack = new ImageView(new Image(GameScene.class.getResourceAsStream("/GameBackground.png")));

    //rotates player
    public static int playerRotation = 0;

    //for frame calculation
    private static long oldFrameTime = 0;
    private static final long[] frameTimes = new long[100];
    private static int frameTimeIndex = 0 ;
    private static boolean arrayFilled = false ;

    //to remember player x and y for resume game
    public static double playerLastX = 0, playerLastY = 0;

    /**         METHODS        **/

    /**getGameScene
     * the design and layout of the game scene
     * (contains a game loop that does animation
     * in this scene)
     * @return
     * returns the scene for other parts of the program
     * to refer to this scene and switch to it
     */
    public static Scene getGameScene(){

        gameRoot = new Group();

        //setting the the glowing effect on the wave
        waveEff= new DropShadow();
        waveEff.setOffsetY(0f);
        waveEff.setOffsetX(0f);
        waveEff.setColor(Color.GOLD);
        waveEff.setWidth(100);
        waveEff.setHeight(100);

        //display background image
        gameRoot.getChildren().add(menuBack);

        //draws the lines in the background
        for(int i = 0; i < 61; i++){
            Line line = new Line(0,i*10, 1066, i*10);
            line.setStroke(Color.rgb(3,14,69));

            gameRoot.getChildren().add(line);
        }
        for(int i = 0; i < 107; i++){
            Line line = new Line(i*10,0, i*10, 600);
            line.setStroke(Color.rgb(3,14,69));

            gameRoot.getChildren().add(line);
        }

        //setting of wave bar
        waveBar = new Rectangle(167,70, 100,20);
        waveBar.setFill(Color.GOLDENROD);

        //display the above in the window
        gameRoot.getChildren().addAll(waveBar,waveBarText);

        if(!resumeGame) {                               //if is new game
            movePlayerTo(900 / 2, 600 / 2);      //move player to center
        }else{                                          //if is not new game
            movePlayerTo(playerLastX, playerLastY);     //move player to last x and y
        }

        //display all info (time left, score)
        drawGameInfoDisplay();

        //display the player
        gameRoot.getChildren().addAll(player);

        gameScene = new Scene(gameRoot);

        //set the cursor to a cross
        gameScene.setCursor(Cursor.CROSSHAIR);

        //get inputs from user
        playerKeyBoardInput();
        playerMouseInput();

        //game loop
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            //update program time
            currentTime = System.currentTimeMillis();

            //calculate frame
            oldFrameTime = frameTimes[frameTimeIndex] ;
            frameTimes[frameTimeIndex] = now ;
            frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
            if (frameTimeIndex == 0) {
                arrayFilled = true ;
            }
            if (arrayFilled) {
                long elapsedNanos = now - oldFrameTime ;
                long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
                fps.setText("FPS: " + (int)frameRate);
            }

            //move the player and rotate
            int dx = 0, dy = 0;
            if (up){
                dy -= 5;
                playerRotation = -90;
                player.setRotate(playerRotation);
            }
            if (down){
                dy += 5;
                playerRotation = 90;
                player.setRotate(playerRotation);
            }
            if (right){
                dx += 5;
                playerRotation = 0;
                player.setRotate(playerRotation);
            }
            if (left){
                playerRotation = 180;
                player.setRotate(playerRotation);
                dx -= 5;
            }
            if(right && down){
                playerRotation = 45;
                player.setRotate(playerRotation);
            }
            if(right && up){
                playerRotation = -45;
                player.setRotate(playerRotation);
            }
            if(left && down){
                playerRotation = 135;
                player.setRotate(playerRotation);
            }
            if(left && up){
                playerRotation = -135;
                player.setRotate(playerRotation);
            }

            //wave bar animation (increase when not 100)
            waveBar.setWidth(waveCharged);

            //CALL ON METHODS (Go To Each TO SEE DESCRIPTION)
            movePlayerBy(dx, dy);
            spawnTrace();
            textTimer();
            spawnEnemy();
            shootBullets();
            bulletDeathAnimation();
            bulletOutOfBoundary();
            shootEnemy();
            waveKillEnemy();
            playerDies();
            waveBarAnimation();
            waveAnimation();

            //if 0 time left
            if(timeLeft <= 0)
                Main.window.setScene(getGameOverScene());       //go to game over scene

            }
        };
        timer.start();

        return gameScene;
    }
}
