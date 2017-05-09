package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Entities.Player;
import Helper.Handler;
import Helper.ImageLoader;
import Helper.KeyInput;
import Helper.Texture;
import enums.ObjectID;

public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8056194281387030261L;
	public static final int WIDTH = 800 , HEIGHT = WIDTH /12 *9;
	
	public enum STATE {
		MENU,
		GAME,
		HELP,
		END
	};
	public static STATE gameState = STATE.MENU;
	
	public static int LEVEL = 1;
	
	
	private boolean running = false;
	private Thread thread;
	private Handler handler;
	private Camera cam;
	private static Texture texture;
	private Menu menu;
	private HUD hud;
	
	private BufferedImage level = null;
	
	public synchronized void start(){
		if(running)
			return;
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	public synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Game(){
		new Window(WIDTH,HEIGHT,"MARIO",this);
	
	}
	
	private void init(){
		cam = new Camera(0,0);
		handler = new Handler(cam);	
		texture = new Texture();
		hud = new HUD();
		menu = new Menu();
		ImageLoader loader = new ImageLoader();
		level = loader.loadImage("/secondLevel.png"); // loading the level
		AudioPlayer.loadSounds();
		handler.LoadImageLevel(level);
		//handler.addObject(new FliyingEnemy(100,100,ObjectID.FliyingEnemy));
		//handler.addObject(new GroundEnemy(100,400,handler,ObjectID.GroundEnemy));
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
	}
	
	public void run() {
		this.requestFocus();
		init();
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
        	long now = System.nanoTime();
        	delta += (now - lastTime) / ns;
        	lastTime = now;
        	while(delta >=1){
        		update();
        		delta--;
        	}
        	if(running)
        		render();
        	frames++;
            
        	if(System.currentTimeMillis() - timer > 1000) {
        		timer += 1000;
        		//System.out.println("FPS: "+ frames);
        		frames = 0;
        	}
        }
        stop();
	}
	private void update(){
		if(gameState == STATE.GAME){
//	AudioPlayer.getMusic("intro").play();
			handler.update();
			hud.update();
			for(int i=0;i<handler.object.size();i++){
				if(handler.object.get(i).getID() == ObjectID.Player){
					cam.update(handler.object.get(i));
					//System.out.println("Cam x : " + cam.getX());
					//System.out.println("Player x : " + handler.object.get(i).getX());
				}
			}
			if(Player.getHealth() <= 0){
				handler.clearLevel();
				gameState = STATE.END;
			}
		}else if(gameState == STATE.MENU || gameState == STATE.END){
			menu.update();
		}
			
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(new Color(25,191,224));
		g.fillRect(0, 0, WIDTH +32, HEIGHT +32);
		if(gameState == STATE.GAME){
			for(int i=0;i<5;i++){
				g.drawImage(texture.background[1], 100,100,null);
				g.drawImage(texture.background[3], 300,300,null);
			}
			hud.render(g);
			g2d.translate(cam.getX(),cam.getY());	// begin
			handler.render(g);	
			g2d.translate(-cam.getX(),-cam.getY());	// end
		}else if(gameState == STATE.MENU || gameState == STATE.HELP || gameState == STATE.END){
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public static Texture getInstances(){
		return texture;
	}
	
	public static void main(String[] args){
		new Game();
	}
}
