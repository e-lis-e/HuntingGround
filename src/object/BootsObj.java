package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BootsObj extends TheObject {

	GamePanel gp;
	public BootsObj(GamePanel gp) {
		this.gp = gp;
		
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
