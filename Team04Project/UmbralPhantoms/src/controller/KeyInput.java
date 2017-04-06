package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.GameObject;
import model.ObjectId;

/**
 * A KeyInput class that extends from KeyAdapter
 * 
 * @author Jerry Lucas, Richard A. Bravo, Joceyln Rocha, Thomas Robinson
 * @version 1.0
 */
public class KeyInput extends KeyAdapter {
	/**
	 * The handler for this KeyInput
	 */
	Handler handler;

	/**
	 * A constructor for this KeyInput, takes in a handler object in the
	 * parameters
	 * 
	 * @param Handler
	 *            handler
	 */
	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	/**
	 * KeyPressed method for this KeyInput
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// Stores the keyinput information that the user pressed
		int key = e.getKeyCode();

		// The key pressed buttons for Menu State
		if (Game.state == Game.STATE.MENU) {
			Music.load("/metal-small1.wav", "clk");
			Music.load("/shade3.wav", "press");
			Music.load("/churchbells.wav", "bells");
			if (key == KeyEvent.VK_DOWN) {
				if (Game.arrowY == 515) {
					Game.arrowY += 0;
				} else {
					Game.arrowY += 55;
				}
				Music.play("clk");
			}
			if (key == KeyEvent.VK_UP) {
				if (Game.arrowY == 460) {
					Game.arrowY -= 0;
				} else {
					Game.arrowY -= 55;
				}
				Music.play("clk");
			}
			if (key == KeyEvent.VK_ENTER) {
				if (Game.arrowY == 460) {
					Music.play("press");
					Game.state = Game.STATE.GAME;
					Music.play("bells");
				} else {
					System.exit(1);
				}
			}
		}

		// The key pressed buttons for Death State
		if (Game.state == Game.STATE.DEATH) {
			Music.load("/metal-small1.wav", "clk");
			Music.load("/shade3.wav", "press");
			if (key == KeyEvent.VK_DOWN) {

				Game.arrowY1 = 332;

				Music.play("clk");
			}
			if (key == KeyEvent.VK_UP) {

				Game.arrowY1 = 288;

				Music.play("clk");
			}
			if (key == KeyEvent.VK_ENTER) {
				if (Game.arrowY1 == 288) {// 460
					Game.state = Game.STATE.GAME;
					Music.load("/churchbells.wav", "bells");
					Music.play("bells");
				} else {
					System.exit(1);
				}
				Music.play("press");
			}

		}

		if (Game.state == Game.STATE.VICTORY) {
			Music.load("/metal-small1.wav", "clk");
			Music.load("/shade3.wav", "press");
			if (key == KeyEvent.VK_DOWN) {

				Game.arrowY2 = 220;
				Game.arrowX2 = -30;

				Music.play("clk");
			}
			if (key == KeyEvent.VK_UP) {

				Game.arrowY2 = 140;
				Game.arrowX2 = -55;

				Music.play("clk");
			}
			if (key == KeyEvent.VK_ENTER) {
				if (Game.arrowY2 == 140) {// 460
					Game.state = Game.STATE.GAME;
					Music.load("/churchbells.wav", "bells");
					Music.play("bells");
				} else {
					System.exit(1);
				}
				Music.play("press");
			}

		}

		// The key pressed buttons for Game State
		if (Game.state == Game.STATE.GAME) {
			Music.load("/swordswing.mp3", "sword");
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() == ObjectId.Player) {

					if (!tempObject.getHit()) {
						if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && !tempObject.getAttacking()) {
							tempObject.setFacing(1);
							if (tempObject.getCrouching()) {
								tempObject.SetVelX(0);
							} else {
								tempObject.SetVelX(5);
							}
						}
						if (((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)) && !tempObject.getAttacking()) {
							tempObject.setFacing(-1);
							if (tempObject.getCrouching()) {
								tempObject.SetVelX(0);
							} else {
								tempObject.SetVelX(-5);
							}
						}
						if ((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
								&& !tempObject.isJumping() && !tempObject.getAttacking()) {
							tempObject.setJumping(true);
							tempObject.SetVelY(-10);
						}
						if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
							tempObject.isCrouching(true);
						}
						if (key == KeyEvent.VK_F && !tempObject.isJumping() && !tempObject.getCrouching()) {
							tempObject.isAttacking(true);
							Music.play("sword");
						}
					}
				}
			}
		}

		// If the user presses the esc key, then terminate the Game
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

	/**
	 * The keyReleased method for KeyInput
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// Stores the keyinput information that the user pressed
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					tempObject.SetVelX(0);
				}
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
					tempObject.SetVelX(0);
				}
				if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
					tempObject.isCrouching(false);
				}
				if (key == KeyEvent.VK_F) {
					// tempObject.isAttacking(false);
				}
			}

		}
	}
}
