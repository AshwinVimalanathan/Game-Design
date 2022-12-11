/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

import java.awt.Color;
import java.awt.Graphics;


public class HeadsUpDisplay {
    
    public int bounds =0;
    public static float HEALTH = 100;
    
    private float greenVal = 255; 
    
    private int score = 0;
    private int level = 1;
    
    public void tick(){
       
       // HEALTH = LetSBuildAGame.clamp(HEALTH, 0, 100); // the clamp method doesnt work too well
       // but health needs stay within the healthbar border
       HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds/2));
       greenVal = HEALTH * 2;
       greenVal = Game.clamp(greenVal, 0, 255);
       
       score++;
        
    }
    
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200 + bounds, 32);
        g.setColor(new Color(75, (int)greenVal, 0));
        g.fillRect(15,15,(int)HEALTH*2, 32);
        g.setColor(Color.white);
        g.drawRect(15,15,200 + bounds,32); // this draw rect, physica;;y draws rectangle to create a sort of border around the healthbar
        
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
        g.drawString("SPACE for shop", 15, 94);
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
        
    public int getLevel(){
        return level;
    }
    
    public void setLevel(int level){
        this.level = level;
    }
}
