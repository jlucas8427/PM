package view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import controller.Game;
import model.GameObject;
import model.ObjectId;

/**
 * A Block class that extends from GameObject. Creates a block used to create
 * the level.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Flag extends GameObject {
	/**
	 * Variables for Block class
	 */
	Texture tex = Game.getInstance();

	/**
	 * The constructor for Flag, takes in two floats, an int, and a ObjectId to
	 * create a flag
	 * 
	 * @param float
	 *            x
	 * @param float
	 *            y
	 * @param int
	 *            type
	 * @param ObjectId
	 *            id
	 */
	public Flag(float x, float y, int type, ObjectId id) {
		super(x, y, id);
	}

	/**
	 * The tick method of this Flag
	 */
	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	/**
	 * The render method for this flag
	 */
	@Override
	public void render(Graphics g) {

	}

	/**
	 * Gets the bounds of the flag
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	/**
	 * Gets the Attackbounds of the flag
	 */
	@Override
	public Rectangle getAttackBounds() {
		return null;
	}

}
