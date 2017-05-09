package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Helper.Handler;
import Helper.Texture;
import enums.ObjectID;
import game.Animation;
import game.Game;

public class Bullet extends GameObject{

	// TODO : bulletlar sona kadar gidiyor. Dengeleme yapilabilir.
	private Animation anim;
	private Handler handler;
	Texture texture = Game.getInstances();
	
	public Bullet(float x, float y,int type, Handler handler,ObjectID id,int velX,int angle) {
		super(x, y, id);
		this.type = type;
		this.handler = handler;
		anim = new Animation(10,texture.fireball[0], texture.fireball[1],texture.fireball[2], texture.fireball[3]);
		this.velX = velX;
		
	}
	
	public void update() {
		x += velX;
		y += velY;
		if(collision())
			velX *= -1;
		anim.runAnimation();
	}
	// TODO : Animasyon calismiyor(Calistirdim ancak TODO'lar yapildi olarak isaretlenmiyor).
	public void render(Graphics g) {
		if(velX !=0 && type == 0)
			anim.drawAnimation(g, (int)x,(int) y,12,12);
		else
			g.drawImage(texture.fireball[0], (int)x, (int)y, null);
	}
	
	private boolean collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Block || tmpObj.getID() == ObjectID.Pipe)
				if(getBounds().intersects(tmpObj.getBounds())){
					return true;
				}	
		}
		return false;
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
