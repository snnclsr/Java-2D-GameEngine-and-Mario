package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Helper.Handler;
import Helper.Texture;
import enums.ObjectID;
import game.Animation;
import game.Game;

public class GroundEnemy extends Enemy{

	private float gravity = 2.0f;
	private float width = 24, height = 24;
	private static final float MAX_SPEED = 10f;
	
	private Texture texture = Game.getInstances();
	private Animation goombaWalk;
	private Handler handler;
	
	public GroundEnemy(float x, float y,Handler handler, ObjectID id) {
		super(x, y, id);
		this.handler = handler;
		velX = 2;
		goombaWalk = new Animation(5,texture.goomba[0],texture.goomba[1],texture.goomba[2]);
	}

	public void update() {
		x += velX;
		y += velY;
		
		if(shootable()){
			handler.object.remove(this);
		}
		
		if(collision()){
			velY = 0;
			isFalling = false;
		}
		if(collideWithBlock()){
			velX *= -1;
		}
		if(!collision() && !isFalling){
			velY = gravity; 
			isFalling = true;
		}
		if(isFalling || isJumping){
			velY += gravity;
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		goombaWalk.runAnimation();
	}
	private boolean collideWithBlock(){
		for(int i = 0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Block || tmpObj.getID() == ObjectID.Pipe){
				if(getBoundsRight().intersects(tmpObj.getBounds()))
					return true;
				if(getBoundsLeft().intersects(tmpObj.getBounds()))
					return true;
			}
		}
		return false;
	}

	private boolean collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Block)
				if(getBounds().intersects(tmpObj.getBounds())){
					return true;
				}	
		}
		return false;
	}
	private boolean shootable() {
		for(int i= 0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Bullet)
				if(getBoundsLeft().intersects(tmpObj.getBounds()) || getBoundsRight().intersects(tmpObj.getBounds()))
					return true;
		}
		return false;
	}
	
	
	public void render(Graphics g) {

//		Graphics2D g2d = (Graphics2D) g;
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());
		if(velX != 0)
			goombaWalk.drawAnimation(g, (int)x,(int)y,24,24);
		else
			g.drawImage(texture.goomba[0], (int)x,(int) y,24,24,null);
	}
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - (width/4)),(int) ((int)y + (height /2)),(int)width/2,(int)height/2);
	}
	public Rectangle getBoundsTop() {
 		return new Rectangle((int)((int)x + (width/2) - (width/4)),(int)y,(int) (int)width/2,(int)height/2);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x + width),(int)y+5,(int)1,(int)height-10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x,(int)y+5,(int)1,(int)height -10);
	}
}
