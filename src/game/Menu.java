package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Entities.Player;
import game.Game;
import game.Game.STATE;

public class Menu extends MouseAdapter{
	
	
	public void mousePressed(MouseEvent e){
		int mouseX = e.getX();
		int mouseY = e.getY();

		// Play button
		if(mouseOver(mouseX,mouseY,320,225,150,64)){
			Game.gameState = STATE.GAME;
			
		}
		// Help button
		if(mouseOver(mouseX,mouseY,320,325,150,64) && Game.gameState != STATE.GAME && Game.gameState != STATE.END){
			Game.gameState = STATE.HELP;
		}
		if(Game.gameState == STATE.HELP)
			if(mouseOver(mouseX,mouseY,100,100,150,64))
				Game.gameState = STATE.MENU;
		// Try Again button
		if(Game.gameState == STATE.END){
			if(mouseOver(mouseX,mouseY,305,395,225,100)){
				Player.setHealth(1);
				HUD.setScoreToZero();
				Game.gameState = STATE.MENU;
			}
		}
		// Quit button
		if(Game.gameState == STATE.MENU && Game.gameState != STATE.END && mouseOver(mouseX,mouseY,320,425,150,64) ){
			System.exit(1);
		}
	}
	
	private boolean mouseOver(int mx,int my,int x,int y,int width,int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else
				return false;
		}else
			return false;
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	public void update() {
	}

	public void render(Graphics g) {
		if(Game.gameState == STATE.MENU){
			Font font = new Font("arial",1,20);
			g.setFont(font);
			g.setColor(Color.white);
			
			// Play Button
			g.setColor(Color.white);
			g.drawRect(320, 225, 150, 64);
			g.drawString("Play",375,260);
			// Help Button
			g.setColor(Color.white);
			g.drawRect(320, 325, 150, 64);
			g.drawString("Help", 375,360);
			// Quit Button
			g.setColor(Color.white);
			g.drawRect(320, 425, 150, 64);
			g.drawString("Quit", 375, 460);			
		}
		else if(Game.gameState == STATE.HELP){
			Font font = new Font("arial", 1,20);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawRect(100, 100, 150, 64);
			g.drawString("Back", 150, 150);
		}
		else if(Game.gameState == STATE.END){
			Font font = new Font("arial", 1,50);
			Font font2 = new Font("arial", 1 , 30);
			g.setFont(font);
			g.setColor(Color.white);
			g.drawString("Game Over", 300, 150);
			
			g.setFont(font2);
			g.drawString("Score : " + HUD.getScore() , 350 , 300);
			
			g.setFont(font2);
			g.drawRect(305,395,225,100);
			g.drawString("Try Again" , 350 , 450);
		}
	}
}
	