/** ResumeRejection.java
 *  Description: This file will only run if the player clicked on resume
 *               but there is no games to resume to. It tells the player
 *               that there are no games to resume to and take them back
 *               to the main menu.
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.Scene;

import org.alexyuan.Input.ResumeRejectionAction;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.alexyuan.Output.GameElement.MenuItemSetting.*;

public class ResumeRejection {

    /**         VARIABLES         **/
    private static AnchorPane rejectionPane;
    private static Scene rejectionScene;
    private static Stage rejectionStage;

    //button
    private static MenuItem back = new MenuItem("BACK");

    /**         METHODS        **/

    /**createContent
     * this method can creates the design and layout of the resume rejection scene
     * @return
     * returns the AnchorPane as layout for setting up the scene variable
     */
    private Parent createContent(){

        rejectionPane = new AnchorPane();

        //size of pop up window
        rejectionPane.setPrefSize(600,300);

        //background color
        rejectionPane.setStyle("-fx-background-color: black");

        //setting of message
        Text message = new Text("No Available Game!");
        message.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,30));
        message.setFill(Color.WHITE);

        message.setTranslateX(200);
        message.setTranslateY(150);

        //setting of the button
        MenuBox backButton = new MenuBox(back);

        backButton.setTranslateX(195);
        backButton.setTranslateY(220);

        //display above objects
        rejectionPane.getChildren().addAll(backButton,message);

        MouseAction e = new MouseAction();

        return rejectionPane;
    }

    /**MouseAction
     * this method will run actions when the buttons are clicked
     */
    private static class MouseAction{
        private MouseAction(){
            back.setOnMouseClicked(event -> ResumeRejectionAction.backButtonAction());
        }
    }

    /**ResumeRejection
     * this constructor initializes the stage variable
     */
    public ResumeRejection() {
        rejectionScene = new Scene(createContent());

        rejectionStage = new Stage();
        rejectionStage.setScene(rejectionScene);
        rejectionStage.setResizable(false);
        rejectionStage.initStyle(StageStyle.TRANSPARENT);
    }

    /**getRejectionStage
     * @return
     * returns the stage for other parts of the program to use it
     */
    public static Stage getRejectionStage(){
        return rejectionStage;
    }
}
