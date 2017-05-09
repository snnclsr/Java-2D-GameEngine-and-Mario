package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Helper.Handler;
import enums.BossState;
import enums.ObjectID;

public class Boss extends GameObject{
	
	
	private int jumpTime = 0;
	private static int hp = 100;
	private float gravity;
	
	
	private boolean addJumpTime = false;
	
	private static BossState bossState = BossState.IDLE;
	private Handler handler;

	public Boss(float x, float y,Handler handler, ObjectID id) {
		super(x, y, id);
		this.handler = handler;
		this.setAttackable(true);
		width = 64;
		height = 64;
	}
	
	public static void setState(BossState newBossState){
		bossState = newBossState;
	}
	
	// This function set the new hp with -=
	public static void setNewHP(int newHP){
		hp -= newHP;
	}
	
	public void update() {
		x += velX;
		y += velY;
		
		if(collision())
			velX *= -1;
		
		if(hp <= 0)	die();
		phaseTime++;
		
		if((phaseTime >= 180 && (bossState == BossState.IDLE)) || 
				(phaseTime>= 60 && bossState != BossState.SPINNING))	chooseState();
		if(bossState == BossState.RECOVERING && phaseTime >= 180){
			bossState = BossState.SPINNING;
			phaseTime = 0;
		}
		if(phaseTime >= 360 && bossState == BossState.SPINNING){
			phaseTime = 0;
			bossState = BossState.IDLE;
		}
		
		if(bossState == BossState.IDLE || bossState == BossState.RECOVERING){
			setVelX(0);
			setVelY(0);
		}
		
		if(bossState == BossState.JUMPING || bossState == BossState.RUNNING)	isAttackable = true;
		else isAttackable = false;
		
		if(bossState != BossState.JUMPING){
			addJumpTime = false;
			jumpTime = 0;
		}
		
		if(addJumpTime){
			jumpTime++;
			if(jumpTime >= 30){
				addJumpTime= false;
				jumpTime = 0;
			}
			if(!isJumping && !isFalling){
				isJumping = true;
				gravity = 8.0f;
			}
		}
		
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Player){
				if(bossState == BossState.JUMPING){
					if(isJumping || isFalling){
						if(getX() >=tmpObj.getX()-4 && getX()<= tmpObj.getX()+4)
							setVelX(0);
						else if(tmpObj.getX() < getX())		setVelX(-3);
						else if(tmpObj.getX() > getX())	setVelX(3);		
					}
					else 
						setVelX(0);
				}else if(bossState == BossState.SPINNING){
					if(tmpObj.getX() < getX())		setVelX(-3);
					else if(tmpObj.getX() > getX())	setVelX(3);	
				}
			}
		}
	}
	
	private boolean collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Block){
				if(getBounds().intersects(tmpObj.getBounds()))
					return true;
			}
		}
		return false;
	}
	

	public void render(Graphics g) {
		if(bossState == BossState.IDLE || bossState == BossState.SPINNING)	g.setColor(Color.gray);
		else if (bossState == BossState.RECOVERING)							g.setColor(Color.red);
		else g.setColor(Color.ORANGE);
		//g.setColor(Color.red);
		g.fillRect((int)x, (int)y ,(int) width, (int)height);
	}

	private void die(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Boss){
				handler.object.remove(this);
			}
		}
	}
	
	public void chooseState() {
		Random r = new Random();
		int nextPhase = r.nextInt(2);
		if(nextPhase == 0){
			bossState = BossState.RUNNING;
			int dir = r.nextInt(2);
			if(dir == 0)	setVelX(-4);
			else 			setVelX(4);
		}else if(nextPhase == 1){
			bossState = BossState.JUMPING;
			isJumping = true;
			gravity = 8.0f;
		}
			
		phaseTime = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,64,64);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int)((int)x + (width/2) - (width/4)),(int)y,(int) (int)width/2,(int)height/2);
	}

	public Rectangle getBoundsLeft() {
		return null;
	}

	public Rectangle getBoundsRight() {
		return null;
	}

}
