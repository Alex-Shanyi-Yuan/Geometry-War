/** ControlScene.java
 *  Description: Tells the user basic controls of the game
 *               inorder for the player to have a good experience
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */


package org.alexyuan.Output.Scene;

import org.alexyuan.Input.ControlSceneAction;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import org.alexyuan.Output.GameElement.MenuItemSetting.*;

public class ControlScene {

    /**         VARIABLES         **/
    private static ImageView intro = new ImageView(new Image(ControlScene.class.getResourceAsStream("/ControlBack.png")));

    private static AnchorPane controlPane;
    private static Scene controlScene;

    private static MenuItem back = new MenuItem("BACK");

    /**         METHODS         **/

    /**createControlContent
     * layout and design of the scene
     * @return
     * returns the design for readability purposes
     */
    private static Parent createControlContent(){          //setting up the view
        controlPane = new AnchorPane();

        //load image
        controlPane.getChildren().add(intro);

        //back button
        MenuBox menu = new MenuBox(back);
        menu.setTranslateX(40);
        menu.setTranslateY(540);

        controlPane.getChildren().add(menu);    //display the button

        back.setOnMouseClicked(event -> ControlSceneAction.back());     //when being clicked

        return controlPane;
    }

    /**getControlScene
     * @return
     * for other parts of the program to get the scene
     */
    public static Scene getControlScene(){
        controlScene = new Scene(createControlContent());
        return controlScene;
    }
}
