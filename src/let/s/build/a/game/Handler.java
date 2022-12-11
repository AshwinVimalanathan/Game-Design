/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

// this class is gonna maintain or update and render all of our objects in our room

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    
    LinkedList <GameObject> object = new LinkedList<GameObject>();
    
    public int speed = 5;
    
    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i); // this will allows us to get the ID of what object were are at
            
            tempObject.tick();
        }
        
    }
    
    public void render(Graphics g){
        for(int i = 0; i< object.size(); i++){
            GameObject tempObject = object.get(i);
            
            tempObject.render(g);
        }
    }
    
    public void clearObject(){
        for(int i = 0; i< object.size(); i++){
            GameObject tempObject = object.get(i);
            if(tempObject.getId() == ID.Player){
            object.clear();
            if(Game.gameState != Game.STATE.End){
            addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player,this));
            }
            }
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
            
    
}
