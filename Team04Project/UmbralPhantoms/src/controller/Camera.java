package controller;

import model.GameObject;

/**
 * The Camera class, sets up the camera to follow the character throughout the
 * game
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Jocelyn Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Camera {
	/**
	 * The x and y of this camera
	 */
	private float x, y;

	/**
	 * The constructor for this Camera, contains an x and y in the parameters
	 * 
	 * @param float
	 *            x
	 * @param float
	 *            y
	 */
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * The tick method for this camera class, takes in a GameObject in the
	 * parameters
	 * 
	 * @param Gameobject
	 *            player
	 */
	public void tick(GameObject player) {
		x = -player.getX() + Game.WIDTH / 2;
	}

	/**
	 * Sets the x of this camera class
	 * 
	 * @param float
	 *            x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Gets the current x value of this camera class
	 * 
	 * @return float
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets the y of this camera class
	 * 
	 * @param float
	 *            y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Gets the current y value of this camera class
	 * 
	 * @return float
	 */
	public float getY() {
		return y;
	}
}
