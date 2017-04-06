package view;

import model.GameObject;
import model.ObjectId;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.Game;
import view.Texture;

/**
 * MovingBlock class, creates a moving block object that extend from Block
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class MovingBlock extends Block {
	/**
	 * The tex of this MovingBlock
	 */
	Texture tex = Game.getInstance();

	/**
	 * The type of this MovingBlock
	 */

	/**
	 * The xstart of this MovingBlock
	 */
	private float xstart;

	/**
	 * The constructor for MovingBlock, takes in two floats, an int, and an
	 * ObjectId to create a MovingBlock object
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
	public MovingBlock(float x, float y, int type, ObjectId id) {
		super(x, y, type, id);
		xstart = x;
	}

	/**
	 * The tick method for this MovingBlock
	 */
	@Override
	public void tick(LinkedList<GameObject> object) {

		if (facing > 0) {
			if (x > xstart - 50) {
				x -= 2;
			} else {
				facing = -1;
				x += 2;
			}
		} else {
			if (x < xstart + 50) {
				x += 2;
			} else {
				facing = 1;
				x -= 2;
			}
		}
	}

	/**
	 * Render the MovingBlock, gives the moving block object an image
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(tex.block[0], (int) x, (int) y, 32, 32, null);

	}

	/**
	 * Gets the AttackBound of this moving block object
	 */
	@Override
	public Rectangle getAttackBounds() {
		return null;
	}

}
