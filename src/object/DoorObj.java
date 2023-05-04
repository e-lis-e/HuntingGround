package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class DoorObj extends TheObject{
	public DoorObj() {
		
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
