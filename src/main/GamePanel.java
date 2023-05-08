package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.TheObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 size
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 size
	public final int maxScreenCol = 16; //column
	public final int maxScreenRow = 12; //row
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50; 
	
	//FPS
	int FPS = 60;
	
	//SYSTEM
	TileManager tileN = new TileManager(this);
	KeyHandler kH = new KeyHandler();
	Sound sound = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public ObjSetter oSetter = new ObjSetter(this);
	Thread gameThread;
	
	//ENTITY AND OBJECT
	public Player player = new Player(this,kH);
	//the 10 here means 10 slots, that can be made bigger if needed
	public TheObject obj[] = new TheObject[10];
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(kH); //can recognize key input
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		oSetter.setObject();
		
		playMusic(0); 
		
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.01666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				//1º WE UPDATE: Update info such as character position
				update();
				//2º WE DRAW: Draw the screen w the updated info
				repaint();
				delta--;
				drawCount++;

			}
			
		}
		
	}
	
	public void update() {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		//tile
		tileN.draw(g2);
		//object
		for(int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		//player
		player.draw(g2);
		
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playEffect(int i) {
		sound.setFile(i);
		sound.play();
	}
}
