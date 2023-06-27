package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.KeyObj;

public class UI {
	
	GamePanel gp;
	Font keyfont;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		keyfont = new Font("Arial", Font.PLAIN, 20);
		KeyObj key = new KeyObj();
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		
		g2.setFont(keyfont);
		g2.setColor(Color.white);
		g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x " + gp.player.hasKey, 74, 65);
		
		//if statement specific for the message
		if (messageOn == true) {
			g2.drawString(message, gp.tileSize/2, gp.tileSize/2);
		}
	}

}
