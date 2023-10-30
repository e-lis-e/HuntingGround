package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	GamePanel gp;
	// the P in each one of those means "Pressed", but i tried to make things easier for me
	public boolean upP,downP, leftP, rightP;
	boolean checkDT = false;

	public KeyHandler(GamePanel gp){
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {	
		//don't actually need to use it now i guess
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upP = true;
		}
		if(code == KeyEvent.VK_S) {
			downP = true;
		}
		if(code == KeyEvent.VK_A) {
			leftP = true;
		}
		if(code == KeyEvent.VK_D) {
			rightP = true;
		}
		if(code == KeyEvent.VK_P) {
			if(gp.gameState == gp.playState){
				gp.gameState = gp.pauseState;
			}
			else if(gp.gameState == gp.pauseState){
				gp.gameState = gp.playState;

			}
		}
		
		//debug
		if(code == KeyEvent.VK_T) {
			if(checkDT == false) {
				checkDT = true;
			}
			else if(checkDT == true) {
				checkDT = false;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upP = false;
		}
		if(code == KeyEvent.VK_S) {
			downP = false;
		}
		if(code == KeyEvent.VK_A) {
			leftP = false;
		}
		if(code == KeyEvent.VK_D) {
			rightP = false;
		
	}

}
}
