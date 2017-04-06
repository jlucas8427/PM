package controller;

import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The music class that will play music in the background
 * 
 * @author Jerry Lucas, Richard A. Bravo, Robert Tuck, Joceyln Rocha, Thomas
 *         Robinson
 * @version 1.0
 */
public class Music {
	/**
	 * The clips of this Music
	 */
	private static HashMap<String, Clip> clips;
	/**
	 * The gap of this Music
	 */
	private static int gap;
	/**
	 * the mute of this music
	 */
	private static boolean mute = false;

	/**
	 * Initiates the clips
	 */
	public static void init() {
		clips = new HashMap<String, Clip>();
		gap = 0;
	}

	/**
	 * Loads up a music file and the name of that file
	 * 
	 * @param String
	 *            s
	 * @param String
	 *            n
	 */
	public static void load(String s, String n) {
		if (clips.get(n) != null)
			return;
		Clip clip;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(Music.class.getResourceAsStream(s));
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			clips.put(n, clip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays the specified music
	 * 
	 * @param String
	 *            s
	 */
	public static void play(String s) {
		play(s, gap);
	}

	/**
	 * Plays the specified music
	 * 
	 * @param String
	 *            s
	 * @param int
	 *            i
	 */
	public static void play(String s, int i) {
		if (mute)
			return;
		Clip c = clips.get(s);
		if (c == null)
			return;
		if (c.isRunning())
			c.stop();
		c.setFramePosition(i);
		while (!c.isRunning())
			c.start();
	}

	/**
	 * Stops the the current music clip being currently played
	 * 
	 * @param String
	 *            s
	 */
	public static void stop(String s) {
		if (clips.get(s) == null)
			return;
		if (clips.get(s).isRunning())
			clips.get(s).stop();
	}

	/**
	 * Loops the specified music.
	 * 
	 * @param String
	 *            s
	 */

}