package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import controller.Game;

/**
 * We don't need this class anymore.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Credits extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4828440807041818774L;

	/**
	 * Renders the Credits screen
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		Font font1 = new Font("Times New Roman", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.white);
		g.drawString("Credits:", Game.WIDTH - 870, 100);
		Font font2 = new Font("arial", Font.BOLD, 30);
		g.setFont(font2);
		g.drawString("Jerry", Game.WIDTH - 825, 150);
		g.drawString("Robert", Game.WIDTH - 825, 200);
		g.drawString("Richard", Game.WIDTH - 825, 250);
		g.drawString("Jocelyn", Game.WIDTH - 825, 300);
		g.drawString("Thomas", Game.WIDTH - 825, 350);
	}
}
