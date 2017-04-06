package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import controller.Camera;
import controller.Game;
import controller.Handler;
import controller.Music;
import view.Animation;
import view.Texture;

/**
 * The Player class, this is the character the player will be controlling This
 * class contains all the characteristics, bounds, and method needed to make the
 * controlling character functional for this game. It extends from the
 * GameObject class.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Player extends GameObject {
	/**
	 * The various data variables needed for this Player
	 */
	private float width = 112, height = 112;
	private int headmodtop = 20, headmodside = 0;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	private int hitcount = 0;
	private Handler handler;
	private int iframes = 100;
	Texture tex = Game.getInstance();
	private Animation playerIdle;
	private Animation playerIdleLeft;
	private Animation playerWalk;
	private Animation playerWalkLeft;
	private Animation playerJump;
	private Animation playerJumpLeft;
	private Animation playerCrouch;
	private Animation playerCrouchLeft;
	private Animation playerAttack;
	private Animation playerAttackLeft;
	private Animation playerBlank;

	/**
	 * 
	 * @param float
	 *            x
	 * @param float
	 *            y
	 * @param Handler
	 *            handler
	 * @param Camera
	 *            cam
	 * @param ObjectId
	 *            id
	 */
	public Player(float x, float y, Handler handler, Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		playerIdle = new Animation(6, tex.player[0], tex.player[1], tex.player[2], tex.player[1], tex.player[0]);
		playerWalk = new Animation(5, tex.player[3], tex.player[4], tex.player[5], tex.player[6], tex.player[7],
				tex.player[8], tex.player[9], tex.player[10]);
		playerWalkLeft = new Animation(5, tex.player[11], tex.player[12], tex.player[13], tex.player[14],
				tex.player[15], tex.player[16], tex.player[17], tex.player[18]);
		playerJump = new Animation(10, tex.player[19], tex.player[20], tex.player[21], tex.player[22]);
		playerIdleLeft = new Animation(6, tex.player[23], tex.player[24], tex.player[25], tex.player[24],
				tex.player[23]);
		playerJumpLeft = new Animation(10, tex.player[26], tex.player[27], tex.player[28], tex.player[29]);
		playerCrouch = new Animation(8, tex.player[30], tex.player[31]);
		playerCrouchLeft = new Animation(8, tex.player[32], tex.player[33]);
		playerAttack = new Animation(4, tex.player[34], tex.player[35], tex.player[36], tex.player[37], tex.player[38],
				tex.player[39]);
		playerAttackLeft = new Animation(4, tex.player[40], tex.player[41], tex.player[42], tex.player[43],
				tex.player[44], tex.player[45]);
		playerBlank = new Animation(5, tex.player[46]);
		playerBlank.setFirst();
	}

	/**
	 * The tick method for this Player
	 */
	@Override
	public void tick(LinkedList<GameObject> object) {
		if ((crouching && !jumping) || attacking && !jumping) {
			velX = 0;
		}

		if (playerAttack.getCount() != 0 || playerAttackLeft.getCount() != 0) {
			velX = 0;
		}

		if (velY > 1) {
			jumping = true;
		}

		x += velX;
		y += velY;

		if (hit || hitcount > 0) {

			if (hitcount < iframes) {
				hitcount++;
			} else {
				hit = false;
				hitcount = 0;
			}

		}
		if (playerAttack.getCount() != 0 || playerAttackLeft.getCount() != 0) {
			if (facing > 0) {
				playerAttack.runAnimationOnce();
			} else {
				playerAttackLeft.runAnimationOnce();
			}
		} else if (!crouching && !jumping && attacking) {
			if (facing > 0) {
				playerAttack.runAnimation();
			} else {
				playerAttackLeft.runAnimation();
			}
		} else if (crouching && !jumping) {
			if (facing > 0) {
				playerCrouch.runAnimationOnce();
			} else {
				playerCrouchLeft.runAnimationOnce();
			}
		} else if ((velX > 0) && !jumping) {
			playerWalk.runAnimation();
		} else if ((velX < 0) && !jumping) {
			playerWalkLeft.runAnimation();
		} else if ((velX == 0) && !jumping && !crouching) {
			if (facing > 0) {
				playerIdle.runAnimation();
			} else {
				playerIdleLeft.runAnimation();
			}
		} else {
			if (facing > 0) {
				playerJump.runAnimationOnce();
			} else {
				playerJumpLeft.runAnimationOnce();
			}
		}
		if (!jumping) {
			playerJump.setCount(0);
			playerJump.setIndex(20);
			playerJumpLeft.setCount(0);
			playerJumpLeft.setIndex(20);
			headmodtop = 20;
			headmodside = 0;
		} else {
			headmodtop = 5;
			headmodside = 15;
		}

		if (!crouching) {
			playerCrouch.setCount(0);
			playerCrouchLeft.setCount(0);
			playerCrouch.setIndex(20);
			playerCrouchLeft.setIndex(20);
		}

		if (playerAttack.getCount() == playerAttack.getFrames()
				|| playerAttackLeft.getCount() == playerAttackLeft.getFrames() || hit) {
			attacking = false;
			playerAttack.setCount(0);
			playerAttackLeft.setCount(0);
			playerAttack.setIndex(20);
			playerAttackLeft.setIndex(20);
		}

		if (falling || jumping) {
			velY += gravity;

			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}

		Collision(object);

	}

	/**
	 * The Collision method for this Player, sets up the various collision bound
	 * for this Player
	 * 
	 * @param LinkedList<GameObject>
	 *            object
	 */
	private void Collision(LinkedList<GameObject> object) {
		Music.load("/door.wav", "door");
		Music.load("/grunt.mp3", "grunt");
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Block || tempObject.getId() == ObjectId.MovingBlock) {
				if (getBoundsTop().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 28;
					velY = 0;
				}
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height + 11;
					velY = 0;
					falling = false;
					jumping = false;
					if (hit) {
						hit = false;
						velX = 0;
					}
				} else {
					falling = true;
				}

				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width + 29;
				}

				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 3;
				}
			}

			if (tempObject.getId() == ObjectId.Spikes) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.resetLevelDeath();
					Music.play("grunt");
				}
			}

			if (tempObject.getId() == ObjectId.Bat && (hitcount == 0)) {
				if (getBoundsTop().intersects(tempObject.getBounds()) || getBounds().intersects(tempObject.getBounds())
						|| getBoundsRight().intersects(tempObject.getBounds())
						|| getBoundsLeft().intersects(tempObject.getBounds())) {

					hit = true;
					hitcount = 0;
					velX = facing * -5;
					jumping = true;
					velY = -5;
				}
			}

			if (tempObject.getId() == ObjectId.Flag) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.resetLevel();
					Music.play("door");
				}
			}

		}
	}

	/**
	 * The render method for this Player, renders out the various animations for
	 * this Player
	 * 
	 * @param Graphics
	 *            g
	 */
	@Override
	public void render(Graphics g) {
		if ((hitcount % 2) == 1) {
			playerBlank.drawAnimation(g, (int) x, (int) y, 112, 112);
		} else if (playerAttack.getCount() != 0 || playerAttackLeft.getCount() != 0 && !hit) {
			if (facing == 1) {
				playerAttack.drawAnimation(g, (int) x - 27, (int) y - 17, 182, 128);
			} else {
				playerAttackLeft.drawAnimation(g, (int) x - 44, (int) y - 17, 182, 128);
			}
		} else if (!crouching && !jumping && attacking && !hit) {
			if (facing == 1) {
				playerAttack.drawAnimation(g, (int) x - 27, (int) y - 17, 182, 128);
			} else {
				playerAttackLeft.drawAnimation(g, (int) x - 44, (int) y - 17, 182, 128);
			}
		} else if (crouching && !jumping) {
			if (facing == 1) {
				playerCrouch.drawAnimation(g, (int) x, (int) y, 112, 112);
			} else {
				playerCrouchLeft.drawAnimation(g, (int) x, (int) y, 112, 112);
			}
		} else if (jumping) {
			if (facing == 1) {
				playerJump.drawAnimation(g, (int) x, (int) y, 112, 112);
			} else {
				playerJumpLeft.drawAnimation(g, (int) x, (int) y, 112, 112);
			}
		} else {
			if (velX == 0) {
				if (facing == 1) {
					playerIdle.drawAnimation(g, (int) x, (int) y, 112, 112);
				} else {
					playerIdleLeft.drawAnimation(g, (int) x, (int) y, 112, 112);
				}
			} else if (velX != 0) {
				if (facing == 1) {
					playerWalk.drawAnimation(g, (int) x, (int) y, 112, 112);
				} else {
					playerWalkLeft.drawAnimation(g, (int) x, (int) y, 112, 112);
				}
			}
		}

	}

	/**
	 * Gets the the attacked bounds, returns a new rectangle
	 * 
	 * @return Rectangle
	 */
	@Override
	public Rectangle getAttackBounds() {
		if (facing == 1 && playerAttack.getCount() != 0) {
			return new Rectangle((int) ((int) x + width - 36), (int) y + 23, 5 + playerAttack.getCount() * 11,
					(int) height - 38 + headmodside);
		} else if (facing == -1 && playerAttackLeft.getCount() != 0) {
			return new Rectangle((int) x + 30 - playerAttackLeft.getCount() * 11, (int) y + 23,
					5 + playerAttackLeft.getCount() * 11, (int) height - 38 + headmodside);
		} else {
			return new Rectangle(0, 0, 0, 0);
		}
	}

	/**
	 * Gets the width of this Player
	 * 
	 * @return float width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Gets the height of this Player
	 * 
	 * @return float height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Returns the Headmodtop value
	 * 
	 * @return int headmodtop
	 */
	public int getHeadModTop() {
		return headmodtop;
	}

	/**
	 * Returns the Headmodside value
	 * 
	 * @return int headmodside
	 */
	public int getHeadModSide() {
		return headmodside;
	}

	/**
	 * Returns the Animation of the Left Player attack
	 * 
	 * @return Animation
	 */
	public Animation getPlayerAttackLeft() {
		return playerAttackLeft;
	}

	/**
	 * Retuns the Animation of the Player Attack
	 * 
	 * @return Animation
	 */
	public Animation getPlayerAttack() {
		return playerAttack;
	}

	/**
	 * Returns a new Rectangle, gets the bound values
	 * 
	 * @return Rectangle
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x + 36, (int) ((int) y + (height / 2)), (int) width / 2 - 17, (int) height / 2 - 11);
	}

	/**
	 * Returns the Top bound values
	 * 
	 * @return Rectangle
	 */
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + 36, (int) y + headmodtop, (int) width / 2 - 17, (int) height / 2 - 20);
	}

	/**
	 * Returns the Right bound values
	 * 
	 * @return Rectangle
	 */
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 36), (int) y + 23 - headmodside, 5,
				(int) height - 38 + headmodside);
	}

	/**
	 * Returns the Left bound values
	 * 
	 * @return Rectangle
	 */
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x + 30, (int) y + 23 - headmodside, 5, (int) height - 38 + headmodside);
	}

}
