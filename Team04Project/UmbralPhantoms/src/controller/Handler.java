package controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import model.Bat;
import model.BufferedImageLoader;
import model.GameObject;
import model.ObjectId;
import model.Player;
import model.Spikes;
import view.Block;
import view.Flag;
import view.MovingBlock;

/**
 * A Handler class
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Jocelyn Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Handler {
	/**
	 * The object of this Handler
	 */
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	/**
	 * The tempObject of this Handler
	 */
	private GameObject tempObject;
	/**
	 * The cam of this Handler
	 */
	private Camera cam;
	/**
	 * The level of this Handler
	 */
	private BufferedImage level = null;

	/**
	 * The constructor for this Handler
	 * 
	 * @param cam
	 */
	public Handler(Camera cam) {
		this.cam = cam;
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png");
	}

	/**
	 * The tick method for this handler
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {

			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}

	/**
	 * Renders the game/menus for this handler.
	 * 
	 * @param Graphics
	 *            g
	 */
	public void render(Graphics g) {

		for (int i = 0; i < object.size(); i++) {

			tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	/**
	 * Clears the level of the Game for this handler.
	 */
	private void clearLevel() {
		object.clear();
	}

	/**
	 * Adds a object to the Game
	 * 
	 * @param GameObject
	 *            object
	 */
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	/**
	 * Removes an object in Handler
	 * 
	 * @param GameObject
	 *            object
	 */
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	/**
	 * A method that loads the level for this handler
	 * 
	 * @param BufferedImage
	 *            image
	 */
	public void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		System.out.println("width, height: " + w + " " + h);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && green == 255 && blue == 255) {
					addObject(new Block(i * 32, j * 32, 1, ObjectId.Block));
				}
				if (red == 255 && green == 255 && blue == 0) {
					addObject(new Spikes(i * 32, j * 32, 0, ObjectId.Spikes));
				}
				if (red == 0 && green == 0 && blue == 255) {
					addObject(new Player(i * 32, j * 32, this, cam, ObjectId.Player));
				}
				if (red == 255 && green == 0 && blue == 0) {
					addObject(new Bat(i * 32, j * 32, this, ObjectId.Bat));
				}
				if (red == 0 && green == 255 && blue == 0) {
					addObject(new Flag(i * 32, j * 32, 1, ObjectId.Flag));
				}
				if (red == 255 && green == 0 && blue == 255) {
					addObject(new MovingBlock(i * 32, j * 32, 1, ObjectId.MovingBlock));
				}
			}
		}
	}

	/**
	 * Resets the level when the plyer gets through the end of the level
	 */
	public void resetLevel() {
		clearLevel();
		cam.setX(0);

		LoadImageLevel(level);
		Game.state = Game.STATE.VICTORY;
		Music.stop("bells");
	}

	/**
	 * Resets the level when the player dies
	 */
	public void resetLevelDeath() {
		clearLevel();
		cam.setX(0);

		LoadImageLevel(level);
		Game.state = Game.STATE.DEATH;
		Music.stop("bells");
	}

}
