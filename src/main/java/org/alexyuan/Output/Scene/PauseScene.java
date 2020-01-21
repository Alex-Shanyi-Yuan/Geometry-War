/** PauseScene.java
 *  Description: this file displays the pause scene
 *               it shows buttons, texts and a board for
 *               displaying texts.
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.Scene;

import org.alexyuan.Input.PauseSceneAction;
import org.alexyuan.Output.GameElement.MenuItemSetting.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static org.alexyuan.Output.GameElement.GameInfoDisplay.score;
import static org.alexyuan.Output.GameElement.GameReactionAnimation.timeLeft;
import static org.alexyuan.Output.Scene.GameScene.gameRoot;

public class PauseScene {

    /**         VARIABLES         **/
    //board for displaying text
    private static Rectangle outline;
    private static Rectangle backRect;
    private static Rectangle displayBox;

    //the time left in the game
    private static Text message = new Text();
    private static Text currentTimeLeft = new Text(513,410,"Time Left: " + timeLeft);
    private static Text currentScore = new Text(742,410,"Score: " + score);

    //a image to make player feel better
    private static ImageView faceIcon;

    //buttons
    private static MenuItem resume = new MenuItem("RESUME");
    private static MenuItem restart = new MenuItem("RESTART");
    private static MenuItem mainMenu = new MenuItem("MAIN MENU");

    private static Title title;
    private static MenuBox menu;

    public static boolean resumeGame = false;

    /**         METHODS        **/

    /**getPauseScene
     * this methods does the design and layout of the program
     */
    public static void getPauseScene(){

        //background with 80% visibility
        backRect = new Rectangle(0,0,1067,602);
        backRect.setFill(Color.BLACK);
        backRect.setOpacity(0.8);

        //setting of title
        title = new Title("GEOMETRY WAR");
        title.setTranslateX(75);
        title.setTranslateY(200);

        //setting of buttons
        menu = new MenuBox(resume,restart,mainMenu);
        menu.setTranslateX(100);
        menu.setTranslateY(300);

        //setting of the board
        displayBox = new Rectangle(434,40,515,506);
        displayBox.setFill(Color.BLACK);

        //out line of the display board
        outline = new Rectangle(434,40,515,506);
        outline.setFill(null);
        outline.setStroke(Color.WHITE);
        outline.setStrokeWidth(3);

        //settings of message
        message.setLayoutY(149);
        message.setFill(Color.WHITE);
        message.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,30));

        currentScore.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,30));
        currentTimeLeft.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,30));

        currentScore.setFill(Color.WHITE);
        currentTimeLeft.setFill(Color.WHITE);

        currentScore.setText("Score: " + score);
        currentTimeLeft.setText("Time Left: " + timeLeft);

        //more setting of message
        if(score > 0){
            message.setText("Hey! You're Doing Pretty Good");       //if score is > 0 tell them good job
            message.setLayoutX(555);

            //show smiley image

            faceIcon = new ImageView(new Image(PauseScene.class.getResourceAsStream("/Smiley.png")));


        }else{  //if score below 0
            message.setText("Hummm, Try Harder!! You Can Do This?");        //tell them to try harder
            message.setLayoutX(520);

            //show sad face
            faceIcon = new ImageView(new Image(PauseScene.class.getResourceAsStream("/SadFace.png")));

        }

        //setting of the images
        faceIcon.setLayoutX(625);
        faceIcon.setLayoutY(180);

        //when button clicked
        MouseAction e = new MouseAction();

        //display on window
        gameRoot.getChildren().addAll(backRect,title,menu,displayBox,message,faceIcon,currentScore,currentTimeLeft,outline);
    }

    /**MouseAction
     * this method does the action when a button is clicked
     */
    private static class MouseAction{
        private MouseAction(){
            resume.setOnMouseClicked(event -> PauseSceneAction.resumeGame());
            restart.setOnMouseClicked(event -> PauseSceneAction.restartGame());
            mainMenu.setOnMouseClicked(event -> PauseSceneAction.mainMenuScene());
        }
    }

    /**removePausePane
     * this method removes the pause scene
     */
    public static void removePausePane(){
        gameRoot.getChildren().removeAll(backRect,title,menu,displayBox,message,faceIcon,currentScore,currentTimeLeft,outline);
    }
}
