package Entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Helper.Texture;
import enums.ObjectID;
import game.Game;

public class Pipe extends GameObject{

	private Texture texture = Game.getInstances();
	
	
	public Pipe(float x, float y, int type,ObjectID id) {
		super(x, y, id);
		this.type = type;
		if(type == 0){
			width = 64;
			height = 196;
		}
		else if(type == 1){
			width = 64;
			height = 128;
		}
	}

	public void update() {}

	public void render(Graphics g) {
		
//		Graphics2D g2d = (Graphics2D) g;
//		
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());
//		
		
		
		if(type == 0)
			g.drawImage(texture.pipe[0], (int)x, (int)y - 128, 64,196,null);
		else if(type == 1)
			g.drawImage(texture.pipe[0], (int)x,(int)y-64,64, 128,null);
	}

	public Rectangle getBounds() {
		if(type == 0)
			return new Rectangle((int)x,(int)y-128,64,196);
		else if(type == 1)
			return new Rectangle((int)x,(int)y -64,64,128);
		return null;
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int)((int)x + (width/2) - (width/4)),(int)y,(int) (int)width/2,(int)height/2);
		//return new Rectangle((int)x,(int) ((int)y - height/*-(height/1.5f))*/),(int)width,(int)height/8);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x,(int)y+5,(int)1,(int)height -10);
		//return new Rectangle((int)x,(int)y, (int)width,(int)height);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width),(int)y+5,(int)1,(int)height-10);
		//return new Rectangle((int)x,(int)y, (int)width,(int)height);
	}

}
