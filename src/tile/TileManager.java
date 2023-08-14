package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	public Tiles[] tile;
	public int mapTileNum[] [];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tiles[10]; //we'll create ten types of tiles here and it's always changeable
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tiles();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, tile[0].image.getType());
			Graphics2D g2 = scaledImage.createGraphics(); //6:49
			
			tile[1] = new Tiles();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			tile[2] = new Tiles();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tiles();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4] = new Tiles();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision = true;
			
			tile[5] = new Tiles();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath) {
		
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
			
		}
	}
	public void draw (Graphics2D g2) {
		
		int worldcol = 0;
		int worldrow = 0;
		
		while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldcol][worldrow];
			
			int worldx = worldcol * gp.tileSize;
			int worldy = worldrow * gp.tileSize;
			int screenx = worldx - gp.player.worldx + gp.player.screenX;
			int screeny = worldy - gp.player.worldy + gp.player.screenY;
			
			if(worldx + gp.tileSize > gp.player.worldx - gp.player.screenX &&
			   worldx - gp.tileSize < gp.player.worldx + gp.player.screenX &&
			   worldy + gp.tileSize > gp.player.worldy - gp.player.screenY &&
			   worldy - gp.tileSize < gp.player.worldy + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenx, screeny, gp.tileSize, gp.tileSize, null);
			}
			worldcol++;
			
			if (worldcol == gp.maxWorldCol) {
				worldcol = 0;
				worldrow++;
	
			}
		}
		
	}
	
}
