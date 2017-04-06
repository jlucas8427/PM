package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * An abstract class "GameObject" that sets the foundations for other game
 * objects
 * 
 * @authors Jerry Lucas, Richard A. Bravo, Robert Tuck, Thomas Robinson, Jocelyn
 *          Rocha
 * @version 1.0
 */
public abstract class GameObject {
	/**
	 * Protected variables for GameObject
	 */
	protected float x, y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean crouching = false;
	protected boolean attacking = false;
	protected boolean hit = false;
	protected int facing = 1;
	protected int health;

	/**
	 * Constructor for GameObject, takes in two float variables and one ObjectId
	 * variable.
	 * 
	 * @param x
	 * @param y
	 * @param id
	 */
	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	/**
	 * An abstract tick method for GameObject
	 * 
	 * @param object
	 */
	public abstract void tick(LinkedList<GameObject> object);

	/**
	 * An abstract render method for GameObject
	 * 
	 * @param g
	 */
	public abstract void render(Graphics g);

	/**
	 * An abstract getBounds method for GameObject
	 */
	public abstract Rectangle getBounds();

	/**
	 * An abstract getAttackingBounds method for GameObject
	 * 
	 * @return Rectangle
	 */
	public abstract Rectangle getAttackBounds();

	/**
	 * Gets the current X value of this GameObject
	 * 
	 * @return float x
	 */
	public float getX() {
		return x;
	}

	/**
	 * Gets the current Y value of this GameObject
	 * 
	 * @return float y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets the X variable for this GameObject
	 * 
	 * @param float
	 *            x
	 */
	public void SetX(float x) {
		this.x = x;
	}

	/**
	 * Sets the Y variable for this GameObject
	 * 
	 * @param float
	 *            y
	 */
	public void SetY(float y) {
		this.y = y;
	}

	/**
	 * Gets the current VelX value of GameObject
	 * 
	 * @return float velX
	 */
	public float getVelX() {
		return velX;
	}

	/**
	 * Gets the current VelY value of this GameObject
	 * 
	 * @return float velY
	 */
	public float getVelY() {
		return velY;
	}

	/**
	 * Sets the VelX variable for this GameObject
	 * 
	 * @param float
	 *            velX
	 */
	public void SetVelX(float velX) {
		this.velX = velX;
	}

	/**
	 * Sets the VelY variable for this GameObject
	 * 
	 * @param float
	 *            velY
	 */
	public void SetVelY(float velY) {
		this.velY = velY;
	}

	/**
	 * Gets the current boolean isFalling value of this GameObject
	 * 
	 * @return boolean falling
	 */
	public boolean isFalling() {
		return falling;
	}

	/**
	 * Sets the falling variable for this GameObject
	 * 
	 * @param boolean
	 *            falling
	 */
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	/**
	 * Gets the current boolean isJumping value of this GameObject
	 * 
	 * @return boolean jumping
	 */
	public boolean isJumping() {
		return jumping;
	}

	/**
	 * Sets the jumping variable for this GameObject
	 * 
	 * @param boolean
	 *            jumping
	 */
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	/**
	 * Gets the current Id value of this GameObject
	 * 
	 * @return ObjectId
	 */
	public ObjectId getId() {
		return id;
	}

	/**
	 * Determines whether the GameObject is "crouching"
	 * 
	 * @param boolean
	 *            crouch
	 */
	public void isCrouching(boolean crouch) {
		this.crouching = crouch;
	}

	/**
	 * Returns a boolean value from the objects crouching status
	 * 
	 * @return boolean
	 */
	public boolean getCrouching() {
		return crouching;
	}

	/**
	 * 
	 * @param boolean
	 *            attack
	 */
	public void isAttacking(boolean attack) {
		this.attacking = attack;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean getAttacking() {
		return attacking;
	}

	/**
	 * 
	 * @param int
	 *            i
	 */
	public void setFacing(int i) {
		this.facing = i;
	}

	public int getFacing() {
		return facing;
	}

	/**
	 * 
	 * @return boolean hit
	 */
	public boolean getHit() {
		return hit;
	}

}
