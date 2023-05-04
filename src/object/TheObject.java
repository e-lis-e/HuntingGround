package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class TheObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldx, worldy;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenx = worldx - gp.player.worldx + gp.player.screenX;
		int screeny = worldy - gp.player.worldy + gp.player.screenY;
		
		
		if(worldx + gp.tileSize > gp.player.worldx - gp.player.screenX &&
		   worldx - gp.tileSize < gp.player.worldx + gp.player.screenX &&
		   worldy + gp.tileSize > gp.player.worldy - gp.player.screenY &&
		   worldy - gp.tileSize < gp.player.worldy + gp.player.screenY) {
			
			g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
		}
		
	}

}
