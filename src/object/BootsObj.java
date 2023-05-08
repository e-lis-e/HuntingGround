package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class BootsObj extends TheObject {
	
	public BootsObj() {
		
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
