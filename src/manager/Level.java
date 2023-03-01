package manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import application.Settings;
import enums.Obstacle;
import mp3_player.Track;
import obstacles.Ball;
import obstacles.GameObject;
import obstacles.Glass;
import obstacles.Ground;
import obstacles.Slow;
import obstacles.Speed;
import obstacles.Spike;
import obstacles.Spring;

/**
 * Represets a level with all the information like sprites, backgrounds, tracks, and current gameObjects(level itself)
 * @author kchri laurinelias
 *
 */
public class Level implements Cloneable{
	
	private ArrayList<GameObject> levelObstacles;
	private Track track;
	private String background;
	private String ground_sprite;
	private String spike_sprite;
	private String ball_sprite;
	private String spring_sprite;
	private String speed_sprite;
	private String glass_sprite;
	private int id;

	/**
	 * Cunstructor
	 * @param id levelid
	 * @param trackPath path of the mp4
	 * @param background background img
	 * @param levelTxtUrl level builder text file
	 * @param ground_sprite sprite
	 * @param spike_sprite sprite
	 * @param ball_sprite sprite
	 * @param spring_sprite sprite
	 * @param speed_sprite sprite
	 * @param glass_sprite sprite
	 */
	public Level(int id ,String trackPath, String background, String levelTxtUrl, String ground_sprite, String spike_sprite, String ball_sprite,
			String spring_sprite, String speed_sprite, String glass_sprite) {
		this.id = id;
		this.track = new Track(trackPath);
		this.setBackground(background);
		this.ground_sprite = ground_sprite;
		this.spike_sprite = spike_sprite;
		this.ball_sprite = ball_sprite;
		this.spring_sprite = spring_sprite;
		this.speed_sprite = speed_sprite;
		this.glass_sprite = glass_sprite;
		levelObstacles = createLevelObstacles(levelTxtUrl);
	}
	
	public ArrayList<GameObject> getLevelObstacles() {
		return levelObstacles;
	}	
	
	/**
	 * Create the level gameObjects based on the level builder text file
	 * @param levelTxtUrl level builder text
	 * @return gameObjects representing the level 
	 */
	private ArrayList<GameObject> createLevelObstacles(String levelTxtUrl) {
		
		BufferedReader reader;
		String line;
		ArrayList<ArrayList<String>> obstaclesChar = new ArrayList<ArrayList<String>>();
		ArrayList<GameObject> out = new ArrayList<GameObject>();
		int offsetY = 1;
		int offsetX = 1;

		/* Read lines of text */
		try {
			reader = new BufferedReader(new FileReader(levelTxtUrl));
			while((line = reader.readLine()) != null) {
				 obstaclesChar.add( new ArrayList<>(Arrays.asList(line.split("(?!^)"))));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		/* convert text int gameobjects level */
		for (int i = 0; i < obstaclesChar.size(); i++) {
		    for (int j = 0; j < obstaclesChar.get(i).size(); j++) {
		    	
		    	int x = Settings.PIXEL_SIZE * i + (Settings.PIXEL_SIZE * (offsetX));
		    	int y = Settings.WINDOW_HEIGHT - (Settings.PIXEL_SIZE * j) - (Settings.PIXEL_SIZE * (offsetY + 1));
		    	
		    	if(obstaclesChar.get(i).get(j).equals(Obstacle.GROUND.getTypeChar()))
		    		out.add(new Ground(ground_sprite,x ,y)); 
		    	
		    	if(obstaclesChar.get(i).get(j).equals(Obstacle.SPIKE.getTypeChar()))
		    		out.add(new Spike(spike_sprite,x ,y)); 
		    	
		    	if(obstaclesChar.get(i).get(j).equals(Obstacle.BALL.getTypeChar()))
		    		out.add(new Ball(ball_sprite,x ,y)); 
		    	
		    	if(obstaclesChar.get(i).get(j).equals(Obstacle.GLASS.getTypeChar()))
		    		out.add(new Glass(glass_sprite,x ,y)); 
		    	
		     	if(obstaclesChar.get(i).get(j).equals(Obstacle.SPRING.getTypeChar()))
		    		out.add(new Spring(spring_sprite,x ,y)); 
		     	
		     	if(obstaclesChar.get(i).get(j).equals(Obstacle.SLOW.getTypeChar()))
		    		out.add(new Slow(speed_sprite,x ,y)); 
		     	
		      	if(obstaclesChar.get(i).get(j).equals(Obstacle.SPEED.getTypeChar()))
		    		out.add(new Speed(speed_sprite,x ,y)); 
				
		    }
		}
		
		return out;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public int getId() {
		return id;
	}
	
	 public Level clone() {
	    try {
			return (Level) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
}
