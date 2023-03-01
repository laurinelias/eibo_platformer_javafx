package player;

import java.util.List;

import javafx.scene.shape.Rectangle;
import obstacles.Ball;
import obstacles.GameObject;
import obstacles.Ground;
import obstacles.Slow;
import obstacles.Speed;
import obstacles.Spike;
import obstacles.Spring;
import utils.Shapes;

/**
 * This class is responsible for the collsion check
 * @author kchri,laurinelias
 *
 */
public class PlayerCollision {
	
	public boolean top, bottom, left, right, check, spike, ball, spring, speed, slow;
	public Rectangle rectangle;
	
	/**
	 * Rectangle wich is going to be checkt on
	 * @param rectangle
	 */
	public PlayerCollision(Rectangle rectangle) {
		check = true;
		top = bottom = left = right = spike = ball = spring = speed = slow = false;
		this.rectangle = rectangle;
	}
	
	/**
	 * Checking every possible collision each frame, with every possible gameobject
	 * results are representet by bools
	 * @param level to be checked
	 */
	public void checkCollision(List<GameObject> level) {
		if(!check) {
			top = bottom = left = right = spike = ball = spring = speed = slow = false;
			return;
		}
		
		/* Traps */
		checkSpikeCollision(level);
		checkBallCollision(level);
		
		/* PowerUps*/
		checkSpringCollision(level);
		checkSpeedCollision(level);
		checkSlowCollision(level);
		
		/* Ground */
		checkGroundCollisionBottom(level);
		checkGroundCollisionRight(level);
		checkGroundCollisionTop(level);
		checkGroundCollisionLeft(level);
		
		debug(false);
	}
	
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	/**
	 * logging results
	 * @param debug true for debug mode
	 */
	public void debug(boolean debug) {
		if(!debug) return;
		System.out.print("bottom:\t" + bottom + "\t");
		System.out.println();
		System.out.print("right:\t" + right + "\t");
		System.out.println();
		System.out.print("top:\t\t" + top + "\t");
		System.out.println();
		System.out.print("left:\t\t" + left + "\t");
		System.out.println();
		System.out.print("spike:\t" + spike + "\t");
		System.out.println();
		System.out.print("ball:\t\t" + ball + "\t");
		System.out.println();
		System.out.print("spring:\t" + spring + "\t");
		System.out.println();
		System.out.print("speed:\t" + speed + "\t");
		System.out.println();
		System.out.print("slow:\t" + slow + "\t");
		System.out.println();
		System.out.println();
	}
	
	/**
	 * Collsion check for slow update
	 * @param level
	 */
	public void checkSlowCollision(List<GameObject> level) {
		for(GameObject other : level) {
			if(other instanceof Slow == false) 
				continue;

			if(rectangle.getBoundsInParent().intersects(other.getShape().getBoundsInParent())) {
				slow = true;
				return;
			}
		}
		slow = false;
	}
	
	/**
	 * Collsion check for speed update
	 * @param level
	 */
	public void checkSpeedCollision(List<GameObject> level) {
		for(GameObject other : level) {
			if(other instanceof Speed == false) 
				continue;

			if(rectangle.getBoundsInParent().intersects(other.getShape().getBoundsInParent())) {
				speed = true;
				return;
			}
		}
		speed = false;
	}
	
	/**
	 * Collsion check for spring obstacle
	 * @param level
	 */
	public void checkSpringCollision(List<GameObject> level) {
		for(GameObject other : level) {
			if(other instanceof Spring == false) 
				continue;

			if(rectangle.getBoundsInParent().intersects(other.getShape().getBoundsInParent())) {
				spring = true;
				return;
			}
		}
		spring = false;
	}
	
	/**
	 * Collsion check for ball obstacle
	 * @param level
	 */
	public void checkBallCollision(List<GameObject> level) {
		for(GameObject other : level) {
			if(other instanceof Ball == false) 
				continue;

			if(rectangle.getBoundsInParent().intersects(other.getShape().getBoundsInParent())) {
				ball = true;
				return;
			}
		}
		ball = false;
	}
	
	/**
	 * Collsion check for spike obstacle
	 * @param level
	 */
	public void checkSpikeCollision(List<GameObject> level) {
		for(GameObject other : level) {
			if(other instanceof Spike == false) 
				continue;

			if(rectangle.getBoundsInParent().intersects(other.getShape().getBoundsInParent())) {
				spike = true;
				return;
			}
			
		}
		spike = false;
	}
	
