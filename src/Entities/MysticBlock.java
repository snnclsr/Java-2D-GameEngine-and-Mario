package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Helper.Handler;
import Helper.Texture;
import enums.ObjectID;
import enums.PlayerState;
import game.Game;
import powerups.Flower;
import powerups.Mushroom;

public class MysticBlock extends Block{
	
	private Texture texture = Game.getInstances();
	private Handler handler;
	
	private float spriteY = getY();
	private boolean poppedUp = false;
	
	public MysticBlock(float x, float y, int type,Handler handler,ObjectID id) {
		super(x, y, type, id);
		this.handler = handler;
	}
	
	public void update() {
		if(isHitted && !poppedUp && type == 1){
			spriteY--;
			if(spriteY <=y-32 && Player.getPlayerState() == PlayerState.SMALL){
				handler.addObject(new Mushroom(x,spriteY,handler,ObjectID.Mushroom));
				poppedUp = true;
			}
			else if(spriteY <=y-32 && Player.getPlayerState() == PlayerState.BIG){
				handler.addObject(new Flower(x,spriteY,ObjectID.Flower));
				poppedUp = true;
			}
		}
	}

	public void render(Graphics g) {
		if(!poppedUp){
			g.drawImage(texture.mysticBlocks[0],(int)x,(int)y,32,32, null);
		}
		if(!isHitted){
			g.drawImage(texture.mysticBlocks[0],(int)x,(int)y,32,32,null);	
		}
		else{
			g.drawImage(texture.mysticBlocks[4], (int)x,(int)y,32,32,null);;
		}
			
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
}
