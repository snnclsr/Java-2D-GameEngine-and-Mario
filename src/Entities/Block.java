package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Helper.Texture;
import enums.ObjectID;
import game.Camera;
import game.Game;

public class Block extends GameObject {

	private Texture tex = Game.getInstances();
	
	private Camera cam;
	
	public Block(float x, float y,int type, ObjectID id) {
		super(x, y, id);
		this.type = type;
	}

	public void update() {
	}
	
	
	public void render(Graphics g) {
		if(type == 0)
			g.drawImage(tex.block[0],(int)x,(int)y,null);
	}

	public Rectangle getBounds() {

		return new Rectangle((int)x,(int)y , 32,32);
	}

	@Override
	public Rectangle getBoundsTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundsRight() {
		// TODO Auto-generated method stub
		return null;
	}

}
