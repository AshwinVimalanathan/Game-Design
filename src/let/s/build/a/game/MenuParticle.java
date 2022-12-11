/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author ashwi
 */
public class MenuParticle extends GameObject{
    
    private Handler handler;
    
    Random r = new Random();
    
    //private int red = r. nextInt(255);
    //private int green = r.nextInt(255);
    //private int blue = r.nextInt(255);
    private Color col;
    
        public MenuParticle(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;
        
      
        velX = (r.nextInt(7 - -7) + -7);
        velY = (r.nextInt(7 - -7) + -7);
        
        if(velX == 0) velX =2;
        if(velY == 0) velY = 2;
        
        
        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }
    
    public void tick(){
        x += velX;
        y += velY;
        
        if(y <= 0 || y >= Game.HEIGHT-40){
            velY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH-20){
            velX *= -1;
        }
        
        handler.addObject(new Trial(x, y, ID.Trial, col, 16, 16, 0.06f, handler));
    }
    
    public void render(Graphics g){
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
}

