package powerups;

import java.awt.Graphics;
import java.awt.Rectangle;

import Entities.GameObject;
import Helper.Handler;
import Helper.Texture;
import enums.ObjectID;
import game.Game;

public class Mushroom extends GameObject{
	
	private Texture texture = Game.getInstances();
	private Handler handler;
	
	private int width = 32, height = 32;
	private float gravity = 5f;
	private static float MAX_SPEED = 10;

	
	public Mushroom(float x, float y,Handler handler, ObjectID id) {
		super(x, y, id);
		this.handler = handler;
		velX = 2;
	}

	public void update() {
		x += velX;
		y += velY;
		if(collision()){
			velY = 0;
			isFalling = false;
		}
		if(collideWithBlock()){
			velX *= -1;
		}
		if(!collision() && !isFalling){
			velY = gravity;
		}
		if(isFalling || isJumping){
			velY += gravity;
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
	}
	
	private boolean collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Block || tmpObj.getID() == ObjectID.MysticBlock)
				if(getBounds().intersects(tmpObj.getBounds()))
					return true;
		}
		return false;
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
	
	
	public void render(Graphics g) {
		g.drawImage(texture.mushroom[0],(int)x,(int)y,32,32,null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
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
