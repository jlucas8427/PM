package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import controller.Game;

/**
 * A JPanel representing the death state and end of game.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Death {

	public void render(Graphics g) {
		Font font1 = new Font("Times New Roman", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.white);
		g.drawString("You Are DEAD!", Game.WIDTH - 950, 100);
		Font font2 = new Font("arial", Font.BOLD, 30);
		g.setFont(font2);
		g.drawString("Restart", (Game.WIDTH - 810), 150 + 35);
		g.drawString("Exit", (Game.WIDTH - 810), 225 + 35);
	}

}
