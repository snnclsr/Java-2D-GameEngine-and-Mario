package Helper;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Entities.Block;
import Entities.Boss22;
import Entities.Collectables;
import Entities.Flag;
import Entities.GameObject;
import Entities.GroundEnemy;
import Entities.MysticBlock;
import Entities.Pipe;
import Entities.Player;
import enums.ObjectID;
import enums.PlayerState;
import game.Camera;
import game.Game;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObj;
	private Camera cam;
	
	private BufferedImage level2 = null;
	private BufferedImage bossRoom = null;
	
	
	public Handler(Camera cam){
		this.cam = cam;
		ImageLoader loader = new ImageLoader();
		level2 = loader.loadImage("/secondLevel.png");
		bossRoom = loader.loadImage("/bossRoom.png");
	}
	
	
	public void update(){
		
		for(int i=0;i<object.size();i++){
			tempObj = object.get(i);
			tempObj.update();
		}
	}
	
	public void render(Graphics g){
		for(int i=0;i<object.size();i++){
			tempObj = object.get(i);
			tempObj.render(g);
		}
	}

	
	
	public void addObject(GameObject obj){
		object.add(obj);
	}
	public void removeObject(GameObject obj){
		object.remove(obj);
	}
	// Remove all the objects from the game.
	public void clearLevel(){
		this.object.clear();
	}
	public void switchLevel(){
		clearLevel();
		cam.setX(0);
		Player.setPlayerState(PlayerState.SMALL);
		switch(Game.LEVEL){
			case 1: 
				LoadImageLevel(level2);
				break;
			case 2:
				LoadImageLevel(bossRoom);
				break;
		}
		Game.LEVEL++;
	}

	public void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
	
		System.out.println("Width : " + w + "\nHeight : " + h);
	
		for(int xx = 0;xx<h;xx++){
			for(int yy = 0;yy<w;yy++){
				int pixel = image.getRGB(xx,yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
			
				if(red == 255 && green == 255 && blue == 255)	addObject(new Block(xx*32,yy*32,0,ObjectID.Block));
				if(red == 0 && green == 0 && blue == 255)		addObject(new Player(xx*32,yy*32,this,cam,ObjectID.Player));
				if(red == 255 && green == 216 && blue == 0)		addObject(new Collectables(xx*32,yy*32,this,ObjectID.Collectables));
				if(red == 255 && green == 0 && blue == 0)		addObject(new Flag(xx*32,yy*32,ObjectID.Flag));
				if(red == 0 && green == 255 && blue == 255)		addObject(new MysticBlock(xx*32,yy*32,0,this,ObjectID.MysticBlock));
				if(red == 0 && green == 100 && blue == 255)		addObject(new MysticBlock(xx*32,yy*32,1,this,ObjectID.MysticBlock));
				if(red == 255 && green == 0 && blue == 220)		addObject(new GroundEnemy(xx*32,yy*32,this,ObjectID.GroundEnemy));
				if(red == 0 && green == 255 && blue == 0)		addObject(new Boss22(xx*32,yy*32,this,ObjectID.Boss22));
				if(red == 255 && green == 0 && blue == 130)		addObject(new Pipe(xx*32,yy*32,0,ObjectID.Pipe));
				if(red == 255 && green == 0 && blue == 120)		addObject(new Pipe(xx*32,yy*32,1,ObjectID.Pipe));
			}
		}
	}

}
