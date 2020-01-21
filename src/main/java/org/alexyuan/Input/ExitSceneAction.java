/** ExitSceneAction.java
 * Description: This file is for the buttons action in the exit scene
 *              Ex. Move back to Main Menu & show the ending Animation
 *
 * @author: Alex Yuan
 * @version: 1.00 (Update: Jan, 18th, 2020)
 */

package org.alexyuan.Input;

import org.alexyuan.Main;
import org.alexyuan.Output.Scene.ByeScene;
import org.alexyuan.Output.Scene.ExitScene;
import org.alexyuan.Output.GameElement.MusicOutput;

import static org.alexyuan.Output.GameElement.MusicOutput.playBack;

public class ExitSceneAction {

    /**         VARIABLES         **/
    private static ExitScene exitManager;                   //object for switch scene
    private static ByeScene byeManager;                     //object for switch scene

    /**         METHODS        **/

    /**noButtonAction
     * when no button is clicked close the window
     */
    public static void noButtonAction(){
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);  //play music effect

        exitManager.getExitStage().hide();                  //hide (exit) the stage
    }

    /**yesButtonAction
     * when yes button is clicked play the good bye animation
     */
    public static void yesButtonAction(){                   //when yes button is clicked
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);  //play sound effect
        playBack.stop();

        exitManager.getExitStage().hide();                  //hide (exit) the stage
        Main.window.setScene(byeManager.getByeScene()); //change to control scene

        Main.centerWindow();                                //method for center the window

    }
}
