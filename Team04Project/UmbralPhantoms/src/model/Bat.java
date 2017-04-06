package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import controller.Game;
import controller.Handler;
import view.Texture;

//Bats X axis movement cannot exceed 3 or 4, breaks collision for given hit box sizes
/**
 * This class represents a Bat enemy type.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Jocelyn Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Bat extends GameObject {
	/**
	 * The height and width of the bat.
	 */
	private float width = 68, height = 68;
	/**
	 * The modifications for the bat top and sides when jumping.
	 */
	/**
	 * The gravity for the bat.
	 */
	private float gravity = -0.5f;
	/**
	 * Maximum speed for the bat.
	 */
	private final float MAX_SPEED = -10;
	/**
	 * Indicates the direction the bat is facing.
	 */
	/**
	 * An instance of handler for the bat.
	 */
	private Handler handler;
	/**
	 * The textures for the bat.
	 */
	private Texture tex = Game.getInstance();

	/**
	 * Constructs a bat at position (x,y), with a handler, and id.
	 * 
	 * @param x
	 *            The x position of this Bat.
	 * @param y
	 *            The y position of this Bat.
	 * @param handler
	 *            The handler for this Bat.
	 * @param id
	 *            The id of this Bat.
	 */
	public Bat(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
	}

	/**
	 * Movement behavior for Bat after each game tick.
	 */
	@Override
	public void tick(LinkedList<GameObject> object) {

		x += velX;
		y += velY;

		if (velX < 0) {
			facing = -1;
		} else if (velX > 0) {
			facing = 1;
		}

		if (!jumping) {
			velY += gravity;

			if (velY < MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		if (jumping) {
			velY -= gravity;

			if (velY > (-1 * MAX_SPEED)) {
				velY = (-1 * MAX_SPEED);
			}
		}

		Collision(object);
		PlayerDetect(object);

	}

	/**
	 * Detects if a player is in its detection area.
	 * 
	 * @param object
	 *            A LinkedList of GameObjects
	 */
	private void PlayerDetect(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Player) {
				if ((tempObject.getX() - x) > -150 && (tempObject.getX() - x) < 0 && !attacking) {
					velX = -3;
					jumping = true;
					velY = 10;
					attacking = true;
				}
				if ((tempObject.getX() - x) < 150 && (tempObject.getX() - x) > 0 && !attacking) {
					velX = 3;
					jumping = true;
					velY = 10;
					attacking = true;
				}

				if (tempObject.getHit()) {
					// player hit interaction
				}
			}
		}
	}

	/**
	 * Determined behavior when Bat collides with another GameObject.
	 * 
	 * @param object
	 *            A LinkedList of GameObjects
	 */
	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.Spikes
					|| tempObject.getId() == ObjectId.MovingBlock) {
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 11;
					velY = 0;
					velX = 0;
					attacking = false;
				}
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height + 25;
					velY = 0;
					jumping = false;
				}

				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width + 11;
				}

				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 25;
				}
			}

			if (tempObject.getId() == ObjectId.Player) {
				if (getBoundsTop().intersects(tempObject.getAttackBounds())) {
					x = 50;
					y = 800;
				}
				if (getBounds().intersects(tempObject.getAttackBounds())) {
					x = 50;
					y = 800;
				}

				if (getBoundsRight().intersects(tempObject.getAttackBounds())) {
					x = 50;
					y = 800;
				}

				if (getBoundsLeft().intersects(tempObject.getAttackBounds())) {
					x = 50;
					y = 800;
				}
			}
		}
	}

	/**
	 * Renders the Bat to the screen.
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(tex.bat[0], (int) x, (int) y, 68, 68, null);
		/*
		 * for debugging hit boxes; g.setColor(Color.RED);
		 * g2d.draw(getBounds()); g2d.draw(getBoundsLeft());
		 * g2d.draw(getBoundsRight()); g2d.draw(getBoundsTop());
		 */
	}

	/**
	 * Returns the bounding box for the Bat.
	 * 
	 * @return Rectangle representing the bounding box
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x + 12, (int) ((int) y + (height / 2) - 2), (int) width / 2 + 5,
				(int) height / 4 - 6);
	}

	/**
	 * Returns the bounding box for the top of the Bat.
	 * 
	 * @return Rectangle representing the top bounding box
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + 12, (int) y + 20, (int) width / 2 + 5, (int) height / 4 - 5);
	}

	/**
	 * Returns the bounding box for the right of the Bat.
	 * 
	 * @return Rectangle representing the right bounding box
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 17), (int) y + 23, 4, 17);
	}

	/**
	 * Returns the bounding box for the left of the Bat.
	 * 
	 * @return Rectangle representing the left bounding box
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x + 8, (int) y + 23, 4, 17);
	}

	/**
	 * Method inherited from GameObject class.
	 */
	@Override
	public Rectangle getAttackBounds() {
		return null;
	}

}
