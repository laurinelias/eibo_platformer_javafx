package mp3_player;

import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * Representing a track for the Mp3Player
 * @author kchri
 *
 */
public class Track {
	private String path;
	private String name;
	private int lenght;
	private int bpm;
	
	/**
	 * Constructor 
	 * @param path
	 */
	public Track(String path) {
		Mp3File mp3 = null;	
		ID3v2 data = null;
		try {
			mp3 = new Mp3File(path);
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		
		if(mp3.hasId3v2Tag()) {
			data = mp3.getId3v2Tag();
		}
		this.setPath(path);
		this.setName(data.getTitle() != null ? data.getTitle() : mp3.getFilename());
		this.setLenght((int)(mp3.getLengthInSeconds()));
		this.setBpm(data.getBPM());
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public int getBpm() {
		return bpm;
	}

	public void setBpm(int bpm) {
		this.bpm = bpm;
	}
}
