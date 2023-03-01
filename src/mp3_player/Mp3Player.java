package mp3_player;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;

/**
 * Mp3Player for managing the ingame Music
 * @author kchri
 *
 */
public class Mp3Player {
	
	private SimpleAudioPlayer audioPlayer;
	private SimpleMinim minim;
	
	/**
	 * Constructor
	 */
	public Mp3Player () {
		minim = new SimpleMinim();
	}
	
	/**
	 * Loading new track
	 * @param track new track
	 */
	public void load(Track track) {
		audioPlayer = minim.loadMP3File(track.getPath()); 
	}
	
	/**
	 * Playing current track
	 */
	public void play() {
		new Thread() {
			public void run() {
				audioPlayer.play();		
			}
		}.start();
	}
	
	/**
	 * Pausing current track
	 */
	public void pause() {
		audioPlayer.pause();
	}
	
	/**
	 * Set gain
	 * @param gain
	 */
	public void setGain(double gain) {
		audioPlayer.setGain((float)gain);
	}
	
	public SimpleAudioPlayer getAudioPlayer() {
		return audioPlayer;
	}
	
}
