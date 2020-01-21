/** GameOver.java
 *  Description: This file display the scene after the game
 *               ends. It tells the player what score they got
 *               and allows them to go back to main menu.
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.Scene;

import org.alexyuan.Input.OverSceneAction;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static org.alexyuan.Output.GameElement.GameInfoDisplay.score;
import static org.alexyuan.Output.Scene.GameScene.timer;

public class GameOver {

    /**         VARIABLES         **/
    private static AnchorPane overPane;
    private static StackPane buttonPane;
    private static Scene overScene;

    //background image
    private static ImageView background;

    //texts in the scene
    private static Text gameOver = new Text(215,280,"GAME OVER");
    private static Text finalScore = new Text(345, 345,"");
    private static Text buttonText = new Text(450,460,"Main Menu");

    public static Rectangle buttonBox = new Rectangle(240, 100 );

    /**         METHODS         **/

    /**getGameOverScene
     * the design of the game over scene
     * @return
     * returns the scene so other parts of the program
     * can refer and switch to this scene
     */
    public static Scene getGameOverScene() {

        timer.stop();                   //stop game loop

        overPane = new AnchorPane();
        buttonPane = new StackPane();

        //the big button (Main Menu) setting
        buttonPane.setLayoutX(440);
        buttonPane.setLayoutY(400);

        //load background image
        background = new ImageView(new Image(GameOver.class.getResourceAsStream("/GameBackground.png")));
        overPane.getChildren().add(background);

        //setting of the texts
        buttonText.setFont(Font.font("Tw Cen MT", FontWeight.SEMI_BOLD,50));
        gameOver.setFont(Font.font("Tw Cen MT", FontWeight.SEMI_BOLD,125));
        finalScore.setFont(Font.font("Tw Cen MT", FontWeight.SEMI_BOLD,50));
        buttonText.setFill(Color.WHITE);
        gameOver.setFill(Color.WHITE);
        finalScore.setFill(Color.WHITE);
        finalScore.setText("FINAL SCORE： " + score);

        //setting of the box around the text (Main Menu)
        buttonBox.setFill(Color.TRANSPARENT);
        buttonBox.setStrokeWidth(3);
        buttonBox.setStroke(Color.WHITE);

        //when buttons clicked run following code
        buttonPane.setOnMousePressed(event -> OverSceneAction.pressed());
        buttonPane.setOnMouseReleased(event -> OverSceneAction.released());

        //display all components of the scene
        buttonPane.getChildren().addAll(buttonBox,buttonText);
        overPane.getChildren().addAll(gameOver,finalScore,buttonPane);

        overScene = new Scene(overPane);
        return overScene;
    }
}
