/** ByeScene.java
 *  Description: Draws all animation that happens after user
 *               clicked yes button to leave the game.
 *
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */


package org.alexyuan.Output.Scene;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ByeScene {

    /**         VARIABLES         **/
    //scene objects
    private static AnchorPane byePane;
    private static Scene byeScene;

    //different types of animation
    private static AnimationTimer endAnimation;
    private static ScaleTransition goBigW, goLong, goBigB;
    private static FadeTransition disMessage;
    private static TranslateTransition endA;

    //objects used in the animation
    private static Circle exploW, exploB;
    private static Line line = new Line();
    private static Text byeMessage = new Text("THANK Y0U FOR PLAYING");

    //player image in the ending animation
    private static ImageView playerTop = new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png")));
    private static ImageView playerBot = new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png")));
    private static ImageView playerLeft = new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png")));
    private static ImageView playerRight = new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png")));

    //player image in the ending animation after the thank you message is displayed
    private static ImageView[] endPlayerImage = {new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png"))),
                                                 new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png"))),
                                                 new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png"))),
                                                 new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png"))),
                                                 new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png"))),
                                                 new ImageView(new Image(ByeScene.class.getResourceAsStream("/Player.png")))};

    /**         METHODS         **/

    /**createContent
     * the design of the scene
     * @return
     * for other part of the program to access
     * code here
     */
    private static Parent createContent(){

        //setting of the Pane
        byePane = new AnchorPane();
        byePane.setPrefSize(1000,600);
        byePane.setStyle("-fx-background-color: black");

        //setting of the white circle in animation
        exploW = new Circle();
        exploW.setFill(Color.WHITE);
        exploW.setLayoutY(300);
        exploW.setLayoutX(500);

        //setting of black circle in the animation
        exploB = new Circle();
        exploB.setFill(Color.BLACK);
        exploB.setLayoutY(300);
        exploB.setLayoutX(500);

        //setting of the dark line in the animation
        line = new Line();
        line.setFill(Color.WHITE);
        line.setLayoutX(500);
        line.setLayoutY(300);

        //setting of all player image at the beginning of animation
        playerBot.setLayoutX(483);
        playerBot.setLayoutY(550);
        playerBot.setRotate(270);
        playerTop.setLayoutX(483);
        playerTop.setLayoutY(0);
        playerTop.setRotate(90);
        playerLeft.setLayoutX(0);
        playerLeft.setLayoutY(283);
        playerRight.setLayoutX(950);
        playerRight.setLayoutY(283);
        playerRight.setRotate(180);

        //setting of the Thank you message displayed
        byeMessage.setFill(Color.WHITE);
        byeMessage.setLayoutX(100);
        byeMessage.setLayoutY(450);
        byeMessage.setOpacity(0);
        byeMessage.setFont(Font.font("Broadway",60));

        //display all objects the is displayed during the animation (some are invisible but still displayed)
        byePane.getChildren().addAll(playerBot, playerLeft, playerRight, playerTop, exploW, exploB, line, byeMessage);

        //setting of all player images displayed after the thank you message
        for(int x = 0; x < endPlayerImage.length; x ++){
            byePane.getChildren().add(endPlayerImage[x]);

            endPlayerImage[x].setRotate(90);
            endPlayerImage[x].setLayoutX(100 + 100 * x);
            endPlayerImage[x].setLayoutY(-67);
        }

        //animation for the 4 player image at the beginning
        endAnimation = new AnimationTimer() {
            @Override
            public void handle(long l) {

                //moving the four images
                playerBot.relocate( playerBot.getLayoutX(),playerBot.getLayoutY() - 5);
                playerTop.relocate(playerTop.getLayoutX(), playerTop.getLayoutY() + 5);
                playerLeft.relocate(playerLeft.getLayoutX() + 8.33, playerLeft.getLayoutY());
                playerRight.relocate(playerRight.getLayoutX() - 8.33, playerRight.getLayoutY());

                //when the four image collides
                if(playerRight.getBoundsInParent().intersects(playerLeft.getBoundsInParent()) && playerTop.getBoundsInParent().intersects(playerBot.getBoundsInParent())){

                    //remove the four image and continue with the animation
                    byePane.getChildren().removeAll(playerBot, playerLeft, playerRight, playerTop);
                    playEndAnimation();
                }
            }
        };
        endAnimation.start();   //start above animation

        return byePane;
    }

    /**playEndAnimation
     * the design of the animation played at the end
     * this part of the animation begins after the
     * four player images be gone
     */
    private static void playEndAnimation() {        //animations happens after the above animation
        endAnimation.stop();                        //stop the loop above

        //set the radius to make the two circles visible
        exploW.setRadius(10);
        exploB.setRadius(5);

        //animation of black circle
        goBigB = new ScaleTransition(Duration.seconds(3), exploB);
        goBigB.setToX(500);
        goBigB.setToY(500);
        goBigB.setOnFinished(actionEvent -> thankYouMessage());     //after animation ended run that code

        //animation of the while circle
        goBigW = new ScaleTransition(Duration.seconds(2), exploW);
        goBigW.setToX(1000);
        goBigW.setToY(1000);
        goBigW.setOnFinished(actionEvent -> exploB.setRadius(10));
        goBigW.play();

        //animation of the line
        goLong = new ScaleTransition(Duration.seconds(1), line);
        goLong.setToX(1000);
        goLong.setToY(20);
        goLong.setOnFinished(actionEvent -> goBigB.play());
        goLong.play();

    }

    /**thankYouMessage
     * apart of the good bye animation.
     * this part of the program will run
     * after circles animation
     */
    private static void thankYouMessage() {
        byePane.getChildren().removeAll(exploB,exploW,line);        //remove the line, black circle and white circle

        disMessage = new FadeTransition(Duration.seconds(3), byeMessage);       //show to text
        disMessage.setFromValue(0.0);                                           //from 0% visibility
        disMessage.setToValue(1.0);                                             //to 100%
        disMessage.setOnFinished(actionEvent -> {
            for(int i = 0; i < endPlayerImage.length; i++){
                endA = new TranslateTransition(Duration.seconds(4), endPlayerImage[i]); //move all the player image in that array
                endA.setFromY(-67);
                endA.setToY(267);
                endA.setOnFinished(actionEvent1 -> {        //when finished
                    try{Thread.sleep(2000);}            //wait 2s
                    catch(InterruptedException e){e.printStackTrace();}
                    System.exit(0);     //exit program
                });
                endA.play();        //play above animation
            }
    });
        disMessage.play();      //play animation

    }

    /**getByeScene
     * for other part of the program to switch
     * to this scene
     * @return
     * byeScene which is the scene it self.
     */
    public static Scene getByeScene(){              //a way to get the information of the scene to other places
        byeScene = new Scene(createContent());
        return byeScene;
    }

}
