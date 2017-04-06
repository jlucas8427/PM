package view;

import java.awt.Dimension;
import javax.swing.JFrame;
/**
 * The Window the game will be displayed in
 * @author Robert, Thomas, Jerry, Jocelyn, Richard
 *
 */
import controller.Game;

/**
 * This class is the Window that the game will run in.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Window {

	/**
	 * Constructs a game Window of set size.
	 * 
	 * @param w
	 *            Width of the Window
	 * @param h
	 *            Height of the Window
	 * @param title
	 *            Title of the Window
	 * @param game
	 *            Game object
	 */
	public Window(int w, int h, String title, Game game) {
		game.setPreferredSize(new Dimension(w, h));
		game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

}
