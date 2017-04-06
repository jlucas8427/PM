package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * A BufferImageLoader class, makes it so that we can load images more
 * efficiently
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class BufferedImageLoader {
	/**
	 * The image of this BufferImageLoader
	 */
	private BufferedImage image;

	/**
	 * A method that loads the BufferedImage, requires you put in the path of
	 * the image you want to load in the parameters.
	 * 
	 * @param String
	 *            path
	 * @return BufferedImage image
	 */
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
