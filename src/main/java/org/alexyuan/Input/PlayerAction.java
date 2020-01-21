/** PlayerAction.java
 *  Description: This file handles all user input
 *               such as moving the player image,
 *               shooting bullets and so on.
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Input;

import org.alexyuan.Output.GameElement.MusicOutput;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static org.alexyuan.Output.GameElement.Bullets.drawBullets;
import static org.alexyuan.Output.GameElement.GameInfoDisplay.*;
import static org.alexyuan.Output.GameElement.GameReactionAnimation.*;
import static org.alexyuan.Output.Scene.GameScene.*;
import static org.alexyuan.Output.Scene.PauseScene.*;


public class PlayerAction {

    /**         VARIABLES         **/
    public static double mouseX = 0, mouseY = 0;                //mouse X and Y position

    public static boolean up = false, down = false, left = false, right = false, pause = false;    //if key pressed

    /**         METHODS        **/

    /**playerKeyBoardInput
     * gets players keyboard input
     */
    public static void playerKeyBoardInput() {       //when key is pressed
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:     up = true; break;               //tell program w pressed
                    case S:     down = true; break;             //tell program s pressed
                    case A:     left = true; break;             //tell program a pressed
                    case D:     right = true; break;            //tell program d pressed
                }
            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {   //when key released
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:     up = false; break;              //tell program w is not pressed
                    case S:     down = false; break;            //tell program a is not pressed
                    case A:     left = false; break;            //tell program s is not pressed
                    case D:     right = false; break;           //tell program d is not pressed

                }
            }
        });

        //also when key pressed (for longer body of code)
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if(t.getCode() == KeyCode.ESCAPE)       //when escape pressed
                {
                    if(!pause){                         //if not yet pause
                        pause = true;                   //tell program to pause
                        timer.stop();                   //stop animation
                        getPauseScene();                //display pause scene
                    }
                    else{                               //if already paused
                        pause = false;                  //tell program to not pause
                        timer.start();                  //restart animation
                        removePausePane();              //remove the pause menu
                    }
                }

                if(t.getCode() == KeyCode.SPACE && waveCharged >= 100){         //if space pressed
                    waveCharged = 0;

                    Circle circle = new Circle(player.getLayoutX(),player.getLayoutY(),0);  //load circle at player x y
                    circle.setFill(null);               //set color of the circle to transparent
                    circle.setStroke(Color.GOLD);       //outline it with gold
                    circle.setEffect(waveEff);          //give it light effect

                    gameRoot.getChildren().add(circle); //display the circle

                    wave.add(circle);                   //add it to a array list
                }
            }
        });
    }

    /**movePlayerBy
     * calculate how much to move th player
     * @param dx
     * speed in x
     * @param dy
     * speed in y
     */
    public static void movePlayerBy(int dx, int dy) {                   //calculate movement of player

        final double cx = player.getBoundsInLocal().getWidth()  /2;     //calculate center point of image X
        final double cy = player.getBoundsInLocal().getHeight() /2;     //calculate center point of image Y

        double x = cx + player.getLayoutX() + dx;                       //base on above info cal the point
        double y = cy + player.getLayoutY() + dy;                       //base on above info cal the point

        movePlayerTo(x, y);                                             //move
    }

    /**movePlayerTo
     * Move the player
     * @param x
     * player location
     * @param y
     * player locatoin
     * */
    public static void movePlayerTo(double x, double y) {               //moves the player
        final double cx = player.getBoundsInLocal().getWidth()  / 2;    //calculate center point of image X
        final double cy = player.getBoundsInLocal().getHeight() / 2;    //calculate center point of image X

        if (x - cx >= 0 && x + cx <= 1067 && y - cy >= 0 && y + cy <= 602) { //if within the window
            player.relocate(x - cx, y - cy);                          //the actual move
        }
    }

    /**playerMouseInput
     * for getting players mouse actions
     * */
    public static void playerMouseInput(){                              //mouse action

        gameScene.setOnMouseMoved(new EventHandler<MouseEvent>() {      //when mouse is moved
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseX = mouseEvent.getX();                             //get mouse X
                mouseY = mouseEvent.getY();                             //get mouse Y
            }
        });

        gameScene.setOnMousePressed(new EventHandler<MouseEvent>() {    //when mouse is pressed

            @Override
            public void handle(MouseEvent event) {

                if(!pause) {                                            //if game is not paused
                    MusicOutput.playEffects(MusicOutput.MUSICFILE[1]);  //play shoot sound effect

                    drawBullets();                                      //draw bullet
                }
            }
        });
    }
}
