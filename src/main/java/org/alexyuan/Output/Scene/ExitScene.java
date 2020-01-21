/** ExitScene.java
 *  Description: Check with the player to see of
 *               they really wants to leave the program
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.Scene;

import org.alexyuan.Input.ExitSceneAction;
import org.alexyuan.Output.GameElement.MenuItemSetting.*;

import org.alexyuan.Output.GameElement.MenuItemSetting.MenuItem;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ExitScene {

    /**         VARIABLES         **/
    private static AnchorPane exitPane;
    private static Scene exitScene;
    private static Stage exitStage;

    private static MenuItem no = new MenuItem("NO");
    private static MenuItem yes = new MenuItem("YES");

    /**         METHODS         **/

    /**createContent
     * creates design and layout of the scene
     * @return
     * returns the design and layout for
     * readability purposes
     */
    private Parent createContent(){

        //setting of pane
        exitPane = new AnchorPane();
        exitPane.setPrefSize(600,300);
        exitPane.setStyle("-fx-background-color: black");       //background color

        //message setting
        Text message = new Text("Exit Yuan Geometry War?");
        message.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,30));
        message.setFill(Color.WHITE);
        message.setTranslateX(180);
        message.setTranslateY(150);

        //displaying the buttons
        MenuBox yesButton = new MenuBox(yes);
        MenuBox noButton = new MenuBox(no);
        yesButton.setTranslateX(70);
        yesButton.setTranslateY(220);
        noButton.setTranslateX(320);
        noButton.setTranslateY(220);

        exitPane.getChildren().addAll(noButton,yesButton,message);      //display components

        //CALLING ON CONSTRICTORS
        MouseAction e = new MouseAction();

        return exitPane;
    }

    //button actions
    private static class MouseAction{
        private MouseAction(){
            yes.setOnMouseClicked(event -> ExitSceneAction.yesButtonAction());
            no.setOnMouseClicked(event -> ExitSceneAction.noButtonAction());
        }
    }

    /**ExitScene
     * Initialize the scene and stage variable
     * */
    public ExitScene() {
        exitScene = new Scene(createContent());

        exitStage = new Stage();
        exitStage.setScene(exitScene);
        exitStage.setResizable(false);
        exitStage.initStyle(StageStyle.TRANSPARENT);
    }

    /**getExitStage
     * @return
     * for other parts of the program to get
     * access to the stage
     */
    public static Stage getExitStage(){
        return exitStage;
    }
}
