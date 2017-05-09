package Helper;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Entities.Bullet;
import Entities.GameObject;
import Entities.Player;
import enums.ObjectID;
import enums.PlayerState;
import game.AudioPlayer;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Player){
				if(tmpObj.isGoingDownPipe())	return;	// if we are entering pipe we ignore the key inputs.
				if(key == KeyEvent.VK_RIGHT)	tmpObj.setVelX(5);
				if(key == KeyEvent.VK_LEFT)		tmpObj.setVelX(-5);
				if(key == KeyEvent.VK_SPACE && !tmpObj.isJumping()){
					tmpObj.setJumping(true);
					tmpObj.setVelY(-15);
					AudioPlayer.getMusic("jumpMusic").play();
				}
				if(key == KeyEvent.VK_F){
					if(Player.getPlayerState() == PlayerState.FIRE)
						handler.addObject(new Bullet(tmpObj.getX()-tmpObj.getWidth()*2,
													tmpObj.getY() + tmpObj.getHeight()/4,0,handler,ObjectID.Bullet,
													tmpObj.getFacing() * 10,45));
				}
			}
		}	
	}
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		for(int i=0;i<handler.object.size();i++){
			GameObject tmpObj = handler.object.get(i);
			if(tmpObj.getID() == ObjectID.Player){
				if(key == KeyEvent.VK_RIGHT)	tmpObj.setVelX(0);
				if(key == KeyEvent.VK_LEFT)		tmpObj.setVelX(0);
			}
		}	
	}
	
	
}
