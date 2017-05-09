package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Helper.Handler;
import enums.ObjectID;

public class BossBullet extends Bullet {

	private int angle;
	private float rads;
	
	public BossBullet(float x, float y, int type,Handler handler, ObjectID id, int velX, int angle) {
		super(x, y, type,handler, id, velX, angle);
		rads = (float) (angle * Math.PI / 180);
	}
	
	public void update() {
		x += Math.cos(rads) * velX;
		y += Math.sin(rads) * velX;	
	}
	
	public void render(Graphics g){
		g.drawImage(texture.bossBullet[0],(int)x,(int)y,12,12,null);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
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
