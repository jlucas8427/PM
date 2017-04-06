package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The Animation class, collects the sprites/frames of the images and puts them
 * together to make an animation
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Jocelyn Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Animation {
	/**
	 * The speed of this Animation
	 */
	private int speed;
	/**
	 * The frames of this Animation
	 */
	private int frames;
	/**
	 * The index of this Animation
	 */
	private int index = 0;
	/**
	 * The countof this Animation
	 */
	private int count = 0;
	/**
	 * The images of this Animation
	 */
	private BufferedImage[] images;
	/**
	 * the currentImg of this Animation
	 */
	private BufferedImage currentImg;

	/**
	 * The constructor for this Animation, takes in an int and BufferImage
	 * object
	 * 
	 * @param int
	 *            speed
	 * @param BufferImage
	 *            args
	 */
	public Animation(int speed, BufferedImage... args) {
		this.speed = speed;
		images = new BufferedImage[args.length];
		for (int i = 0; i < args.length; i++) {
			images[i] = args[i];
		}
		frames = args.length;
		index = speed;

	}

	/**
	 * Runs the animation object
	 */
	public void runAnimation() {
		index++;
		if (index > speed) {
			index = 0;
			nextFrame();
		}
	}

	/**
	 * Goes to the next frame in the Animation
	 */
	private void nextFrame() {
		for (int i = 0; i < frames; i++) {
			if (count == i) {
				currentImg = images[i];
			}
		}
		count++;
		if (count > frames) {
			count = 0;
			nextFrame();
		}
	}

	/**
	 * Runs the Animation fully once
	 */
	public void runAnimationOnce() {
		index++;
		if (index > speed) {
			index = 0;
			nextFrameNR();
		}
	}

	/**
	 * Goes to the next frame in the Animation
	 */
	private void nextFrameNR() {
		for (int i = 0; i < frames; i++) {
			if (count == i) {
				currentImg = images[i];
			}
		}
		if (count < frames) {
			count++;
		}
	}

	/**
	 * Sets the first image of the Animation
	 */
	public void setFirst() {
		currentImg = images[0];
	}

	/**
	 * Sets the count for this Animation
	 * 
	 * @param int
	 *            count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Returns the current count value of this Animation
	 * 
	 * @return int count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Returns the current frames value of this Animation
	 * 
	 * @return int frames
	 */
	public int getFrames() {
		return frames;
	}

	/**
	 * Sets the index value for this Animation
	 * 
	 * @param int
	 *            index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Puts together and draws the Animation
	 * 
	 * @param Graphics
	 *            g
	 * @param int
	 *            x
	 * @param int
	 *            y
	 */
	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(currentImg, x, y, null);
	}

	/**
	 * Puts together and draws the Animation
	 * 
	 * @param Graphics
	 *            g
	 * @param int
	 *            x
	 * @param int
	 *            y
	 * @param int
	 *            scaleX
	 * @param int
	 *            scaleY
	 */
	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
		g.drawImage(currentImg, x, y, scaleX, scaleY, null);
	}

}
