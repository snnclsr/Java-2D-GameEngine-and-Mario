package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import enums.ObjectID;

public abstract class GameObject {
	protected float x,y;
	protected float velX, velY;
	protected float width,height;
	protected int facing = 1;
	protected int type;
	protected int phaseTime;
	protected float gravity;
	
	protected ObjectID id;
	
	protected boolean isFalling = false;
	protected boolean isJumping = false;
	protected boolean isHitted = false;
	protected boolean isAttackable = false;
	protected boolean goingDownPipe = false;
	
	
	
	
	public GameObject(float x, float y,ObjectID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	// abstract methods
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	public abstract Rectangle getBoundsTop();
	public abstract Rectangle getBoundsLeft();
	public abstract Rectangle getBoundsRight();
	
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
	public float getVelX() {
		return velX;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public ObjectID getID(){
		return id;
	}

	public boolean isFalling() {
		return isFalling;
	}

	public void setFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}

	public boolean isJumping() {
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	public int getFacing(){
		return facing;
	}
	public void setHitted(boolean hitted){
		this.isHitted = hitted;
	}
	public boolean isHitted(){
		return this.isHitted;
	}

	public boolean isGoingDownPipe() {
		return goingDownPipe;
	}

	public void setGoingDownPipe(boolean goingDownPipe) {
		this.goingDownPipe = goingDownPipe;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	
	public void setGravity(float gravity){
		this.gravity = gravity;
	}
	public float getGravity(){
		return gravity;
	}
	
	public void setAttackable(boolean attackable){
		this.isAttackable = attackable;
	}
	public boolean isAttackable(){
		return isAttackable;
	}
	public int getPhaseTime(){
		return phaseTime;
	}
	public void setPhaseTime(int phaseTime){
		this.phaseTime = phaseTime;
	}
	
}
