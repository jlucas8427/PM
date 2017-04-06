package view;

import java.awt.image.BufferedImage;
/**
 * 
 * @author Robert, Thomas, Jerry, Jocelyn, Richard.
 *
 */
import model.BufferedImageLoader;

/**
 * A texture class that constructs and manages the various sprites.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Texture {

	/**
	 * Various data variables for Texture class
	 */
	SpriteSheet bs, pi, pil, pw, pwl, pj, pjl, pc, pcl, pa, pal, bi, pb;
	private BufferedImage block_sheet = null;
	private BufferedImage player_idle = null;
	private BufferedImage player_idle_left = null;
	private BufferedImage player_walk = null;
	private BufferedImage player_walk_left = null;
	private BufferedImage player_jump = null;
	private BufferedImage player_jump_left = null;
	private BufferedImage player_crouch = null;
	private BufferedImage player_crouch_left = null;
	private BufferedImage player_attack = null;
	private BufferedImage player_attack_left = null;
	private BufferedImage player_blank = null;
	private BufferedImage bat_idle = null;
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] spikes = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[47];
	public BufferedImage[] bat = new BufferedImage[1];

	/**
	 * Constructs a Texture object and creates SpriteSheets
	 */
	public Texture() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/block_sheet.png");
			player_idle = loader.loadImage("/player_idle.png");
			player_idle_left = loader.loadImage("/player_idle_left.png");
			player_walk = loader.loadImage("/player_walk.png");
			player_walk_left = loader.loadImage("/player_walk_left.png");
			player_jump = loader.loadImage("/player_jump.png");
			player_jump_left = loader.loadImage("/player_jump_left.png");
			player_crouch = loader.loadImage("/player_crouch.png");
			player_crouch_left = loader.loadImage("/player_crouch_left.png");
			player_attack = loader.loadImage("/player_attack.png");
			player_attack_left = loader.loadImage("/player_attack_left.png");
			player_blank = loader.loadImage("/player_blank.png");
			bat_idle = loader.loadImage("/bat.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		bs = new SpriteSheet(block_sheet);
		pi = new SpriteSheet(player_idle);
		pil = new SpriteSheet(player_idle_left);
		pw = new SpriteSheet(player_walk);
		pwl = new SpriteSheet(player_walk_left);
		pj = new SpriteSheet(player_jump);
		pjl = new SpriteSheet(player_jump_left);
		pc = new SpriteSheet(player_crouch);
		pcl = new SpriteSheet(player_crouch_left);
		pa = new SpriteSheet(player_attack);
		pal = new SpriteSheet(player_attack_left);
		pb = new SpriteSheet(player_blank);
		bi = new SpriteSheet(bat_idle);

		getTextures();
	}

	/**
	 * Grabs the different textures from the SpriteSheets.
	 */
	private void getTextures() {
		block[0] = bs.grabImage(2, 1, 16, 16);
		block[1] = bs.grabImage(4, 5, 16, 16);

		// spike blocks
		spikes[0] = bs.grabImage(3, 13, 16, 16);

		// player idle
		player[0] = pi.grabImage(1, 1, 56, 56);
		player[1] = pi.grabImage(2, 1, 56, 56);
		player[2] = pi.grabImage(3, 1, 56, 56);

		// player walk
		player[3] = pw.grabImage(1, 1, 56, 56);
		player[4] = pw.grabImage(2, 1, 56, 56);
		player[5] = pw.grabImage(3, 1, 56, 56);
		player[6] = pw.grabImage(4, 1, 56, 56);
		player[7] = pw.grabImage(5, 1, 56, 56);
		player[8] = pw.grabImage(6, 1, 56, 56);
		player[9] = pw.grabImage(7, 1, 56, 56);
		player[10] = pw.grabImage(8, 1, 56, 56);

		// player walk left
		player[11] = pwl.grabImage(1, 1, 56, 56);
		player[12] = pwl.grabImage(2, 1, 56, 56);
		player[13] = pwl.grabImage(3, 1, 56, 56);
		player[14] = pwl.grabImage(4, 1, 56, 56);
		player[15] = pwl.grabImage(5, 1, 56, 56);
		player[16] = pwl.grabImage(6, 1, 56, 56);
		player[17] = pwl.grabImage(7, 1, 56, 56);
		player[18] = pwl.grabImage(8, 1, 56, 56);

		// player jump
		player[19] = pj.grabImage(1, 1, 56, 56);
		player[20] = pj.grabImage(2, 1, 56, 56);
		player[21] = pj.grabImage(3, 1, 56, 56);
		player[22] = pj.grabImage(4, 1, 56, 56);

		// player idle left
		player[23] = pil.grabImage(1, 1, 56, 56);
		player[24] = pil.grabImage(2, 1, 56, 56);
		player[25] = pil.grabImage(3, 1, 56, 56);

		// player jump left
		player[26] = pjl.grabImage(1, 1, 56, 56);
		player[27] = pjl.grabImage(2, 1, 56, 56);
		player[28] = pjl.grabImage(3, 1, 56, 56);
		player[29] = pjl.grabImage(4, 1, 56, 56);

		// player crouch
		player[30] = pc.grabImage(1, 1, 56, 56);
		player[31] = pc.grabImage(2, 1, 56, 56);

		// player crouch left
		player[32] = pcl.grabImage(1, 1, 56, 56);
		player[33] = pcl.grabImage(2, 1, 56, 56);

		// player attack
		player[34] = pa.grabImage(1, 1, 91, 64);
		player[35] = pa.grabImage(2, 1, 91, 64);
		player[36] = pa.grabImage(3, 1, 91, 64);
		player[37] = pa.grabImage(4, 1, 91, 64);
		player[38] = pa.grabImage(5, 1, 91, 64);
		player[39] = pa.grabImage(6, 1, 91, 64);

		// player attack left
		player[40] = pal.grabImage(1, 1, 91, 64);
		player[41] = pal.grabImage(2, 1, 91, 64);
		player[42] = pal.grabImage(3, 1, 91, 64);
		player[43] = pal.grabImage(4, 1, 91, 64);
		player[44] = pal.grabImage(5, 1, 91, 64);
		player[45] = pal.grabImage(6, 1, 91, 64);

		// player blank
		player[46] = pb.grabImage(1, 1, 56, 56);

		// bat idle
		bat[0] = bi.grabImage(1, 1, 34, 34);

	}
}
