/** OverSceneAction.java
 * Description: This file is for the action of button on the game over scene
 *              Ex. Switch scene back to the Main Menu
 *
 * @Author: Alex Yuan
 * @version: 1.0 (Update: Jan, 18th, 2020)
 */

package org.alexyuan.Input;

import org.alexyuan.Main;
import org.alexyuan.Output.GameElement.MusicOutput;
import org.alexyuan.Output.Scene.GameMenu;
import javafx.scene.paint.Color;

import static org.alexyuan.Output.Scene.GameOver.buttonBox;
import static org.alexyuan.Output.Scene.PauseScene.resumeGame;

public class OverSceneAction {

    /**         VARIABLES         **/
    private static GameMenu e = new GameMenu();                 //object for switch scene

    /**         METHODS        **/

    /**released
     * when released switch scene to main menu
     */
    public static void released() {
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);      //play sound effect

        buttonBox.setStroke(Color.WHITE);                       //change color back to white

        resumeGame = false;                                     //tell the program there are no game to resume

        Main.window.setScene(e.getGameMenu());                  //switch to main scene
    }

    /**pressed
     * when pressed show red rectangle
     */
    public static void pressed() {                              //when button is pressed
        buttonBox.setStroke(Color.RED);                         //set the stroke of the rectangle to red
    }
}