package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.Game;
import view.Texture;

/**
 * The spikes class that creates a spike for the game.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Spikes extends GameObject {
	/**
	 * The tex of this Spikes class
	 */
	Texture tex = Game.getInstance();
	/**
	 * The type of this Spikes class
	 */
	private int type;

	/**
	 * A constructor for Spikes, takes in two floats, a int, and a ObjectId in
	 * the parameters.
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
	public Spikes(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	/**
	 * A tick method for this Spike class
	 */
	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	/**
	 * Renders the spikes image
	 */
	@Override
	public void render(Graphics g) {
		if (type == 0) {
			g.drawImage(tex.spikes[0], (int) x, (int) y, 32, 32, null);
		}
		if (type == 1) {
			g.drawImage(tex.spikes[1], (int) x, (int) y, 32, 32, null);
		}

	}

	/**
	 * Gets the bounds for spikes
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	/**
	 * Gets the attack bounds for spikes
	 */
	@Override
	public Rectangle getAttackBounds() {
		return null;
	}

}
