package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.KeyObj;

public class UI {
	
	GamePanel gp;
	Font keyfont, eifont;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameEnd = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		keyfont = new Font("Arial", Font.PLAIN, 40);
		eifont = new Font("Arial", Font.BOLD, 80);
		KeyObj key = new KeyObj();
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		if(gameEnd == true) {
			
			g2.setFont(keyfont);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			text = "Current Playtime: " + dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*4);
			g2.drawString(text, x, y);
			
			g2.setFont(eifont);
			g2.setColor(Color.yellow);
			text = "Congratuations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
		
		} else {
		g2.setFont(keyfont);
		g2.setColor(Color.white);
		g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x " + gp.player.hasKey, 74, 65);
		
		//timestamp
		playTime +=(double)1/60;
		g2.drawString("Time: "+ dFormat.format(playTime), gp.tileSize*11, 65);
		
		//if statement specific for the message
		if (messageOn == true) {
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, gp.tileSize/2, gp.tileSize/2);
			
			messageCounter++;
			if(messageCounter > 100) {
				messageCounter = 0;
				messageOn = false;
			}
		}
		
		
		}
	}

}
