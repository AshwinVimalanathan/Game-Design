/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas{
    
    private static final long serialVersionUID = -240840600533728354L;
    
    public Window(int width, int height, String title, Game game){
        // we created a JFrame and a JFrame is basically the frame of our window
        // its an in-built library within JAVA JRE, and soo we use that
        JFrame frame = new JFrame(title);
        // here we are setting the preferred, max, and min size are gonna be a dimension of width, height
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        // this closes the window properly. if you dont have this i wont actually stop the thread in the game operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // // makes resizing false
        frame.setResizable(false);
        // this isnt rlly needed.  but if we didnt our window would start in the top left 
        // but if we put it to NULL, it would start in the middle
        frame.setLocationRelativeTo(null);
        // here we add the game class into the frame
        frame.add(game);
        // set frame to actually be seen
        frame.setVisible(true);
        //runs teh start method created in the game class
        game.start();
        
    }
}
