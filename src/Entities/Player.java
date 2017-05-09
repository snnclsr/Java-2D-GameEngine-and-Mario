package Entities;


import java.awt.Graphics;
import java.awt.Rectangle;

import Helper.Handler;
import Helper.Texture;
import enums.BossState;
import enums.ObjectID;
import enums.PlayerState;
import game.Animation;
import game.AudioPlayer;
import game.Camera;
import game.Game;
import game.HUD;

public class Player extends GameObject{
	
	
	private static PlayerState playerState = PlayerState.SMALL;
	
	private float width = 24 , height = 24;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	private static int TOTAL_HEALTH = 2;
	
	private Handler handler;
	private Camera cam;
	private Texture texture = Game.getInstances();
	private Animation playerWalk = new Animation(8,texture.player[0],texture.player[1],
													texture.player[2],texture.player[3],texture.player[4]);
	private Animation playerWalkLeft = new Animation(8,texture.playerLeft[0],texture.playerLeft[1],texture.playerLeft[2],
													texture.playerLeft[3]);
	
	public Player(float x, float y,Handler handler,Camera cam, ObjectID id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
	}
	public static void setHealth(int health){
		TOTAL_HEALTH = health;
	}
	public static int getHealth() {
		return TOTAL_HEALTH;
	}

	public static PlayerState getPlayerState() {
		return playerState;
	}

	public static void setPlayerState(PlayerState playerState) {
		Player.playerState = playerState;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	public void update() {
		x += velX;
		y += velY;
		
//		double previousTime = 0;
//		double currentTime = System.currentTimeMillis();
//		float dt; // time pass between frames.
//		
//		previousTime = currentTime;
//		currentTime = System.currentTimeMillis();
//		dt = (float) (currentTime * previousTime );
//		
//		if(dt > 0.5f)
//			dt = 0.5f;
//		
//		x = (float) (x + velX * dt * 2.1f);
//		y = (float) (y + velY * dt);
		if(velX < 0)		facing = -1;
		else if(velX > 0)	facing = 1;
		
		if(isFalling || isJumping) {
			velY += gravity;
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		if(y > Game.WIDTH){
			TOTAL_HEALTH--;
		}
		collision();
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
	}
	// TODO :
	// Ayni kodu tekrar yazdim. Duzeltilmeli
	private void collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Block || tmpObj.getID() == ObjectID.Pipe){
				if(getBoundsTop().intersects(tmpObj.getBounds())){
					y = tmpObj.getY()+ 32;
					velY = 0;		
				}
				if(getBounds().intersects(tmpObj.getBounds())){
					y = tmpObj.getY() - height;
					velY = 0;
					isFalling = false;
					isJumping = false;
				}else 
					isFalling = true;
				
				// Right
				if(getBoundsRight().intersects(tmpObj.getBounds())){
					x = tmpObj.getX() - width;
				}
				// Left
				if(getBoundsLeft().intersects(tmpObj.getBounds())){
					x = tmpObj.getX() + 32;
				}
			}else if(tmpObj.getID() == ObjectID.Pipe) {
				if(getBoundsTop().intersects(tmpObj.getBounds())){
					y = tmpObj.getY()+ 32;
					velY = 0;		
				}
				if(getBounds().intersects(tmpObj.getBounds())){
					y = tmpObj.getY() + height;
					velY = 0;
					isFalling = false;
					isJumping = false;
				}else 
					isFalling = true;
				// Right
				if(getBoundsRight().intersects(tmpObj.getBounds())){
					x = tmpObj.getX() - width;
				}
				// Left
				if(getBoundsLeft().intersects(tmpObj.getBounds())){
					x = tmpObj.getX() + 150;
				}
				
			}else if(tmpObj.getID() == ObjectID.MysticBlock){
				if(getBoundsTop().intersects(tmpObj.getBounds())){
					// if we never hit the mystic block before.
					if(!tmpObj.isHitted()){
						tmpObj.setHitted(true);	
						y = tmpObj.getY()+ 32;
						velY = 0;
						AudioPlayer.getMusic("coinMusic").play();
						HUD.setScore(100);
					}
					// If we hit the block once.
					else{
						y = tmpObj.getY()+32;
						velY = 0;
					}
				}
				if(getBounds().intersects(tmpObj.getBounds())){
					if(!tmpObj.isHitted()){
						y = tmpObj.getY() - height;
						velY = 0;
						isFalling = false;
						isJumping = false;
					}else{
						y = tmpObj.getY() - height;
						isJumping = false;
					}
				}else
					isFalling = true;					
				// Right
				if(getBoundsRight().intersects(tmpObj.getBounds())){
					if(!tmpObj.isHitted())
						x = tmpObj.getX() - width;
				}
				// Left
				if(getBoundsLeft().intersects(tmpObj.getBounds())){
					if(!tmpObj.isHitted())
						x = tmpObj.getX() + 32;
				}
				
			}else if(tmpObj.getID() == ObjectID.Flag){
				if(getBounds().intersects(tmpObj.getBounds())){
					handler.switchLevel();
				}
			}else if(tmpObj.getID() == ObjectID.Mushroom){
				if(playerState == PlayerState.SMALL){
					System.out.println("Mario is : " + playerState);
					if(getBounds().intersects(tmpObj.getBounds())){
						AudioPlayer.getMusic("powerUp").play();
						width *= 1.5;
						height *= 1.5;
						handler.object.remove(tmpObj);
						playerState = PlayerState.BIG;
						System.out.println("Mario is : " + playerState);
					}
				}
			}else if(tmpObj.getID() == ObjectID.GroundEnemy){
				if(getBounds().intersects(tmpObj.getBoundsTop())){
					handler.object.remove(tmpObj);
				}
				if(getBounds().intersects(tmpObj.getBounds())){
					if(playerState == PlayerState.BIG){
						System.out.println("Mario is : " + playerState);
						width /= 1.5;
						height /= 1.5;
						playerState = PlayerState.SMALL;
						handler.object.remove(tmpObj);
						break;
					}else if(playerState == PlayerState.SMALL){
						System.out.println("Mario is : " + playerState);
						handler.object.remove(this);
						TOTAL_HEALTH--;
					}	
				}
			}else if(tmpObj.getID() == ObjectID.Flower){
				if(getBounds().intersects(tmpObj.getBounds())){
					handler.object.remove(tmpObj);
					playerState = PlayerState.FIRE;
				}
			}else if(tmpObj.getID() == ObjectID.Boss22){
				if(getBounds().intersects(tmpObj.getBoundsTop())){
					Boss22.health -= 100;
					System.out.println("Boss HP : " + Boss22.health);
				}else if(getBounds().intersects(tmpObj.getBounds())){
					TOTAL_HEALTH--;
					if(TOTAL_HEALTH == 0)
						handler.object.remove(this);
				}
			}
			else if(tmpObj.getID() == ObjectID.BossBullet){
				if(getBounds().intersects(tmpObj.getBounds())){
					System.out.println("Vurulduk");
					TOTAL_HEALTH--;
					if(TOTAL_HEALTH == 0)
						handler.object.remove(this);
					
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsTop());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());
//		
		if(velX != 0){
			if(facing == 1)
				playerWalk.drawAnimation(g,(int) x,(int) y, (int)width, (int)height);
			else
				playerWalkLeft.drawAnimation(g,(int) x,(int) y, (int)width, (int)height);
		}else{
			if(facing == 1)
				g.drawImage(texture.player[0],(int)x,(int)y,(int)width,(int)height,null);
			else
				g.drawImage(texture.playerLeft[0],(int)x,(int)y,(int)width,(int)height,null);
		}
			
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
