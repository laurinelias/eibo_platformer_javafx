package player;

import java.util.List;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import obstacles.GameObject;
import obstacles.Ground;
import utils.Shapes;

/**
 * this class is responsible for the wall check
 * it holds inforamtion about the player envoirenment
 * @author kchri,laurinelias
 *
 */
public class PlayerWallCheck {
	
	public int topWall;
	public int leftWall; 
	public int rightWall;
	public int bottomWall;
	public boolean hasTopWall;
	public boolean hasLeftWall;
	public boolean hasRightWall;
	public boolean hasBottomWall;
	public Rectangle rectangle;
	public boolean check;
	
	/**
	 * Rectangle wich is going to be checkt on
	 * @param rectangle
	 */
	public PlayerWallCheck(Rectangle rectangle) {
		topWall = leftWall = rightWall = bottomWall = 0;
		hasTopWall = hasLeftWall = hasRightWall = hasBottomWall = false;
		check = true;
		this.rectangle = rectangle;
	}
	
	/**
	 * Checking every angle of the player and where the walls are
	 * from the player perspective
	 * @param level to be checked
	 */
	public void checkWalls(List<GameObject> level) {
		
		if(!check) {
			hasTopWall = hasLeftWall = hasRightWall = hasBottomWall = false;
			return;
		}
		
		checkWallBottom(level);
		checkWallRight(level);
		checkWallTop(level);
		checkWallLeft(level);
		debug(false);
	}
	
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	/**
	 * logging results
	 * @param debug
	 */
	public void debug(boolean debug) {
		
		if(!debug) return;
		System.out.print("bottom:\t" + hasBottomWall + "\t");
		if(hasBottomWall) System.out.println(bottomWall);
		else System.out.println();
		
		System.out.print("right:\t" + hasRightWall + "\t");
		if(hasRightWall) System.out.println(rightWall);
		else System.out.println();
		
		System.out.print("top:\t\t" + hasTopWall + "\t");
		if(hasTopWall) System.out.println(topWall);
		else System.out.println();
		
		System.out.print("left:\t\t" + hasLeftWall + "\t");
		if(hasLeftWall) System.out.println(leftWall);
		else System.out.println();
		
		System.out.println();
	}
	
	/**
	 * Checking wall bottom
	 * @param level
	 */
	public void checkWallBottom(List<GameObject> level) {
		hasBottomWall = false;
		double tempBottomWall = -1;
		for(GameObject other : level) {
			
			/* Checks only ground */
			if(other instanceof Ground == false) 
				continue;
			
			Rectangle other_rec = (Rectangle)other.getShape();
			
			/* Check Player Down*/
			if(
			(
				Shapes.getPointFromRec(rectangle, "bottom_left").getX() >=
				Shapes.getPointFromRec(other_rec, "top_left").getX() && 
				Shapes.getPointFromRec(rectangle, "bottom_left").getX() <=
				Shapes.getPointFromRec(other_rec, "top_right").getX()
			||
				Shapes.getPointFromRec(rectangle, "bottom_right").getX() >=
				Shapes.getPointFromRec(other_rec, "top_left").getX() && 
				Shapes.getPointFromRec(rectangle, "bottom_right").getX() <=
				Shapes.getPointFromRec(other_rec, "top_right").getX())	
			&& 
			(
				Shapes.getPointFromRec(rectangle, "bottom_left").getY() <= 
				Shapes.getPointFromRec(other_rec, "top_left").getY())
			) 	
			
			{
				/** Only the block width the smallest top value (ergo the block on the surface) will be the value*/
				if(tempBottomWall == -1) {
					tempBottomWall = (int)Shapes.getPointFromRec(other_rec, "top_left").getY();					
				} else {
					if((int)Shapes.getPointFromRec(other_rec, "top_left").getY() < tempBottomWall) {
						tempBottomWall = (int)Shapes.getPointFromRec(other_rec, "top_left").getY();
					}
				}
				hasBottomWall = true;
				
			} 
		}
		bottomWall = (int)tempBottomWall;
	}
	
