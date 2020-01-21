/** ResumeRejectionAction.java
 *  Description: This file handles button action in the resume rejection pop up window.
 *               This file can tell the player they cannot resume a game if no games have been played yet
 *               or resume the game if there is a game to resume to.
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Input;

import org.alexyuan.Output.GameElement.MusicOutput;
import org.alexyuan.Output.Scene.ResumeRejection;

public class ResumeRejectionAction {

    /**         VARIABLES         **/
    private static ResumeRejection resumeManager;       //object for switch scene

    /**         METHODS        **/

    /**backButtonAction
     * remove the rejection window when back button pressed
     */
    public static void backButtonAction() {
        MusicOutput.playEffects(MusicOutput.MUSICFILE[2]);  //play sound effect

        resumeManager.getRejectionStage().hide();           //hide (close) the stage
    }
}
