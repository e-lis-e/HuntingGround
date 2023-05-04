package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class ChestObj extends TheObject {
	public ChestObj() {
		
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
