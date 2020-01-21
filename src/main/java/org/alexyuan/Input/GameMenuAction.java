/** GameMenuAction.java
 * Description: This file is for the button control in the main menu
 *              when a button in the main menu is clicked it will come
 *              to this file to find out what to do.
 *
 * @author: Alex Yuan
 * @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Input;

import org.alexyuan.Main;
import org.alexyuan.Output.GameElement.MusicOutput;
import org.alexyuan.Output.Scene.ControlScene;
import org.alexyuan.Output.Scene.ExitScene;
import org.alexyuan.Output.Scene.GameScene;
import org.alexyuan.Output.Scene.ResumeRejection;

import static org.alexyuan.Input.PlayerAction.pause;
import static org.alexyuan.Main.resetGame;
import static org.alexyuan.Output.GameElement.Bullets.bullets;
import static org.alexyuan.Output.GameElement.Enemies.*;
import static org.alexyuan.Output.GameElement.GameReactionAnimation.*;
import static org.alexyuan.Output.Scene.GameScene.gameRoot;
import static org.alexyuan.Output.Scene.PauseScene.resumeGame;

public class GameMenuAction {

    /**         VARIABLES         **/
    static GameScene gameScene = new GameScene();                   //object for switch scene
    static ControlScene controlManager = new ControlScene();        //object for switch scene
    static ExitScene exitManager = new ExitScene();                 //object for switch scene
    static ResumeRejection resumeManager = new ResumeRejection();   //object for switch scene

    /**         METHODS        **/

    /**closeGame
     * display a pop up window to ask the user
     * if they really want to leave the game
     */
    public static void closeGame(){
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);      //play sound effect

       exitManager.getExitStage().show();                       //show pop up window
    }

    /**newGame
     * switch scene to the game scene when new game clicked
     */
    public static void newGame(){
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);      //play sound effect

        resetGame();                                            //set the game to a fresh start

        pause = false;                                          //the game is not paused
        resumeGame = false;                                     //tells the program it's first time run

        Main.window.setScene(gameScene.getGameScene());         //change the view to the actual game view
    }

    /**controlScene
     * switch scene to the control scene when control clicked
     */
    public static void controlScene(){
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);      //play sound effect

        Main.window.setScene(controlManager.getControlScene()); //change to control scene
    }

    /**resumeGame
     * actions when resume game is clicked
     */
    public static void resumeGame() {
        if(resumeGame){                                         //if there is a game
            MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);  //play sound effect

            Main.window.setScene(gameScene.getGameScene());     //change the scene to the game play ground scene

            for(int i = 0; i < bullets.size(); i++)
                gameRoot.getChildren().add(bullets.get(i));
            for(int i = 0; i < seeker.size(); i++)
                gameRoot.getChildren().add(seeker.get(i));
            for(int i = 0; i < wanderer.size(); i++)            /**display all game elements that was on the scene**/
                gameRoot.getChildren().add(wanderer.get(i));
            for(int i = 0; i < wave.size(); i++)
                gameRoot.getChildren().add(wave.get(i));

        }else{                                                  //if there isn't a game
            MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);  //play effect when clicked

            resumeManager.getRejectionStage().show();           //show the pop up window to tell user there is no game
        }
    }
}
