/** Bullets.java
 *  Description: This file is for everything related with bullets (draw and animation)
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.GameElement;

import org.alexyuan.Processing.Calculation;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import static org.alexyuan.Input.PlayerAction.*;
import static org.alexyuan.Output.Scene.GameScene.*;

public class Bullets {

    /**         VARIABLES         **/
    public static ArrayList<Node> bullets = new ArrayList<Node>();          //keep track of all bullets
    public static ImageView bulletImg;                                   //bullet image
    public static Node newBullet;

    public static final int BULLETSPEED = 10;                            //speed of bullet
    public static ArrayList<Double> bulletAngle = new ArrayList<Double>();    //angle of bullet moving towards
    public static ArrayList<Double> bulletVelocityX = new ArrayList<Double>() , bulletVelocityY = new ArrayList<Double>();    //velocity for each bullet

    /**         METHODS        **/

    /**drawBullets
     * setting up bullets info for display
     */
    public static void drawBullets(){                                                                               //draw the bullets

        bulletImg = new ImageView(new Image(Bullets.class.getResourceAsStream("/Bullet.png")));                                                           //more loading
        newBullet = bulletImg;                                                                             //even more loading
        newBullet.relocate(player.getLayoutX()+player.getBoundsInLocal().getWidth()-18,player.getLayoutY()+12);     //draw it first at player's location


        bulletAngle.add(Calculation.calAngle(mouseX,mouseY,newBullet.getLayoutX(),newBullet.getLayoutY()));     //calculate the angle and add to array list
        newBullet.setRotate(bulletAngle.get(bulletAngle.size()-1));           //rotate it so it's facing the direction it is moving towards

        bulletVelocityX.add(Calculation.calVelocityX(bulletAngle.get(bulletAngle.size()-1), BULLETSPEED));      //calculate the velocity for x and add to array list
        bulletVelocityY.add(Calculation.calVelocityY(bulletAngle.get(bulletAngle.size()-1), BULLETSPEED));      //calculate the velocity for y and add to array list

        bullets.add(newBullet);                                                                                 //add it to array list to keep track of it

        gameRoot.getChildren().add(newBullet);                                                                  //display the bullets in game window
    }

    /**shootBullets
     * movements of the bullets
     */
    public static void shootBullets(){
        for(int i  = 0; i < bullets.size(); i++){                   //for every bullet
            bullets.get(i).relocate(bullets.get(i).getLayoutX() + bulletVelocityX.get(i), bullets.get(i).getLayoutY() + bulletVelocityY.get(i)); //relocate base on the velocity
        }
    }
}
