/** ControlSceneAction.java
 *  Description:    This file is for the button actions within the control scene
 *                  Ex. Switch scene back to the Main menu
 *
 *  @Author: Alex Yuam
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Input;

import org.alexyuan.Main;
import org.alexyuan.Output.GameElement.MusicOutput;

import static org.alexyuan.Output.Scene.GameMenu.*;

public class ControlSceneAction {

    /**         METHODS        **/

    /**back
     * switch scene to game menu when back button
     * is clicked
     */
    public static void back(){
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);  //play sound effect (click)

        Main.window.setScene(getGameMenu());                //change the view to main menu
    }
}
