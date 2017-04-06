package controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import model.BufferedImageLoader;
import model.ObjectId;
import view.Credits;
import view.Death;
import view.Texture;
import view.Victory;

/*
 * If using the Music under Linux the java jdk sound.properties file
 * must be changed with the following code.
 * 
 * javax.sound.sampled.Clip=com.sun.media.sound.DirectAudioDeviceProvider
 * javax.sound.sampled.Port=com.sun.media.sound.PortMixerProvider
 * javax.sound.sampled.SourceDataLine=com.sun.media.sound.DirectAudioDeviceProvider
 * javax.sound.sampled.TargetDataLine=com.sun.media.sound.DirectAudioDeviceProvider
*/
/**
 * A game class that extends from Canvas and implements the Runnable interface.
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Jocelyn Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Game extends Canvas implements Runnable {
	/**
	 * Various data variables used throughout the program
	 */
	private static final long serialVersionUID = -4120288274944317238L;
	private boolean running = false;
	private Thread thread;
	public static int WIDTH, HEIGHT;
	private BufferedImage arrow = null;
	private BufferedImage bc = null;
	public static int arrowX = -298;
	public static int arrowY = 460;
	public static int arrowX1 = -75;
	public static int arrowY1 = 288;
	public static int arrowX2 = -55;
	public static int arrowY2 = 140;
	private BufferedImage level = null;
	private BufferedImage level1 = null;
	private BufferedImage background1 = null;
	private BufferedImage died = null;
	private BufferedImage vic = null;
	Handler handler;
	Camera cam;
	static Texture tex;
	Random rand = new Random();
	private Credits menu;
	private Victory victory;
	private Death death;
	public static STATE state = STATE.MENU;
	public static int LEVEL = 1;

	/**
	 * An enum to switch from the various game states, this is our menu system
	 * 
	 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
	 *         Robinson
	 * @version 1.0
	 */
	public static enum STATE {
		MENU, GAME, VICTORY, DEATH, CREDITS
	};

	/**
	 * Sets up the variables needed to start the game.
	 */
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		tex = new Texture();
		BufferedImageLoader loader = new BufferedImageLoader();
		bc = loader.loadImage("/bc1.jpg");
		arrow = loader.loadImage("/Arrow4.png");
		level = loader.loadImage("/level.png");
		level1 = loader.loadImage("/map.png");
		background1 = loader.loadImage("/background.png");
		died = loader.loadImage("/YOUDIED2.png");
		vic = loader.loadImage("/vic.png");
		menu = new Credits();
		victory = new Victory();
		death = new Death();
		cam = new Camera(0, 0);
		handler = new Handler(cam);
		handler.LoadImageLevel(level);
		this.addKeyListener(new KeyInput(handler));
		Music.init();
		Music.load("/bgm.mp3", "bgm");
		Music.play("bgm");
	}

	/**
	 * Starts the game
	 */
	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Initiates and runs the game
	 */
	@Override
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		boolean render = false;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
				render = true;
			}
			if (render) {
				render();
				frames++;
				render = false;
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}

		}
	}

	/**
	 * The tick method of this game
	 */
	private void tick() {
		if (state == STATE.GAME) {
			handler.tick();
		}
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player) {
				cam.tick(handler.object.get(i));
			}
		}
	}

	/**
	 * Renders the Game, and menus of this Game
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		// set background color
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		g2d.translate(cam.getX(), cam.getY());

		// Renders for Game state
		if (state == STATE.GAME) {
			g.drawImage(background1, 0, 0, null);
			handler.render(g);
			g.drawImage(level1, 0, 0, null);
		}

		// Renders for Menu state
		else if (state == STATE.MENU) {
			g.drawImage(bc, -375, -8, null);
			g.drawImage(arrow, arrowX, arrowY, 50, 50, this);
		}

		// renders for death state
		else if (state == STATE.DEATH) {
			death.render(g);
			g.drawImage(died, -368, 3, null);
			g.drawImage(arrow, arrowX1, arrowY1, 50, 50, this);
		}
		// Renders for Victory state
		else if (state == STATE.VICTORY) {
			victory.render(g);
			g.drawImage(vic, -375, -8, null);
			g.drawImage(arrow, arrowX2, arrowY2, 50, 50, this);
		}
		// Renders for Credits state
		else if (state == STATE.CREDITS) {
			menu.render(g);
			g.drawImage(arrow, arrowX, arrowY, 50, 50, this);
		}

		g2d.translate(-cam.getX(), -cam.getY());
		g.dispose();
		bs.show();

	}

	/**
	 * Returns a Texture object of this Game
	 * 
	 * @return Texture
	 */
	public static Texture getInstance() {
		return tex;
	}

}
