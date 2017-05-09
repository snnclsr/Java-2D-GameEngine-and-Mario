package game;

import Entities.GameObject;

public class Camera {

	private float x;
	private float y;
	
	
	public Camera(float x,float y){
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	public void update(GameObject player){
		x = -player.getX() + Game.WIDTH /2;
	}
	
	
	
}
