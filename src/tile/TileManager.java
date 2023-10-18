package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

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
			
			setup(0, "grass", false);
			setup(1, "wall", true);
			setup(2, "water", true);
			setup(3, "earth", false);
			setup(4, "tree", true);
			setup(5, "sand", false);
	}
			
		
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		
		try {
			tile[index] = new Tiles();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		} catch (IOException e){
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
				g2.drawImage(tile[tileNum].image, screenx, screeny, null);
			}
			worldcol++;
			
			if (worldcol == gp.maxWorldCol) {
				worldcol = 0;
				worldrow++;
	
			}
		}
		
	}
	
}
