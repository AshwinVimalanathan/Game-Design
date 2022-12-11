/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;

import java.util.Random;


public class Spawn {
    
    private Handler handler;
    private HeadsUpDisplay hud;
    private Random r = new Random();
    private Game game;
    private int scoreKeep = 0;
    
    public Spawn(Handler handler, HeadsUpDisplay hud, Game game){
        this.handler = handler;
        this.game = game;
        this.hud = hud;
    }
    
    public void tick(){
        
        scoreKeep++;
        
        if(scoreKeep >= 250){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            
            if(game.diff == 0){
            if(hud.getLevel() == 2){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 3){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 4){    
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 5){    
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 6){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 7){    
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 8){    
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 10){
                handler.clearObject();
                handler.addObject(new EnemyBoss((Game.WIDTH/2) - 48, -120, ID.EnemyBoss, handler));
            }
            }else if(game.diff == 1){
            if(hud.getLevel() == 2){
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 3){
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 4){    
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 5){    
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 6){
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 7){    
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 8){    
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 10){
                handler.clearObject();
                handler.addObject(new EnemyBoss((Game.WIDTH/2) - 48, -120, ID.EnemyBoss, handler));
            }
            }
            
            
            
            
        }
    }
}
