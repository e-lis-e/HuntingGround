package main;

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
		
		
	}
	

}
