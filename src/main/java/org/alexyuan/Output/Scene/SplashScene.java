/** SplashScene.java
 *  Description: The file draws the splash scene. Things included are:
 *               loading bar, my name, course code.....
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.Scene;

import javafx.application.Preloader;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SplashScene extends Preloader {

    /**         VARIABLES         **/
    private StackPane splashPane;
    private Stage splashStage;
    private Scene splashScene;

    private ProgressBar progressBar;

    //texts for display
    private final Text GAMETITLE = new Text("Geometry War"),
                       AUTHOR = new Text("By: Alex Yuan"),
                       CLASSCODE = new Text("ICS3U1"),
                       TEACHER = new Text("Mr. Sloim");

    private final ImageView splashBack = new ImageView(new Image(getClass().getResourceAsStream("/SplashSceneBack.jpg")));

    /**         METHODS        **/

    /**createContent
     * design and layout of the splash scene
     * @return
     * returns the scene so other part of the program can
     * use the design here
     */
    private Parent createContent(){

        splashPane = new StackPane();

        //setting of progress bar
        progressBar = new ProgressBar(0);
        progressBar.setTranslateX(0);
        progressBar.setTranslateY(180);
        progressBar.setPrefHeight(20);
        progressBar.setPrefWidth(800);
        progressBar.setStyle("-fx-padding: 0px;");

        //setting of the texts
        GAMETITLE.setFont(Font.font("Tw Cen MT", FontWeight.SEMI_BOLD,50));       //set font
        GAMETITLE.setTranslateX(0);
        GAMETITLE.setTranslateY(-120);
        GAMETITLE.setFill(Color.CYAN);
        AUTHOR.setFont(Font.font("Tw Cen MT", FontWeight.SEMI_BOLD,40));       //set font
        AUTHOR.setTranslateX(0);
        AUTHOR.setTranslateY(-60);
        AUTHOR.setFill(Color.CYAN);
        CLASSCODE.setFont(Font.font("Tw Cen MT", FontWeight.SEMI_BOLD,40));       //set font
        CLASSCODE.setTranslateX(0);
        CLASSCODE.setTranslateY(0);
        CLASSCODE.setFill(Color.CYAN);
        TEACHER.setFont(Font.font("Tw Cen MT", FontWeight.SEMI_BOLD,40));       //set font
        TEACHER.setTranslateX(0);
        TEACHER.setTranslateY(60);
        TEACHER.setFill(Color.CYAN);

        //display above
        splashPane.getChildren().addAll(splashBack,progressBar,GAMETITLE,AUTHOR,CLASSCODE,TEACHER);

        return splashPane;
    }

    /**start
     * JavaFX method for setting up the window
     * @param stage
     * the window
     */
    @Override
    public void start(Stage stage) {
        this.splashStage = stage;

        splashScene = new Scene(createContent());

        splashStage.setScene(splashScene);
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.show();
    }

    /**handleApplicationNotification
     * tells how much the progress bar should go
     * @param info
     * info is the stuff for loading
     */
    @Override
    public void handleApplicationNotification(PreloaderNotification info) {
        if(info instanceof ProgressNotification){
            progressBar.setProgress(((ProgressNotification) info).getProgress()/100.0);
        }
    }

    /**handleStateChangeNotification
     * hide the splash window when its loaded
     * @param info
     * info is again the stuff to load before program start
     */
    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        switch (type){
            case BEFORE_START:
                splashStage.hide();
                break;
        }
    }
}
