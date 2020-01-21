/** MusicOutput.java
 *  Description: This file declare the path of all
 *               music files and plays the music when called
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.GameElement;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicOutput {
    /**         VARIABLES         **/
    //ARRAY:
    public static final String[] MUSICFILE = {
            "/BackgroundMusic.mp3",
            "/Shoot.wav",
            "/MouseClick.wav",
            "/EnemyExplosion.wav",
            "/PlayerExplosion.wav",
            "/SeekerSpawn.wav",
            "/WandererSpawn.wav"
    };

    //OBJECT:
    public static MediaPlayer playBack;
    private static AudioClip effects;

    /**         METHODS         **/
    /**playBackground
     * plays background music
     * @param musicLocation
     * location of the file
     */
    public static void playBackground(String musicLocation){
        playBack = new MediaPlayer(new Media(MusicOutput.class.getResource(musicLocation).toString()));
        playBack.setVolume(0.7);
        playBack.setOnEndOfMedia(new Runnable() {
            public void run() {
                playBack.seek(Duration.ZERO);
            }
        });
        playBack.play();
    }

    /**playEffects
     * plays background music
     * @param musicLocation
     * location of the file
     */
    public static void playEffects(String musicLocation){
        effects = new AudioClip(MusicOutput.class.getResource(musicLocation).toString());
        effects.play();
    }
}
