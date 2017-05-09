package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Helper.Handler;
import Helper.Texture;
import enums.ObjectID;
import game.Animation;
import game.Game;

public class Boss22 extends GameObject {
	
	private enum BossStates{
		DEFENSIVE,
		NORMAL,
		ATTACK,
		DEAD
	};
	
	private Texture texture = Game.getInstances();
	private Animation bossAnimRight = new Animation(16,texture.boss[0],texture.boss[1],texture.boss[2],texture.boss[3]);
	private Animation bossAnimLeft = new Animation(16,texture.boss[4],texture.boss[5],texture.boss[6],texture.boss[7]);
	private Handler handler;
	
	private BossStates bossState = BossStates.NORMAL;
	private Random random = new Random();
	
	
	public static int health = 2000;
	private int nextAttackTime = 120;
	private int totalDefenseRight = 2;
	
	
	
	public Boss22(float x, float y,Handler handler, ObjectID id) {
		super(x, y, id);
		this.handler = handler;
		width = 48;
		height = 64;
		velX = 5;
	}
	public static void setNewHealth(int newHealth){
		health -= newHealth;
	}
	
	public static int getHealth(){
		return health;
	}
	public void setNextAttackTime(int attackTime){
		nextAttackTime = attackTime;
	}
	public int getNextAttackTime(){
		return nextAttackTime;
	}
	
	
	public void update(){
		x += velX;
		y += velY;
		
		if(velX < 0)		facing = -1;
		else if(velX > 0)	facing = 1;
		
		if(collision())
			velX *= -1;
		
		
		
		nextAttackTime--;
		if(nextAttackTime >0 && nextAttackTime <= 30){
			isAttackable = true;
			nextAttackTime = 120;
		}else{
			isAttackable = false;
		}
		
		if(health <= 500 && totalDefenseRight >0){
			health += 500;
			totalDefenseRight--;
			return;
		}
		
		if(health <= 100)
			bossState = BossStates.ATTACK;
		else if(health <= 0)
			bossState = BossStates.DEAD;
		
		if(bossState == BossStates.ATTACK){
			fireBullet();
		}
		bossAnimRight.runAnimation();
		bossAnimLeft.runAnimation();
		
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
	
	private void fireBullet(){
		int bulletSpawn = random.nextInt(10);
		if(bulletSpawn == 0)
			handler.addObject(new BossBullet((int)x ,(int)y,1,handler,ObjectID.BossBullet,facing*15,random.nextInt(91)));
	}
	
	
	
	public void render(Graphics g) {
		if(velX != 0){
			if(facing == 1)
				bossAnimRight.drawAnimation(g, (int)x,(int)y,(int)width,(int)height);
			else if(facing == -1)
				bossAnimLeft.drawAnimation(g, (int)x,(int)y,(int)width,(int)height);
		}
		else{
			if(facing == 1)
				g.drawImage(texture.boss[0],(int)x,(int)y,(int)width,(int)height,null);
			else
				g.drawImage(texture.boss[4],(int)x,(int)y,(int)width,(int)height,null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2) - (width/4)),(int) ((int)y + (height /2)),(int)width/2,(int)height/2);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int)((int)x + (width/2) - (width/4)),(int)y,(int) (int)width/2,(int)height/2);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) ((int)x + width),(int)y+5,(int)1,(int)height-10);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x,(int)y+5,(int)1,(int)height -10);
	}
}
