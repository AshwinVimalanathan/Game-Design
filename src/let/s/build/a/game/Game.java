/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package let.s.build.a.game;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;


// this is our MAIN CLASS, this is the GO-TO class, this is the class that will handle everything
//this is where we draw soemthing to screen and have a MAIN method for our game

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = 1550691097823471818L;
    
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH/12 * 9; // this is beleived to give to 16 by 9 ratio or something like that
    
    private Thread thread; //this is basically how the entire game is gonna run within this game
    //this is gonna be a single-threaded game. 
    
    public int diff = 0;
    
    //0 = normal
    //1 = hard;
    public static boolean paused = false;
    private Random r;
    private boolean running = false;
    private HeadsUpDisplay hud;
    private Handler handler;
    private Spawn spawn;
    private Menu menu;
    private Shop shop;
    
    public enum STATE{
        Menu, 
        Help,
        Game,
        Shop,
        Select,
        End
    };
    
    public static STATE gameState = STATE.Menu;
    
    public Game(){
        // this is before window is bc some errors would occur.
        handler = new Handler();
        hud = new HeadsUpDisplay();
        shop = new Shop(handler, hud);
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler,this)); // this just says HEY WE'RE GONNA BE USING KEYS SO LISTEN/WATCH OUT FOR THEM
        this.addMouseListener(menu);
        this.addMouseListener(shop);
        
        new Window(WIDTH, HEIGHT, "Let's a Build Game!", this);
        
        
        spawn = new Spawn(handler, hud, this);
        
        r = new Random();
        //for(int i = 0; i < 50; i++){
            
        if(gameState == STATE.Game){
        handler.addObject(new Player(/*r.nextInt(WIDTH)*/WIDTH/2-32,/*r.nextInt(HEIGHT)*/ HEIGHT/2-32,ID.Player, handler));
        
        


        //for(int i = 0; i < 5; i++){
        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
        //}
        }else{
            for(int i = 0; i < 19; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));

            }
        }

//handler.addObject(new Player(WIDTH/2+64, HEIGHT/2-32,ID.Player2));// this creates a player2 on the window 
        
        /*
        
        
        SOOOOOO NOW LEMME ASK YOU THIS, HOW DO WE DIFFERENTIATE THE PLAYERS IN OUR KEY INPUT
        HOW DO YOU KNOW THAT IF I HIT THE "W" KEY I WANT ONLY PLAYER 1 TO GO UP AND NOT PLAYER 2.
        
        THIS WAS ACTUALLY A CHALLENGE FOR HIM IN THE BEGINNING & HE DIDNT QUITE UNDERTSAND IT
        
        SOO WHAT YOU HAVE TO DO IS GO TO THE KEYPRESSED METHOD IN KEYINPUT CLASS AND LOOP IT THRU ALL OF OUR OBJECTS IN THE GAME
         AND ASK WHICH ONE HAS THE ID OF PLAYER. IF IT HAS ID OF PLAYER THEN MOVE THE PLAYER
        
        */
        //}
        
       // handler.addObject(new Player(200,200,ID.Player)); this is just added to show an extra pler 
        
        // now that mean we have got two objects as Players AKA the white boxes
    }
    public synchronized void start(){
        // in our start method. here we are just intializing the thread. And this refers to this instance of our game class 
        thread = new Thread(this);
        thread.start(); // this starts the thread
        running = true;
    }
    
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        // this is a game loop that is very important. this is the heartbeat of the game. this is what constantly updates the game
        // lots of ppl use game-loops like this. This is a VERRYYYYY POPULARRR GAMELOOP
        this.requestFocus(); // this line bc helps bc it means that i dont have to click the winodw every time to has access to moving the player using keyBoard controls
        long lastTime = System.nanoTime();
        double amountofTicks = 60.0;
        double ns = 1000000000 / amountofTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running){
                render();
            }
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
                    
        }
        stop();
    }
    
    private void tick(){
        if(gameState == STATE.Game){
            if(!paused){
        hud.tick();
        spawn.tick();
        handler.tick();
        if(HeadsUpDisplay.HEALTH <= 0){
            HeadsUpDisplay.HEALTH = 100;
            gameState = STATE.End;
            handler.clearObject();
            for(int i = 0; i < 19; i++){
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));

            }
        }}
        }else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select){
            menu.tick();
            handler.tick();
        }
    }
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();// now when we create this buffer strategy its gonna intially have a value of NULL 
        // use an if statement to check if bs equals NULL
        if(bs == null){
            this.createBufferStrategy(3);// whatever we put in this parameter is how many buffers it is gonna create. 3 is the most used/recommend value, you cannot have 2 and try not to go over 3
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);
        
        if(paused){
            g.setColor(Color.white);
            g.drawString("PAUSED", 100, 100);
        }
        
        if(gameState == STATE.Game){
        hud.render(g);
        handler.render(g);
        }else if(gameState == STATE.Shop){
            shop.render(g);
        }
            else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select){
            menu.render(g);
            handler.render(g);
        }
        g.dispose();
        bs.show();
    }
    
    public static float clamp(float var, float min, float max){
        
        if(var >= max){
            return var = max;
        }
        else if(var <= min){
            return var = min;
        }else
            return var;
    }
    
    public static void main(String[] args) {
        new Game();
    }
    
}