	/**
	 * Collsion check for ground (bottom)
	 * @param level
	 */
	public void checkGroundCollisionBottom(List<GameObject> level) {
		
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
				Shapes.getPointFromRec(rectangle, "bottom_left").getY()  >= 
				Shapes.getPointFromRec(other_rec, "top_left").getY()
			)
			&&
			(
				Shapes.getPointFromRec(rectangle, "bottom_left").getY()  <= 
				Shapes.getPointFromRec(other_rec, "bottom_left").getY())
			) 		
			
			{
				bottom = true;
				return;
			} 
		}
	
		bottom = false;
	}
	
	/**
	 * Collsion check for ground (right)
	 * @param level
	 */
	public void checkGroundCollisionRight(List<GameObject> level) {
		
		for(GameObject other : level) {
			
			/* Checks only ground */
			if(other instanceof Ground == false) 
				continue;
			
			Rectangle other_rec = (Rectangle)other.getShape();
			
			/* Check Player Down*/
			if(
			(
				Shapes.getPointFromRec(rectangle, "top_right").getY() + 1 <=
				Shapes.getPointFromRec(other_rec, "bottom_left").getY() && 
				Shapes.getPointFromRec(rectangle, "top_right").getY() + 1 >=
				Shapes.getPointFromRec(other_rec, "top_left").getY()
			||
				Shapes.getPointFromRec(rectangle, "bottom_right").getY() - 1 <=
				Shapes.getPointFromRec(other_rec, "bottom_left").getY() && 
				Shapes.getPointFromRec(rectangle, "bottom_right").getY() - 1 >=
				Shapes.getPointFromRec(other_rec, "top_right").getY())	
			&& 
			(
				Shapes.getPointFromRec(rectangle, "bottom_right").getX() >= 
				Shapes.getPointFromRec(other_rec, "bottom_left").getX()
			)
			&&
			(
				Shapes.getPointFromRec(rectangle, "bottom_right").getX() <= 
				Shapes.getPointFromRec(other_rec, "bottom_right").getX())
			) 	
			
			{
				right = true;
				return;
			} 
		}
		right = false;
	}
	
	/**
	 * Collsion check for ground (top)
	 * @param level
	 */
	public void checkGroundCollisionTop(List<GameObject> level) {
		
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
				Shapes.getPointFromRec(rectangle, "top_left").getY() <= 
				Shapes.getPointFromRec(other_rec, "bottom_left").getY()
			)
			&&
			(
				Shapes.getPointFromRec(rectangle, "top_left").getY() >=
				Shapes.getPointFromRec(other_rec, "top_left").getY())
			) 	
			{
				top = true;
				return;
			} 
		}
		top = false;
	}

	/**
	 * Collsion check for ground (left)
	 * @param level
	 */
	public void checkGroundCollisionLeft(List<GameObject> level) {
		
		for(GameObject other : level) {
			
			/* Checks only ground */
			if(other instanceof Ground == false) 
				continue;
			
			Rectangle other_rec = (Rectangle)other.getShape();
			
			/* Check Player Down*/
			if(
			(
				Shapes.getPointFromRec(rectangle, "top_left").getY() + 1 <=
				Shapes.getPointFromRec(other_rec, "bottom_right").getY() && 
				Shapes.getPointFromRec(rectangle, "top_left").getY() + 1 >=
				Shapes.getPointFromRec(other_rec, "top_right").getY()
			||
				Shapes.getPointFromRec(rectangle, "bottom_left").getY() - 1 <=
				Shapes.getPointFromRec(other_rec, "bottom_right").getY() && 
				Shapes.getPointFromRec(rectangle, "bottom_left").getY() - 1 >=
				Shapes.getPointFromRec(other_rec, "top_right").getY())	
			&& 
			(
				Shapes.getPointFromRec(rectangle, "bottom_left").getX() <=
				Shapes.getPointFromRec(other_rec, "bottom_right").getX()
			)
			&&
			(
				Shapes.getPointFromRec(rectangle, "bottom_left").getX() >=
				Shapes.getPointFromRec(other_rec, "bottom_left").getX())
			) 	
			
			{
				left = true;
				return;
			} 
		}
		left = false;
	}
}
