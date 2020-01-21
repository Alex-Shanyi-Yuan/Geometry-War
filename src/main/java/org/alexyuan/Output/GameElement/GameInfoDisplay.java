/** GameInfoDisplay.java
 *  Description: This file is for displaying everything user might need
 *               in order to play the game
 *               Ex. score, time left, fps...
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 5th, 2020)
 */

package org.alexyuan.Output.GameElement;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static org.alexyuan.Output.Scene.GameScene.gameRoot;
import static org.alexyuan.Output.GameElement.GameReactionAnimation.*;

public class GameInfoDisplay {

    /**         VARIABLES         **/
    public static int score;                        //score
    public static Text scoreText;                   //display of score

    public static Text fps = new Text();            //display of fps

    private static Circle timeCircle = new Circle(1067/2, 70, 50);  //circle for the time left

    public static double waveCharged = 100;         //integer for wave charged (max 100)
    public static Rectangle waveBar;                //rectangle for showing how much wave is charged
    public static Text waveBarText = new Text(200,84,"SPACE");      //tell user to pressed space for wave

    /**         METHODS        **/

    /**drawGameInfoDisplay
     * collection of all game info display in this class
     */
    public static void drawGameInfoDisplay(){

        //call methods
        scoreSetting();
        fpsSetting();
        timerSetting();

        //display on window
        gameRoot.getChildren().addAll(fps,scoreText,timeCircle,timeText);
    }

    /**scoreSetting
     * set up the information for score
     */
    private static void scoreSetting(){         //setting for score display
        scoreText = new Text(score + "");    //set the text
        scoreText.setTranslateX(900);           //X
        scoreText.setTranslateY(50);            //Y
        scoreText.setFill(Color.WHITE);         //Color
    }

    /**fpsSetting
     * set up the information for fps
     */
    private static void fpsSetting(){           //setting for fps
        fps.setTranslateX(167);                 //X
        fps.setTranslateY(50);                  //Y
        fps.setFill(Color.WHITE);               //C
        //setting for text is in GameReactionAnimation since it needs to be updated
    }

    /**timerSeeting
     * set up the information for time left
     */
    private static void timerSetting(){         //setting of time left display
        timeCircle.setStroke(Color.rgb(0,204,255));                 //color of out line
        timeCircle.setFill(null);                                              //color inside to transparent

        timeText = new Text(timeLeft + "");                                 //set text for amount of text left
        timeText.setFont(Font.font("verdana", FontWeight.BOLD, 20));     //font
        timeText.setFill(Color.WHITE);                                          //color
        timeText.setLayoutX(1067/2 - 20);                                       //X
        timeText.setLayoutY(78);                                                //Y
    }

    /**waveBarAnimation
     * animation for the wave bar
     */
    public static void waveBarAnimation(){           //setting for wave bar
        if(waveCharged < 100){                       //if the int value of wave charged is less then 100
            waveCharged += 0.1;                      //add (rectangle's width is drawn using this so updating this will update rectangle)
        }
    }
}
