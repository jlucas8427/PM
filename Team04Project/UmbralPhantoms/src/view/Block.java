package view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

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
public class Block extends GameObject {
	/**
	 * The texture for Block.
	 */

	/**
	 * The type of Block represented by an int.
	 */

	/**
	 * Constructs a Block at position (x,y), type, and id.
	 * 
	 * @param x
	 *            The x position of this Block.
	 * @param y
	 *            The y position of this Block.
	 * @param type
	 *            The type of Block.
	 * @param id
	 *            The id of this Block.
	 */
	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
	}

	/**
	 * Ticket inherited from GameObject.
	 */
	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	/**
	 * Renders the image for the Block.
	 */
	@Override
	public void render(Graphics g) {

	}

	/**
	 * Returns the bounding box of the Block.
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	/**
	 * AttackBounds method inherited from GameObject.
	 */
	@Override
	public Rectangle getAttackBounds() {
		return null;
	}

}
