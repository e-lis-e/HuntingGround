package main;

import object.ChestObj;
import object.DoorObj;
import object.KeyObj;

public class ObjSetter {
	
	GamePanel gp;
	
	public ObjSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new KeyObj();
		gp.obj[0].worldx = 23 * gp.tileSize;
		gp.obj[0].worldy = 7 * gp.tileSize;

		gp.obj[1] = new KeyObj();
		gp.obj[1].worldx = 23 * gp.tileSize;
		gp.obj[1].worldy = 40 * gp.tileSize;
		
		gp.obj[2] = new KeyObj();
		gp.obj[2].worldx = 38 * gp.tileSize;
		gp.obj[2].worldy = 9 * gp.tileSize;
		
		gp.obj[3] = new DoorObj();
		gp.obj[3].worldx = 10 * gp.tileSize;
		gp.obj[3].worldy = 11 * gp.tileSize;
		
		gp.obj[4] = new DoorObj();
		gp.obj[4].worldx = 8 * gp.tileSize;
		gp.obj[4].worldy = 28 * gp.tileSize;
		
		gp.obj[5] = new DoorObj();
		gp.obj[5].worldx = 12 * gp.tileSize;
		gp.obj[5].worldy = 22 * gp.tileSize;
		
		gp.obj[6] = new ChestObj();
		gp.obj[6].worldx = 10 * gp.tileSize;
		gp.obj[6].worldy = 8 * gp.tileSize;
		
		
		
		
		
		
	}
	

}
