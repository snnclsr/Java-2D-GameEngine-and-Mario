package powerups;

import java.awt.Graphics;
import java.awt.Rectangle;

import Entities.GameObject;
import Helper.Texture;
import enums.ObjectID;
import game.Game;

public class Flower extends GameObject {
	
	private Texture texture = Game.getInstances();

	public Flower(float x, float y, ObjectID id) {
		super(x, y, id);
		velX = 0;
		velY = 0;
	}

	public void update() {
		
	}

	public void render(Graphics g) {
		g.drawImage(texture.mushroom[1],(int)x, (int)y,32,32, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	public Rectangle getBoundsTop() {
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
