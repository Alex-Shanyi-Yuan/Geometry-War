/** GameReactionAnimation.java
 *  Description: This file is for animation that happens when a condition
 *               become true as the program process.
 *               ex. bullet death animation, player death.....
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.GameElement;

import org.alexyuan.Output.Scene.GameOver;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

import static org.alexyuan.Main.window;
import static org.alexyuan.Output.GameElement.GameInfoDisplay.*;
import static org.alexyuan.Output.Scene.GameScene.*;
import static org.alexyuan.Processing.Calculation.*;

public class GameReactionAnimation {

    /**         VARIABLES         **/
    private static int shakeX = 0;          //how much window shake by
    private static int shakeY = 0;          //how much window shake by

    public static long traceSpawnTime = 0;  //use to track how long passed since last traceSpawned

    public static long timeLastUpdate = 0;  //use to track how long passed since last time left changed

    public static int timeLeft = 0;         //value of time left
    public static Text timeText;            //text for showing time left

    private static Random random = new Random();        //for generate random values
    private static Rectangle rect = new Rectangle();    //particles for bullet death animation
    private static DropShadow rectEff;                  //effect for each particles
    private static Color particleColor;                 //color of each particle
    private static final int PARTICLESPEED = 2;         //speed particles move
    private static int numParticle = 0;                 //numbers of particle (max)
    private static ArrayList<Rectangle> particle = new ArrayList<Rectangle>();          //keep track of particles
    private static ArrayList<Double> particleAngle = new ArrayList<Double>();        //keep track of angle of those particles
    private static ArrayList<Double> particleVelocityX = new ArrayList<Double>();    //keep track of velocity X of particle
    private static ArrayList<Double> particleVelocityY = new ArrayList<Double>();    //keep track of velocity Y of particle
    private static ArrayList<Long> particleSpawnTime = new ArrayList<Long>();      //use to track how long passed since they spawn

    public static ArrayList<Circle> wave = new ArrayList<>();      //keep track of waves
    public static DropShadow waveEff;                              //effect for the wave

    /**         METHODS        **/

    /**scoreAnimation
     * Animation for the score
     * @param fromX
     * where an action happened (player die or enemy die)
     * @param fromY
     * where an action happened (player die or enemy die)
     * */
    public static void scoreAnimation(double fromX, double fromY, int amount){      //change the score

        Text text = new Text(amount + "");         //set text
        text.setTranslateX(fromX);                    //set location X
        text.setTranslateY(fromY);                    //set location Y
        text.setFill(Color.WHITE);                    //color
        score += amount;                              //update score


        gameRoot.getChildren().add(text);             //display

        TranslateTransition tt = new TranslateTransition(Duration.seconds(1),text);     //animation last 1s
        tt.setToX(900);                                 //move to
        tt.setToY(50);                                  //move to
        tt.setOnFinished(actionEvent -> {               //after arrive
            gameRoot.getChildren().remove(text);        //remove from window
            scoreText.setText(score + "");              //update text to for display
        });
        tt.play();
    }

    /**spawnTrace
     * Animation for the trace moving behind player
     * */
    public static void spawnTrace(){
        if(currentTime - traceSpawnTime > 100) {        //every 0.1 seconds

            ImageView trace = new ImageView(new Image(GameReactionAnimation.class.getResourceAsStream("/Player.png")));
            trace.setLayoutX(player.getLayoutX());                                      //set X to player X
            trace.setLayoutY(player.getLayoutY());                                      //set Y to player Y
            trace.setRotate(playerRotation);                                            //rotate with player
            gameRoot.getChildren().add(trace);                                          //display on window

            FadeTransition ft = new FadeTransition((Duration.seconds(0.5)), trace);     //animation is 0.5 seconds long
            ft.setFromValue(1);                                                         //from 100% see
            ft.setToValue(0);                                                           //0% see
            ft.setOnFinished(actionEvent -> gameRoot.getChildren().remove(trace));      //after finish remove from window
            ft.play();

            traceSpawnTime = System.currentTimeMillis();                                //reset time tracker

        }
    }

    /**textTimer
     * reduce time left by 1 every second
     * */
    public static void textTimer(){                         //time left setting
        if(currentTime - timeLastUpdate > 1000) {           //for every second
            timeLeft--;                                     //time left -1
            timeText.setText(timeLeft + "");                //reset the text to display

            if(timeLeft < 100){                             //when time left is below 100
                timeText.setLayoutX(1067/2 - 15);           //relocate the text to center
            }

            if(timeLeft < 10){                              //when time left is below 10
                timeText.setLayoutX(1067/2 - 7);            //relocate the center
            }

            timeLastUpdate = System.currentTimeMillis();    //reset the time tracker

            if(timeLeft == 0){                              //if there are no more time left
                window.setScene(GameOver.getGameOverScene());   //show game over scene
            }
        }
    }

    /**loadBulletDeathAnimation
     * setting up the animation for bullet death
     * @param fromX
     * @param fromY
     * Where the bullet dies
     * */
    public static void loadBulletDeathAnimation(double fromX, double fromY){                        //creating particles for bullet death animation
        particleColor = Color.hsb(random.nextInt(150) + 50, 1, 1, 0.75);    //generate random color

        numParticle = random.nextInt(5);                                                    //at most 5 particle from one bullet

        for (int i = 0; i < numParticle; i++) {                 //for every particle
            rect = new Rectangle(5, 2);                 //create new rectangle
            rect.setFill(particleColor);                        //set the color
            rectEff = new DropShadow(2, Color.GOLD);         //set the effect
            rectEff.setInput(new Glow(0.7));                //set how much to glow
            rect.setEffect(rectEff);                            //add effect to rectangle
            rect.relocate(fromX, fromY);                        //draw it at where bullet died

            particle.add(rect);                                 //add to array list to keep track of it
            particleAngle.add(calAngle(Math.random()*1067, Math.random()*600, fromX, fromY));       //calculate angle
            particleVelocityX.add(calVelocityX(particleAngle.get(particleAngle.size()-1), PARTICLESPEED));    //calculate velocity X
            particleVelocityY.add(calVelocityY(particleAngle.get(particleAngle.size()-1), PARTICLESPEED));      //calculate velocity Y
            particleSpawnTime.add(System.currentTimeMillis());

            gameRoot.getChildren().addAll(rect);        //display on window
        }
    }

    /**bulletDeathAnimation
     * move all the particles generated when
     * a bullet die
     * */
    public static void bulletDeathAnimation(){                  //play animatoin
        for(int x = 0; x < particle.size(); x++){               //for every particle
            particle.get(x).setRotate(particleAngle.get(x));    //rotate them by the angle

            //move
            particle.get(x).relocate(particle.get(x).getLayoutX() + particleVelocityX.get(x), particle.get(x).getLayoutY() + particleVelocityY.get(x));
            
            if(currentTime - particleSpawnTime.get(x) > 1000){      //after 1s
                gameRoot.getChildren().remove(particle.get(x));     //remove particle

                particle.remove(x);                                 //clear array
                particleAngle.remove(x);                            //clear array
                particleVelocityX.remove(x);                        //clear array
                particleVelocityY.remove(x);                        //clear array
                particleSpawnTime.remove(x);                        //clear array
            }
        }
    }

    /**playerDeathAnimation
     * animation when player dies
     */
    public static void playerDeathAnimation() {             //when player dies play this animation

        Timeline timelineX = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {     //use to play animation
            @Override
            public void handle(ActionEvent t) {
                if (shakeX == 0) {                      //always true when first run
                    window.setX(window.getX() + 10);    //window X + 10
                    shakeX = 1;                         //shakeX == 1 so else will run
                } else {
                    window.setX(window.getX() - 10);    //window X - 10
                    shakeX = 0;                         //shake == 0 so next time called it will run again
                }
            }
        }));

        timelineX.setCycleCount(2);                     //the code is ran twice
        timelineX.setAutoReverse(false);                //no auto reverse
        timelineX.play();                               //play animation


        Timeline timelineY = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {     //window animation in Y
            @Override
            public void handle(ActionEvent t) {
                if (shakeY == 0) {
                    window.setY(window.getY() + 10);                //window move by 10 pixel right
                    shakeY = 1;
                } else {
                    window.setY(window.getY() - 10);                //window move by 10 pixel left
                    shakeY = 0;
                }
            }
        }));

        timelineY.setCycleCount(2);                                 //two cycles
        timelineY.setAutoReverse(false);
        timelineY.play();                                           //play animation
    }

    /**waveAnimation
     * Animation moving the wave
     */
    public static void waveAnimation(){
        for(int i = 0; i < wave.size(); i++){                       //for all waves
            wave.get(i).setRadius(wave.get(i).getRadius() + 15);    //increase radius

            if(wave.get(i).getRadius() > 1067){                     //when the radius reach a certain point
                gameRoot.getChildren().remove(wave.get(i));         //remove from window
                wave.remove(i);                                     //remove from array list
            }
        }
    }

}