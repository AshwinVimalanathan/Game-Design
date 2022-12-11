/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author ashwi
 */
public class SmartEnemy extends GameObject{
    
    private Handler handler;
    private GameObject player;
    
    public SmartEnemy(int x, int y, ID id, Handler handler){
        super(x,y,id);
        
        this.handler = handler;
        
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player){
                player = handler.object.get(i);
            }
        }
        
    }
    
    public void tick(){
        x += velX;
        y += velY;
        
        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float dist = (float)Math.sqrt((x - player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));
        
        velX = ((-1/dist) * diffX);
        velY = ((-1/dist) * diffY);
        
        if(y <= 0 || y >= Game.HEIGHT-40){
            velY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH-20){
            velX *= -1;
        }
        
        handler.addObject(new Trial(x, y, ID.Trial, Color.green, 16, 16, 0.02f, handler));
    }
    
    public void render(Graphics g){
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, 16, 16);
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
}

