package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Helper.Texture;
import enums.ObjectID;
import game.Game;


public class Flag extends GameObject{

	private Texture texture = Game.getInstances();
	
	
	public Flag(float x, float y, ObjectID id) {
		super(x, y, id);
	}
	public void update() {
		
	}
	public void render(Graphics g) {
		g.drawImage(texture.flag[0], (int)x,(int) y,24,32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
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