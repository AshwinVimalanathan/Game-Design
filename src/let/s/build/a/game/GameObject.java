/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

// this class is gonna be an abstract which we refer to as all of the game objects


import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    protected float x, y; // protecetd means it can only be accessed by which objects inherits the gameObject 
    //  now what's we gotta think about is how do we differentiate the player, enemy, and coin
    // TO DO THIS -> WE NEED TO CREATE AN ENUM
    protected ID id;
    protected float velX, velY;
    
    public GameObject(float x, float y, ID id){
        
        this.x = x;
        this.y = y;
        this.id = id;
        
        //Okay so what we basically did is create a constructor for our game object which When we create our PLAYER obj 
        // its gonna need a constructor. So when we create an instance of our game object, we need the three parameter component
        // and for that instance it assigned those local variable things to the 3 parameter values that were past in. 
    }
    
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();// this fucntion is used for collision check and intersections btwn two objects
    
    // these are basic getter & setter methods
    // they are basically we can change our x position by calling this setX method with a int x parameter
    // it assigns the aprameter to the local x variable of that instance
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
       this.y =y; 
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setId(ID id){
        this.id = id;
    }
    
    public ID getId(){
        return id;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    
    public void setVelY(int velY){
        this.velY = velY;
    }
    
    public float getVelX(){
        return velX;
    }
    
    public float getVelY(){
        return velY;
    }
    
    // now that these are not abstract, they are basically in the Player class but they are just hidden methods

}
