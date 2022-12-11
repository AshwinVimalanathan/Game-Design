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
public class HardEnemy extends GameObject{
    
    private Handler handler;
    
    private Random r = new Random();
    public HardEnemy(int x, int y, ID id, Handler handler){
        super(x,y,id);
        this.handler = handler;
        velX = 5;
        velY = 5;
    }
    
    public void tick(){
        x += velX;
        y += velY;
        
        if(y <= 0 || y >= Game.HEIGHT-40){
            if(velY < 0){
                velY = -(r.nextInt(7)+1)*-1;
            }else{
                velY = (r.nextInt(7)+1)*-1;
            }
        }
        if(x <= 0 || x >= Game.WIDTH-20){
            if(velX < 0){
                velX = -(r.nextInt(7)+1)*-1;
            }else{
                velX = (r.nextInt(7)+1)*-1;
            }
        }
        
        handler.addObject(new Trial(x, y, ID.Trial, Color.orange, 16, 16, 0.02f, handler));
    }
    
    public void render(Graphics g){
        g.setColor(Color.orange);
        g.fillRect((int)x, (int)y, 16, 16);
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 16, 16);
    }
}
