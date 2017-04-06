package game;

import controller.Game;
import view.Window;

/**
 * The Start class for this game, code execution starts here.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Start {
	/**
	 * The main method of this game, the code starts here.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		new Window(800, 565, "Umbral Phantoms", new Game());
	}
}
