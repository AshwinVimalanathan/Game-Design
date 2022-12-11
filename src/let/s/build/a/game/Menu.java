/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import let.s.build.a.game.Game.STATE;

/**
 *
 * @author ashwi
 */
public class Menu extends MouseAdapter{
    
    private Game game;
    private Handler handler;
    private HeadsUpDisplay hud;
    private Random r = new Random();
    
    public Menu(Game game, Handler handler, HeadsUpDisplay hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }
    
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(game.gameState == STATE.Menu){
            
        // play button
        if(mouseOver(mx, my, 210, 150, 200, 64)){
            //game.gameState = STATE.Game;
            //handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
            //handler.clearObject();
            //handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            game.gameState = STATE.Select;
            return;
            
        }
        
        //help button
        if(mouseOver(mx, my, 210, 250, 200, 64)){
            game.gameState = STATE.Help;
            
        } 

        //quite button
        if(mouseOver(mx, my, 210, 350, 200, 64)){
            System.exit(1);
        }  
        
        }
        
        if(game.gameState == STATE.Select){
            
        // normal button
        if(mouseOver(mx, my, 210, 150, 200, 64)){
            game.gameState = STATE.Game;
            handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
            handler.clearObject();
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            
            game.diff = 0;
        }
        
        //hard button
        if(mouseOver(mx, my, 210, 250, 200, 64)){
            game.gameState = STATE.Game;
            handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
            handler.clearObject();
            handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            
            game.diff = 1;
            
        } 

        //back button
        if(mouseOver(mx, my, 210, 350, 200, 64)){
            game.gameState = STATE.Menu;
                return;
        }  
        
        }
        
        
        // back button for help
        if(game.gameState == STATE.Help){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = STATE.Menu;
                return;
            }
        }
        
        // try again button
        if(game.gameState == STATE.End){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                //handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player, handler));
                //handler.clearObject();
                //handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                
            }
        }
        
        
    }
    
    public void mouseRelased(MouseEvent e){
        
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx > x && mx < x+width){// this line basically says if our mx(mouseX) is anywhere inbetween the top left corner of the window & top right corner (basically saying if it anywhere inbetween those two points do the following
            if(my > y && my < y+height){// this line is the same as previous but for in the y direction
                return true;// this sortof iaginary box that we made is for the menu. SOOOO if we use the mouse and clicked the play button this condiitons is to ensure we clicked within the box
            }else return false;
        }else return false;
    }
    
    public void tick(){
        
    }
    public void render(Graphics g){
        if(game.gameState == STATE.Menu){
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Survivor", 210, 70);
        
        g.setFont(fnt2);
        g.setColor(Color.white);
        g.drawRect(210, 150, 200, 64);
        g.drawString("Play", 270, 190);
        
        g.setFont(fnt2);
        g.setColor(Color.white);
        g.drawRect(210, 250, 200, 64);
        g.drawString("Help", 270, 290);
        
        g.setColor(Color.white);
        g.drawRect(210, 350, 200, 64);
        g.drawString("Quit", 270, 390);
        }else if(game.gameState == STATE.Help){
            
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 245, 70);
            
            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies", 75, 200);
            
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }else if(game.gameState == STATE.End){
            
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 180, 70);
            
            g.setFont(fnt3);
            g.drawString("You lost at level " + hud.getLevel() + " with a score of: " + hud.getScore(), 175, 200);
            
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);
        }else if(game.gameState == STATE.Select){
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("SELECT DIFFICULTY", 68, 70);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Normal", 270, 190);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Hard", 270, 290);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }
    }
}
