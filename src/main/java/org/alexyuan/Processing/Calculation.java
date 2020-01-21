/** Calculation.java
 *  Description: This file does all the 2D calculations
 *               required in this program
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */


package org.alexyuan.Processing;

public class Calculation {

    /**         METHODS        **/

    /**calAngle
     * calculate how much the object have to rotate
     * @param toX
     * the direction moving towards X
     * @param toY
     * the direction moving towards Y
     * @param fromX
     * the object's location X
     * @param fromY
     * the object's location Y
     * @return
     */
    public static double calAngle(double toX, double toY, double fromX, double fromY){
        return Math.toDegrees(Math.atan2(toX - fromX, fromY - toY)) - 90;
    }

    /**calVelocityX
     * calculates how much the object has to move each from to get from one point to another (X Value)
     * @param angle
     * the angle of rotation of the image
     * @param speed
     * pixel that image moves every second
     * @return
     * returns the speed to move every second (X)
     */
    public static double calVelocityX(double angle, double speed){
        return speed * Math.cos(Math.toRadians(angle));
    }

    /**calVelocityY
     * calculates how much the object has to move each from to get from one point to another (Y Value)
     * @param angle
     * the angle of rotation of the image
     * @param speed
     * pixel that image moves every second
     * @return
     * returns the speed to move every second (X)
     */
    public static double calVelocityY(double angle, double speed){
        return speed * Math.sin(Math.toRadians(angle));
    }

}
