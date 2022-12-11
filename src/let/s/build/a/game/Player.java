/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;


public class Player extends GameObject{
    
    Random r = new Random();
    Handler handler;
    
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        //setX(100);
        // i created these non-abstract method in the game object class which are sorta hidden can accesible in the player class
        // which is why we are able to use setX(100) and not get an error. also no matter whatever x parameter i put, the x value will always be 100
        //velX = r.nextInt(5)+1;
        //velY = r.nextInt(5);
        
    }
    
    @Override
    public void tick(){
        x += velX;
        y += velY;
        
        if(x<=0) x = 0;
        if(x>=Game.WIDTH - 36) x = Game.WIDTH - 36;
        
        if(y<=0) y = 0;
        if(y>=Game.HEIGHT - 60) y = Game.HEIGHT - 60;
        
        //x = LetSBuildAGame.clamp(x, 0, LetSBuildAGame.WIDTH - 32);
        //y = LetSBuildAGame.clamp(y, 0, LetSBuildAGame.HEIGHT - 32);
        
        handler.addObject(new Trial(x, y, ID.Trial, Color.white, 32, 32, 0.1f, handler));

        collision();
    }
    
    private void collision(){
        for(int i = 0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy){ // tempObject is now all BasicEnemy
                if(getBounds().intersects(tempObject.getBounds())){ // if player.getBounds intersects w/ the enemy then decrease health
                    //collision code
                    HeadsUpDisplay.HEALTH -= 2;
                }
            }
        }
    }
    @Override
    public void render(Graphics g){
        //if(id == ID.Player) g.setColor(Color.white);
        //else if(id == ID.Player2) g.setColor(Color.blue);
        
        //Graphics2D g2d = (Graphics2D)g;// graphics2d has the method we need that grpahics doesnt
        
        //g.setColor(Color.red);
        //g2d.draw(getBounds());
        
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 32, 32);
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}
