package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Helper.Handler;
import Helper.Texture;
import enums.ObjectID;
import game.Animation;
import game.AudioPlayer;
import game.Game;
import game.HUD;

public class Collectables extends GameObject{

	Texture texture = Game.getInstances();
	
	private Animation coinAnim;
	private Handler handler;
	
	public Collectables(float x, float y, Handler handler,ObjectID id) {
		super(x, y, id);
		this.handler = handler;
		coinAnim = new Animation(5,texture.coin[0] , texture.coin[1] , texture.coin[2],texture.coin[3]
							,texture.coin[4]);
	}

	public void update() {
		coinAnim.runAnimation();
		collision();
	}

	public void render(Graphics g) {
		coinAnim.drawAnimation(g,(int) x,(int) y,32,32);
	}

	private void collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj instanceof Player){
				if(getBounds().intersects(tmpObj.getBounds())){
					handler.object.remove(this);
					HUD.setScore(100);
					AudioPlayer.getMusic("coinMusic").play();
				}
			}
		}
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
