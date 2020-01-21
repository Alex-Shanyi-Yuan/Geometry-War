/** Main.java
 *  Description: This file is everything to do with the window
 *               It creates a window and have the calculation to
 *               center the window as well as resetting the game
 *               information about the actual game.
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */


package org.alexyuan;

import org.alexyuan.Output.Scene.GameMenu;
import org.alexyuan.Output.GameElement.MusicOutput;

import javafx.application.Application;
import javafx.application.Preloader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.alexyuan.Output.Scene.SplashScene;

import static org.alexyuan.Output.GameElement.Bullets.*;
import static org.alexyuan.Output.GameElement.Enemies.*;
import static org.alexyuan.Output.GameElement.GameInfoDisplay.*;
import static org.alexyuan.Output.GameElement.GameReactionAnimation.*;
import static org.alexyuan.Input.PlayerAction.*;
import static org.alexyuan.Output.Scene.GameScene.gameRoot;

public class Main extends Application {

    /**         VARIABLES         **/
    GameMenu e = new GameMenu();                                //object for switch scene

    private static final int COUNT_LIMIT = 50000;
    public static Stage window;                                 //make a reference to the window (stage)
                                                        /*      Important for switch between scene      */

    private static Rectangle2D primScreenBounds;

    /**         METHODS        **/

    /**resetGame
     * reset all information in the game for
     * starting new games
     * */
    public static void resetGame(){                 //TO RESET THE GAME

        for(int i = 0; i < bullets.size(); i++)
            gameRoot.getChildren().remove(bullets.get(i));
        for(int i = 0; i < seeker.size(); i++)
            gameRoot.getChildren().remove(seeker.get(i));
        for(int i = 0; i < wanderer.size(); i++)                /**    Read all game element     **/
            gameRoot.getChildren().remove(wanderer.get(i));
        for(int i = 0; i < wave.size(); i++)
            gameRoot.getChildren().remove(wave.get(i));

        //clear all element within the array list
        bulletVelocityX.clear();                    
        bulletVelocityY.clear();                    
        bulletAngle.clear();                        
        bullets.clear();                            

        seeker.clear();                             
        wanderer.clear();                           

        seekerAngle.clear();                        

        wandererMovementAngle.clear();              
        wandererMovementX.clear();                  
        wandererMovementY.clear();                  

        seekerVelocityX.clear();                    
        seekerVelocityY.clear();                    

        wave.clear();

        //stop all movement of player
        up = false;                                 
        left = false;                               
        right = false;                              
        down = false;                               

        score = 0;                                  //reset score
        timeLeft = 120;                             //reset time left
        waveCharged = 120;                          //recharge the wave
    }

    /**centerWindow
     * for centering the window in the center of the screen
     * */
    public static void centerWindow() {
        primScreenBounds = Screen.getPrimary().getVisualBounds();                           //get window information
        Main.window.setX((primScreenBounds.getWidth() - Main.window.getWidth()) / 2);       //move X value to center of screen
        Main.window.setY((primScreenBounds.getHeight() - Main.window.getHeight()) / 2);     //move Y value to center of screen
    }

    /**start
     * javafx method for setting up window
     * */
    @Override
    public void start(Stage primaryStage){          //required by library

        MusicOutput.playBackground(MusicOutput.MUSICFILE[0]);   //play looped background music

        window = primaryStage;                      //so we can reference to it even outside of the methods (function)
        window.setScene(e.getGameMenu());           //display the game menu
        window.setResizable(false);                 //can not be resized
        window.initStyle(StageStyle.UNDECORATED);   //hide the stage bar
        window.show();                              //display the window
    }

    /**init
     * javafx method for preload (splash scene)
     * */
    @Override
    public void init() throws Exception {
        for (int i = 0; i < COUNT_LIMIT; i++){
            double progress  = (100 * i) / COUNT_LIMIT;
            notifyPreloader(new Preloader.ProgressNotification(progress));
        }
    }

    public static void main(String[] args) {
        System.setProperty("javafx.preloader", SplashScene.class.getName());
        Application.launch(Main.class, args);                               //run all code      (required)
    }

}
