/** Enemies.java
 *  Description: draws all types of enemies on the screen
 *               and moves them.
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.GameElement;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import static org.alexyuan.Output.Scene.GameScene.*;
import static org.alexyuan.Processing.Calculation.*;

public class Enemies {

    /**         VARIABLES         **/
    public static ArrayList<Node> seeker = new ArrayList<Node>();               //keep track of all enemies (type: seeker)
    public static ArrayList<Node> wanderer = new ArrayList<Node>();             //keep track of all enemies (type: wanderer)

    public static ArrayList<Double> seekerAngle = new ArrayList<Double>();        //angle of each enemy (type: seeker)

    public static ArrayList<Double> wandererMovementAngle = new ArrayList<Double>();    //for rotating the wanderer
    public static ArrayList<Double> wandererMovementX = new ArrayList<Double>();        //for moving the wanderer
    public static ArrayList<Double> wandererMovementY = new ArrayList<Double>();        //for moving the wanderer

    public static ArrayList<Double> seekerVelocityX = new ArrayList<Double>();    //velocity of seeker
    public static ArrayList<Double> seekerVelocityY = new ArrayList<Double>();    //velocity of seeker

    private static ImageView seekerImg = new ImageView();                    //image of seeker
    private static ImageView wandererImg = new ImageView();                  //image of wanderer

    private static final int SEEKERSPEED = 5;                                //speed of seeker
    private static final int WANDERERSPEED = 5;                              //speed of wanderer

    private static Node newSeeker;                                           //image node for seeker
    private static Node newWanderer;                                         //image node for wanderer

    /**         METHODS        **/

    /**spawnEnemy
     * setting up enemy information for display
     * and spawn them when condition is met
     */
    public static void spawnEnemy(){                                         //creating enemies and animation

        seekerImg = new ImageView(new Image(Enemies.class.getResourceAsStream("/Seeker.png")));
        newSeeker = seekerImg;                                                                                    //load image

        wandererImg = new ImageView(new Image(Enemies.class.getResourceAsStream("/Wanderer.png")));
        newWanderer = wandererImg;                                                                                //load image


        //spawn seekers
        if(Math.random() < 0.01){                                                                          //1% chance every frame to spawn a seeker
            newSeeker.relocate(Math.random() * 1027 + 40, Math.random() * 560 + 40);                //spawn it at a random position

            seeker.add(newSeeker);                                                                          //add to array list for tracking and manage

            seekerAngle.add(0.0);                                                                           //add to array list for tracking and manage
            seekerVelocityX.add(0.0);                                                                       //add to array list for tracking and manage
            seekerVelocityY.add(0.0);                                                                       //add to array list for tracking and manage

            gameRoot.getChildren().add(newSeeker);                                                          //display on screen
        }
        //spawn wanderer
        else if(Math.random() > 0.995) {                                                                  //1% chance every frame to spawn a wanderer
            newWanderer.relocate(Math.random() * 1027, Math.random() * 560);                        //spawn at random location

            wanderer.add(newWanderer);                                                                      //add to array list for tracking and manage

            wandererMovementAngle.add(0.0);                                                                 //add to array list for tracking and manage
            //calculate +1 or -1 to multiply to speed if enemy reach border
            wandererMovementX.add((double) (Math.round(Math.random()) * 2 - 1) * WANDERERSPEED);            //add to array list for tracking and manage
            wandererMovementY.add((double) (Math.round(Math.random()) * 2 - 1) * WANDERERSPEED);            //add to array list for tracking and manage

            gameRoot.getChildren().add(newWanderer);                                                        //display
        }

        //move seeker
        for(int i  = 0; i < seeker.size(); i++){                                                            //for every seeker on screen

            seekerAngle.set(i, calAngle(player.getLayoutX(),player.getLayoutY(),seeker.get(i).getLayoutX(),seeker.get(i).getLayoutY()));    //calculate angle to face player

            seekerVelocityX.set(i, calVelocityX(seekerAngle.get(i), SEEKERSPEED));                          //calculate how much to move
            seekerVelocityY.set(i, calVelocityY(seekerAngle.get(i), SEEKERSPEED));                          //calculate how much to move

            seeker.get(i).setRotate(seekerAngle.get(i));                                                    //rotate to face player

            seeker.get(i).relocate(seeker.get(i).getLayoutX() + seekerVelocityX.get(i), seeker.get(i).getLayoutY() + seekerVelocityY.get(i));   //move it

        }

        //move wanderer
        for(int i  = 0; i < wanderer.size(); i++){                                                  //for every wanderer

            wandererMovementAngle.set(i, wandererMovementAngle.get(i) + 4);                         //calculate angle for rotate in circular motion

            wanderer.get(i).setRotate(wandererMovementAngle.get(i));                                //rotate in circular motion

            wanderer.get(i).relocate(wanderer.get(i).getLayoutX() + wandererMovementX.get(i), wanderer.get(i).getLayoutY() + wandererMovementY.get(i)); //move

            if(wanderer.get(i).getLayoutX() < 0 || wanderer.get(i).getLayoutX() > 1027)             //if touch left and right border
                wandererMovementX.set(i, wandererMovementX.get(i) * -1);                            //move in opposite X

            if(wanderer.get(i).getLayoutY() < 0 || wanderer.get(i).getLayoutY() > 560)              //if touch top and down border
                wandererMovementY.set(i, wandererMovementY.get(i) * -1);                            //move in opposite Y
        }

    }
}
