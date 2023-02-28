package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldx + entity.solidArea.x;
		int entityRightWorldX = entity.worldx + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldy + entity.solidArea.width;
		int entityBottomWorldY = entity.worldy + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileN.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileN.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileN.tile[tileNum1].collision == true || gp.tileN.tile[tileNum2].collision == true) {
				entity.collisionOn = true; 
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileN.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileN.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileN.tile[tileNum1].collision == true || gp.tileN.tile[tileNum2].collision == true) {
				entity.collisionOn = true; 
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileN.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileN.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileN.tile[tileNum1].collision == true || gp.tileN.tile[tileNum2].collision == true) {
				entity.collisionOn = true; 
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileN.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileN.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileN.tile[tileNum1].collision == true || gp.tileN.tile[tileNum2].collision == true) {
				entity.collisionOn = true; 
			}
			break;
		
		}
		
	}

}
