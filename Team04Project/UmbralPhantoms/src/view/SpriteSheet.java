package view;

import java.awt.image.BufferedImage;

/**
 * The SpriteSheet class, contains a set of sprites.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class SpriteSheet {
	/**
	 * The SpriteSheet to be used.
	 */
	private BufferedImage image;

	/**
	 * Creates a SpriteSheet object from a BufferedImage.
	 * 
	 * @param image
	 *            The image to be used as a SpriteSheet
	 */
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Separates the SpriteSheet into blocks a grabs the subimage.
	 * 
	 * @param col
	 *            Column of the SpriteSheet
	 * @param row
	 *            Row of the SpriteSheet
	 * @param width
	 *            Width of the subimage to be grabbed
	 * @param height
	 *            Height of the subimage to be grabbed
	 * @return Returns the subimage from the SpriteSheet
	 */
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
		return img;
	}

}
