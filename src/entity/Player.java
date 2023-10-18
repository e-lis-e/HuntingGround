package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler kH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public Player(GamePanel gp, KeyHandler kH) {
		this.gp = gp;
		this.kH = kH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
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

		up1 = setup("boy_up_1");
		up2 = setup("boy_up_3a");
		down1 = setup("boy_down_1 (1)");
		down2 = setup("boy_down_3a");
		left1 = setup("boy_left_1");
		left2 = setup("boy_left_2");
		right1 = setup("boy_right_1");
		right2 = setup("boy_right_2");
	}
	public BufferedImage setup(String imageName){
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/"+ imageName +".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;

	}
	public void update() {
		
		if (kH.upP == true || kH.downP == true || kH.leftP == true || kH.rightP == true ) {
			if(kH.upP == true) {
				
				direction = "up";
			}
			else if(kH.downP == true) {
				direction = "down";
			}
			else if (kH.leftP == true) {
				direction = "left";
			}
			else if (kH.rightP == true) {
				direction = "right";
			}
			
			//CHECK THE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickObj(objIndex);
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE 
			if(collisionOn == false) {
				
				switch(direction) {
				case "up": worldy -= speed; break;
				case "down": worldy += speed; break;
				case "left": worldx -= speed; break;
				case "right": worldx += speed; break;
					
				}
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
	
	public void pickObj(int i) {
		
		if (i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				gp.playEffect(1);
				hasKey++;
				gp.obj[i] = null; //this nullification makes the object disappear
				gp.ui.showMessage("You got a key!");
				break;
			case "Door":
				gp.playEffect(3);
				if(hasKey > 0) {
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("You opened one of the Sacred Doors!");
				} else {
					gp.ui.showMessage("You need a key, silly!");
				}
				
				break;
			case "Boots":
				gp.playEffect(2);
				speed += 2;
				gp.obj[i] = null;
				break;
			case "Chest":
				gp.ui.gameEnd = true;
				gp.stopMusic();
				gp.playEffect(4);
				break;
				
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
		g2.drawImage(image, screenX, screenY, null);
	}

}
