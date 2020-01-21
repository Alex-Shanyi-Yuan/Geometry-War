/** MenuItemSetting.java
 *  Description: This file is objects of the layout of buttons
 *               and the title. This java file did all the setting to them
 *               so when called they won't need to be initialized again.
 *
 *  @Author: Alex Yuan
 *  @Version: 1.0 (Update: Jan 18th, 2020)
 */

package org.alexyuan.Output.GameElement;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuItemSetting {

    /**         CONSTRUCTORS        **/
    public static class Title extends StackPane {
        /**Title
         * displaying the title of the program
         * @param name
         * the title
         */
        public Title (String name){
            Rectangle titleRect = new Rectangle(250,60);                    //title outline box
            titleRect.setFill(null);                                                //color is transparent
            titleRect.setStrokeWidth(2);                                            //width of stroke
            titleRect.setStroke(Color.WHITE);                                       //color of stroke

            Text titleText = new Text(name);                                        //set text
            titleText.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,50));   //set font
            titleText.setFill(Color.WHITE);             //set color

            setAlignment(Pos.CENTER);                   //center the text
            getChildren().addAll(titleRect,titleText);  //display on window
        }
    }

    /**MenuBox
     * a box contains all the buttons
     * */
    public static class MenuBox extends VBox {

        /**MenuBox
         * display all buttons
         * @param items
         * buttons
         * */
        public MenuBox(MenuItem... items){
            getChildren().add(createSeparator());               //draw a line first before any button

            for(MenuItem item : items)                          //loop through all menu items user wish to add
                getChildren().addAll(item,createSeparator());   //display the button and a line after to separate it with next

        }
        /**createSeparator
         * create a line to separate each button
         * @return
         * returns a line as separator
         * */
        private Line createSeparator(){
            Line sep = new Line();              //initialize a line
            sep.setEndX(200);                   //set length of line
            sep.setStroke(Color.DARKGRAY);      //set color

            return sep;                         //return the line
        }
    }

    public static class MenuItem extends StackPane {
        /**MenuItem
         * creating the buttons
         * @param name
         * name of buttons for display
         * */
        public MenuItem(String name){
            LinearGradient gradient = new LinearGradient(0,0,1,0, true, CycleMethod.NO_CYCLE, new Stop[]{       //color setting
                    new Stop(0, Color.DARKVIOLET),          //0 - 10% of the button will show purple after mouse hover
                    new Stop(0.1, Color.BLACK),             //10% beyond of the button will show black after mouse hover
                    new Stop(0.9, Color.BLACK),             //90% before will show black when mouse hover
                    new Stop(1,Color.DARKVIOLET)            //90% to 100% of button will show purple when mouse hover
            });

            Rectangle itemBackGround = new Rectangle(200,30);        //background of the buttons
            itemBackGround.setOpacity(0.4);                             //40% see through

            Text itemName = new Text(name);             //text of the buttons
            itemName.setFill(Color.DARKGRAY);           //set color to dark gray
            itemName.setFont(Font.font("Tw Cen MT Condensed", FontWeight.SEMI_BOLD,22));    //set font

            setAlignment(Pos.CENTER);                       //center the text
            getChildren().addAll(itemBackGround,itemName);  //display the text and background of buttons on window

            setOnMouseEntered(event -> {
                itemBackGround.setFill(gradient);       //when mouse hover set the color to color when hover
                itemName.setFill(Color.BLACK);          //set text color to black
            });
            setOnMouseExited(event -> {
                itemBackGround.setFill(Color.BLACK);    //when mouse exit set the color back
                itemName.setFill(Color.DARKGRAY);       //set text back to normal color
            });
            setOnMousePressed(event -> {
                itemBackGround.setFill(Color.DARKVIOLET);   //when pressed set the whole button color to purple
            });
            setOnMouseReleased(event -> {
                itemBackGround.setFill(gradient);           //when released set to color hover
            });
        }
    }
}
