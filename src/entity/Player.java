package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler kH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler kH) {
		this.gp = gp;
		this.kH = kH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldx = gp.tileSize * 23;
		worldy = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_3a.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1 (1).png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_3a.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		
		if (kH.upP == true || kH.downP == true || kH.leftP == true || kH.rightP == true ) {
			if(kH.upP == true) {
				worldy -= speed;
				// alt way of writing: playery = playery - players
				direction = "up";
			}
			else if(kH.downP == true) {
				worldy += speed;
				direction = "down";
			}
			else if (kH.leftP == true) {
				worldx -= speed;
				direction = "left";
			}
			else if (kH.rightP == true) {
				worldx += speed;
				direction = "right";
			}
			
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
		}
		
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
			
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

}
