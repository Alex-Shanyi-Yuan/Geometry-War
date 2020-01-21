/** PauseSceneAction.java
 *  Description: This scene is to control button action in the pause menu
 *               Ex. Resume, restart and switch back to Main menu
 *
 *  @Author: ALex Yuan
 *  @Version: 1.0 (Update: Jav 18th, 2020)
 */

package org.alexyuan.Input;

import org.alexyuan.Main;
import org.alexyuan.Output.GameElement.MusicOutput;
import org.alexyuan.Output.Scene.GameMenu;

import static org.alexyuan.Main.resetGame;
import static org.alexyuan.Output.GameElement.GameInfoDisplay.scoreText;
import static org.alexyuan.Output.GameElement.GameReactionAnimation.*;
import static org.alexyuan.Output.Scene.GameScene.*;
import static org.alexyuan.Output.Scene.PauseScene.*;
import static org.alexyuan.Input.PlayerAction.*;

public class PauseSceneAction {

    /**         VARIABLES         **/
    private static GameMenu e = new GameMenu();                 //object for switch scene

    /**         METHODS        **/

    /**resumeGame
     * when resume button clicked remove the pause scene
     */
    public static void resumeGame(){
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);      //play sound effect

        pause = false;                                          //tell the program it's not paused
        timer.start();                                          //start the animation
        removePausePane();                                      //remove the pause menu
    }

    /**restartGame
     * when restart button clicked
     * reset game information and
     * remove the pause scene
     */
    public static void restartGame(){
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);      //play sound effect

        resetGame();                                            //reset game information
        scoreText.setText("0");                                 //reset the score;
        timeText.setText(timeLeft +"");                         //reset the time left

        pause = false;                                          //tell the program it's not paused anymore
        timer.start();                                          //restart the animation
        removePausePane();                                      //remove the pause scene

        movePlayerTo(900 / 2, 600 / 2);                  //move the player back to default place

    }

    /**mainMenuScene
     * when main menu button clicked
     * switch scene to main menu and remember
     * current game information
     */
    public static void mainMenuScene(){
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);      //plays sound effect

        playerLastX = player.getLayoutX();                      //remember player's last position
        playerLastY = player.getLayoutY();                      //remember player's last position

        pause = false;                                          //tell the program it's not paused anymore
        resumeGame = true;                                      //tell the program there is a game that can be resumed

        Main.window.setScene(e.getGameMenu());                  //switch scene to main menu
    }
}
