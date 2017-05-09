package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Entities.Player;

public class HUD {

	// Currently, 1 coin gives 100 score.
	private static int SCORE = 0;
	
	public static int getScore(){
		return SCORE;
	}
	public static void setScore(int score) {
		SCORE += score;
	}
	public static void setScoreToZero(){
		SCORE = 0;
	}
	public void update() {	
		
	}

	public void render(Graphics g) {
		//Font font = g.getFont().deriveFont(20.0f);
		// Both gives the same result(Except the font type).
		Font font = new Font("Verdana" , Font.BOLD ,20);
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString("Score : " + SCORE, 20, 85);
		
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString("Total Health : " + Player.getHealth(), 420, 85);
	}
}