	/**
	 * Checking wall top
	 * @param level
	 */
	public void checkWallTop(List<GameObject> level) {
		
		hasTopWall = false;
		double tempTopWall = -1;
		for(GameObject other : level) {
			
			/* Checks only ground */
			if(other instanceof Ground == false) 
				continue;
			
			Rectangle other_rec = (Rectangle)other.getShape();
			
			/* Check Player Down*/
			if(
			(
				Shapes.getPointFromRec(rectangle, "top_right").getX() >=
				Shapes.getPointFromRec(other_rec, "bottom_left").getX() && 
				Shapes.getPointFromRec(rectangle, "top_right").getX() <=
				Shapes.getPointFromRec(other_rec, "bottom_right").getX()
			||
				Shapes.getPointFromRec(rectangle, "top_left").getX() >=
				Shapes.getPointFromRec(other_rec, "bottom_left").getX() && 
				Shapes.getPointFromRec(rectangle, "top_left").getX() <=
				Shapes.getPointFromRec(other_rec, "bottom_right").getX())	
			&& 
			(
				Shapes.getPointFromRec(rectangle, "top_left").getY() >=
				Shapes.getPointFromRec(other_rec, "bottom_left").getY())
			) 	 	
			{
				
				if(tempTopWall == -1) {
					tempTopWall = (int)Shapes.getPointFromRec(other_rec, "bottom_left").getY();					
				} else {
					if((int)Shapes.getPointFromRec(other_rec, "bottom_left").getY() > tempTopWall) {
						tempTopWall = (int)Shapes.getPointFromRec(other_rec, "bottom_left").getY();
					}
				}
				hasTopWall = true;
				
			} 
		}
		topWall = (int)tempTopWall;
		
	}
	
	/**
	 * Checking wall left
	 * @param level
	 */
	public void checkWallLeft(List<GameObject> level) {
		hasLeftWall = false;
		double tempLeftWall = -1;
		for(GameObject other : level) {
			
			/* Checks only ground */
			if(other instanceof Ground == false) 
				continue;
			
			Rectangle other_rec = (Rectangle)other.getShape();
			
			/* Check Player Down*/
			if(
			(
				Shapes.getPointFromRec(rectangle, "top_left").getY() <=
				Shapes.getPointFromRec(other_rec, "bottom_right").getY() && 
				Shapes.getPointFromRec(rectangle, "top_left").getY() >=
				Shapes.getPointFromRec(other_rec, "top_right").getY()
			||
				Shapes.getPointFromRec(rectangle, "bottom_left").getY() <=
				Shapes.getPointFromRec(other_rec, "bottom_right").getY() && 
				Shapes.getPointFromRec(rectangle, "bottom_left").getY() >=
				Shapes.getPointFromRec(other_rec, "top_right").getY())	
						
			&&
			(
				Shapes.getPointFromRec(rectangle, "bottom_left").getX() >=
				Shapes.getPointFromRec(other_rec, "bottom_right").getX())
			) 	 	
			{
				
				if(tempLeftWall == -1) {
					tempLeftWall = (int)Shapes.getPointFromRec(other_rec, "bottom_right").getX();					
				} else {
					if((int)Shapes.getPointFromRec(other_rec, "bottom_right").getX() > tempLeftWall) {
						tempLeftWall = (int)Shapes.getPointFromRec(other_rec, "bottom_right").getX();
					}
				}
				hasLeftWall = true;
				
			} 
		}
		leftWall = (int)tempLeftWall;
	}
	
	/**
	 * Checking wall right
	 * @param level
	 */
	public void checkWallRight(List<GameObject> level) {
		hasRightWall = false;
		double tempRightWall = -1;
		for(GameObject other : level) {
			
			/* Checks only ground */
			if(other instanceof Ground == false) 
				continue;
			
			Rectangle other_rec = (Rectangle)other.getShape();
			
			/* Check Player Down*/
			if(
			(
				Shapes.getPointFromRec(rectangle, "top_right").getY() <=
				Shapes.getPointFromRec(other_rec, "bottom_left").getY() && 
				Shapes.getPointFromRec(rectangle, "top_right").getY() >=
				Shapes.getPointFromRec(other_rec, "top_left").getY()
			||
				Shapes.getPointFromRec(rectangle, "bottom_right").getY() <=
				Shapes.getPointFromRec(other_rec, "bottom_left").getY() && 
				Shapes.getPointFromRec(rectangle, "bottom_right").getY() >=
				Shapes.getPointFromRec(other_rec, "top_right").getY())	
			&& 
			(
				Shapes.getPointFromRec(rectangle, "bottom_right").getX() <=
				Shapes.getPointFromRec(other_rec, "bottom_left").getX())
			) 	 	
			{
				if(tempRightWall == -1) {
					tempRightWall = (int)Shapes.getPointFromRec(other_rec, "bottom_left").getX();					
				} else {
					if((int)Shapes.getPointFromRec(other_rec, "bottom_left").getX() < tempRightWall) {
						tempRightWall = (int)Shapes.getPointFromRec(other_rec, "bottom_left").getX();
					}
				}
				hasRightWall = true;
				
			} 
		}
		rightWall = (int)tempRightWall;
	}
	
}
