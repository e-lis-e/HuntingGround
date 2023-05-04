package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class KeyObj extends TheObject{
	
	public KeyObj() {
		
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
