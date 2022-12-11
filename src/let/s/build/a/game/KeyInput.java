/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import let.s.build.a.game.Game.STATE;


public class KeyInput extends KeyAdapter{
    
    private Handler handler;
    private boolean [] keyDown = new boolean[4];
    
    Game game;
    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        
        this.game = game;
        
        keyDown[0] = false;
        keyDown[1] = false;        
        keyDown[2] = false;        
        keyDown[3] = false;        

    }
    
    public void keyPressed(KeyEvent e){
        
        int key = e.getKeyCode();
        
        for(int i = 0; i< handler.object.size(); i++){ //for this for loop, loops thru every object in the game 
            GameObject tempObject = handler.object.get(i); // so lets create a game obejct. now we can use temp obj as every obj in our game
            
            if(tempObject.getId() == ID.Player){
                // all the ley events for player 1
                
                if(key == KeyEvent.VK_W){
                    tempObject.setVelY(-handler.speed);
                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_S){
                    tempObject.setVelY(handler.speed);
                    keyDown[1] = true;
                }
                if(key == KeyEvent.VK_A){
                    tempObject.setVelX(-handler.speed);
                    keyDown[2] = true;
                }
                if(key == KeyEvent.VK_D){
                    tempObject.setVelX(handler.speed);
                    keyDown [3] = true;
                }
            }
            /*if(tempObject.getId() == ID.Player2){
                // all the ley events for player 2
                
                if(key == KeyEvent.VK_UP){
                    tempObject.setVelY((-5));       /// SIDE NOTE: TO HAVE BOTH PERSONS PRESS THE UP & W @ THE SAME TIME, IT WONT WORK----- MULTI-THREADING IS REQUIRED FOR THAT
                }
                if(key == KeyEvent.VK_DOWN){
                    tempObject.setVelY(5);
                }
                if(key == KeyEvent.VK_LEFT){
                    tempObject.setVelX(-5);
                }
                if(key == KeyEvent.VK_RIGHT){
                    tempObject.setVelX(5);
                }
            }*/
        }
        
        if(key == KeyEvent.VK_P){
            if(Game.gameState == STATE.Game){
            if(Game.paused) Game.paused = false;
            else Game.paused = true;
        }
        }
        if(key == KeyEvent.VK_ESCAPE){
            
            System.exit(1);
        }
        if(key == KeyEvent.VK_SPACE){
            if(Game.gameState == STATE.Game){
                Game.gameState = STATE.Shop;
            }else if(Game.gameState == STATE.Shop){
                Game.gameState = STATE.Game;
            }
        }
    }
        
        
    
    public void keyReleased(KeyEvent e){
     
        int key = e.getKeyCode();
        
        for(int i = 0; i< handler.object.size(); i++){ //for this for loop, loops thru every object in the game 
            GameObject tempObject = handler.object.get(i); // so lets create a game obejct. now we can use temp obj as every obj in our game
            
            if(tempObject.getId() == ID.Player){
                // all the ley events for player 1
                
                if(key == KeyEvent.VK_W){
                    //tempObject.setVelY(0);
                    keyDown[0] = false;
                }
                if(key == KeyEvent.VK_S){
                    //tempObject.setVelY(0);
                    keyDown[1] = false;
                }
                if(key == KeyEvent.VK_A){
                    //tempObject.setVelX(0);
                    keyDown[2] = false;
                }
                if(key == KeyEvent.VK_D){
                    //tempObject.setVelX(0);
                    keyDown[3] = false;
                }
                
                //vertical movement
                
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                
                //horizontal movement
                
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
            /*if(tempObject.getId() == ID.Player2){
                // all the ley events for player 2
                
                if(key == KeyEvent.VK_UP){
                    tempObject.setVelY(0);       /// SIDE NOTE: TO HAVE BOTH PERSONS PRESS THE UP & W @ THE SAME TIME, IT WONT WORK----- MULTI-THREADING IS REQUIRED FOR THAT
                }
                if(key == KeyEvent.VK_DOWN){
                    tempObject.setVelY(0);
                }
                if(key == KeyEvent.VK_LEFT){
                    tempObject.setVelX(0);
                }
                if(key == KeyEvent.VK_RIGHT){
                    tempObject.setVelX(0);
                }
            }*/
        }
    }
    
}
