package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;


public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font mainfont, eifont;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameEnd = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		mainfont = new Font("Arial", Font.PLAIN, 40);
		eifont = new Font("Arial", Font.BOLD, 80);
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(mainfont);
		g2.setColor(Color.white);
		
		if (gp.gameState == gp.playState){
			//

		}
		else if (gp.gameState == gp.pauseState){
			drawPauseScreen();
		}
	}
	
	public void drawPauseScreen(){

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80f));
		String text = "PAUSED";
		int x = centerText(text);
		int y = gp.screenHeight;
		//need to discover how to blur the screen
		
		g2.drawString(text, x, y); 

	}

	public int centerText(String text){
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2; 
		return x;
	}

}
