/** RemoveGameObject.java
 *  Description: This file checks when images are colliding and if so,
 *               remove the ones that are colliding. if bullet touch the
 *               sides of window it will also be removed
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Processing;

import static org.alexyuan.Output.GameElement.Bullets.*;
import static org.alexyuan.Output.GameElement.Enemies.*;
import static org.alexyuan.Output.GameElement.GameReactionAnimation.*;
import static org.alexyuan.Output.Scene.GameScene.*;
import static org.alexyuan.Output.GameElement.MusicOutput.*;

public class RemoveGameObject {

    /**bulletOutOfBoundary
     * when bullets get out of boundary
     * remove all information about that bullet
     */
    public static void bulletOutOfBoundary(){
        for(int i  = 0; i < bullets.size(); i++){

            if(bullets.get(i).getLayoutX() > 1039 || bullets.get(i).getLayoutX() < 0 || bullets.get(i).getLayoutY() > 600 || bullets.get(i).getLayoutY() < 0){

                //play bullet death animation
                loadBulletDeathAnimation(bullets.get(i).getLayoutX(),bullets.get(i).getLayoutY());

                //remove everything about that image
                gameRoot.getChildren().remove(bullets.get(i));
                bullets.remove(i);
                bulletAngle.remove(i);
                bulletVelocityX.remove(i);
                bulletVelocityY.remove(i);
            }
        }
    }

    /**shootEnemy
     * when bullets touch enemy
     * remove everything about
     * the enemy and the bullet
     */
    public static void shootEnemy(){
        for(int i  = 0; i < bullets.size(); i++){
            for(int z = 0; z < seeker.size(); z++) {
                if (bullets.get(i).getBoundsInParent().intersects(seeker.get(z).getBoundsInParent())) {

                    //remove everything about that bullet
                    gameRoot.getChildren().remove(bullets.get(i));
                    bullets.remove(i);
                    bulletAngle.remove(i);
                    bulletVelocityX.remove(i);
                    bulletVelocityY.remove(i);

                    //play score animation
                    scoreAnimation(seeker.get(z).getLayoutX(),seeker.get(z).getLayoutY(), 300);

                    //play bullet death animation
                    loadBulletDeathAnimation(seeker.get(z).getLayoutX(),seeker.get(z).getLayoutY());

                    //remove everything about that seeker
                    gameRoot.getChildren().remove(seeker.get(z));
                    seeker.remove(z);
                    seekerAngle.remove(z);
                    seekerVelocityX.remove(z);
                    seekerVelocityY.remove(z);

                    //play sound effect
                    playEffects(MUSICFILE[3]);

                    break;
                }
            }
        }
        for(int y = 0; y < bullets.size(); y++) {
            for (int x = 0; x < wanderer.size(); x++) {
                if (bullets.get(y).getBoundsInParent().intersects(wanderer.get(x).getBoundsInParent())) {

                    //remove everything about that bullet
                    gameRoot.getChildren().remove(bullets.get(y));
                    bullets.remove(y);
                    bulletAngle.remove(y);
                    bulletVelocityX.remove(y);
                    bulletVelocityY.remove(y);

                    //play score animation
                    scoreAnimation(wanderer.get(x).getLayoutX(),wanderer.get(x).getLayoutY(), 500);

                    //play bullet death animation
                    loadBulletDeathAnimation(wanderer.get(x).getLayoutX(),wanderer.get(x).getLayoutY());

                    //remove everything about that wanderer
                    gameRoot.getChildren().remove(wanderer.get(x));
                    wanderer.remove(x);
                    wandererMovementAngle.remove(x);
                    wandererMovementX.remove(x);
                    wandererMovementY.remove(x);

                    playEffects(MUSICFILE[3]);

                    break;
                }
            }
        }
    }

    /**waveKillEnemy
     * happens when the wave kills an enemy
     * remove all information about that enemy
     */
    public static void waveKillEnemy(){
        for(int i = 0; i < wave.size(); i++){
            for(int z = 0; z < seeker.size(); z++) {
                if (wave.get(i).getBoundsInParent().intersects(seeker.get(z).getBoundsInParent())) {

                    //play score animation
                    scoreAnimation(seeker.get(z).getLayoutX(), seeker.get(z).getLayoutY(), 300);

                    //remove everything about that seeker
                    gameRoot.getChildren().remove(seeker.get(z));
                    seeker.remove(z);
                    seekerAngle.remove(z);
                    seekerVelocityX.remove(z);
                    seekerVelocityY.remove(z);

                    playEffects(MUSICFILE[3]);

                    break;
                }
            }
        }
        for(int i = 0; i < wave.size(); i++){
            for(int z = 0; z < wanderer.size(); z++) {
                if (wave.get(i).getBoundsInParent().intersects(wanderer.get(z).getBoundsInParent())) {

                    //play score animation
                    scoreAnimation(wanderer.get(z).getLayoutX(), wanderer.get(z).getLayoutY(), 300);

                    //remove everything about that wanderer
                    gameRoot.getChildren().remove(wanderer.get(z));
                    wanderer.remove(z);
                    wandererMovementAngle.remove(z);
                    wandererMovementX.remove(z);
                    wandererMovementY.remove(z);

                    playEffects(MUSICFILE[3]);

                    break;
                }
            }
        }
    }

    /**playerDies
     * when player dies, remove all information
     * about the player and the enemy that the player
     * touch with.
     */
    public static void playerDies(){
        for(int i  = 0; i < seeker.size(); i++){
            if (player.getBoundsInParent().intersects(seeker.get(i).getBoundsInParent())) {

                //play score animation
                scoreAnimation(player.getLayoutX(), player.getLayoutY(), -1000);

                //relocate to random location
                player.relocate(Math.random() * 980 + 40, Math.random() * 500 + 40);

                //remove everything about that seeker
                gameRoot.getChildren().remove(seeker.get(i));
                seeker.remove(i);
                seekerAngle.remove(i);
                seekerVelocityX.remove(i);
                seekerVelocityY.remove(i);

                playEffects(MUSICFILE[4]);
                playerDeathAnimation();

            }
        }

        for(int i  = 0; i < wanderer.size(); i++){
            if (player.getBoundsInParent().intersects(wanderer.get(i).getBoundsInParent())) {

                scoreAnimation(player.getLayoutX(), player.getLayoutY(), -1000);

                player.relocate(Math.random() * 980 + 40, Math.random() * 500 + 40);

                //remove everything about that wanderer
                gameRoot.getChildren().remove(wanderer.get(i));
                wanderer.remove(i);
                wandererMovementAngle.remove(i);
                wandererMovementX.remove(i);
                wandererMovementY.remove(i);

                playEffects(MUSICFILE[4]);
                playerDeathAnimation();

            }
        }
    }
}
