/** GameMenu.java
 *  Description: This scene gives the player options to
 *               start a new game, see controls, resume
 *               to a game and exit the game
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.Scene;

import org.alexyuan.Input.GameMenuAction;
import org.alexyuan.Output.GameElement.MenuItemSetting.*;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GameMenu {

    /**         VARIABLES         **/
    private static AnchorPane mainPane;
    private static Scene mainScene;

    //buttons
    private static MenuItem newGame = new MenuItem("NEW GAME");
    private static MenuItem continueGame = new MenuItem("RESUME GAME");
    private static MenuItem controls = new MenuItem("CONTROLS");
    private static MenuItem exit = new MenuItem("EXIT");

    public static ImageView menuBack = new ImageView(new Image(GameMenu.class.getResourceAsStream("/MenuBackGround.jpg"))); //background image

    /**         METHODS         **/

    /**createContent
     * creates the design of the scene
     *
     * @return
     * returns the design and layout for
     * readability purposes
     */
    private static Parent createContent(){
        mainPane = new AnchorPane();

        //background image
        mainPane.getChildren().add(menuBack);

        //title setting
        Title title = new Title("GEOMETRY WAR");
        title.setTranslateX(75);
        title.setTranslateY(200);

        //setting of the buttons
        MenuBox menu = new MenuBox(newGame,continueGame,controls,exit);
        menu.setTranslateX(100);
        menu.setTranslateY(300);

        //display title and buttons
        mainPane.getChildren().addAll(title,menu);

        //when buttons are clicked run following code
        MouseAction e = new MouseAction();

        return mainPane;
    }

    /**MouseAction
     * actions when mouse is clicked on each button
     */
    private static class MouseAction{
        private MouseAction(){
            exit.setOnMouseClicked(event -> GameMenuAction.closeGame());
            newGame.setOnMouseClicked(event -> GameMenuAction.newGame());
            continueGame.setOnMouseClicked(event -> GameMenuAction.resumeGame());
            controls.setOnMouseClicked(event -> GameMenuAction.controlScene());
        }
    }

    /**getGameMenu
     * allows other part of the program to get access the scene
     * @return
     * returns the scene so other parts of the program can refer
     * to this scene and switch to it
     */
    public static Scene getGameMenu(){
        mainScene = new Scene(createContent());
        return mainScene;
    }
}
