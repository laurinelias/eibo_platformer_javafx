package player;

import java.util.ArrayList;
import application.Settings;
import enums.Direction;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import obstacles.GameObject;
import ui.GameView;

/**
 * Representing the Moveable Player ingame
 * @author kchri,laurinelias
 *
 */
public class Player {
	
	private PlayerCollision playerCollision;
	private PlayerWallCheck playerWallCheck;
	private Rectangle rectangle;
	private Point2D currentDir;
	private double speed;
	private double initSpeed;
	private boolean pause;
	
	/* Gravity Variables */
	private boolean inAir;
	private double velocityY = 0;
	private double myTime = 0;
	
	/* Jump Variables */
	private boolean isJumping = false;
	private boolean canJump = true;
	private boolean initJump = false;
	private double myTimeJump = 0;
	private double velocityYJump = 0;
	private double jumpPower = 20;
	private double jumpDuration = 0.05;
	
	/**
	 * Constructor
	 */
	public Player() {
		rectangle = new Rectangle(Settings.PIXEL_SIZE, Settings.PIXEL_SIZE);
		rectangle.setFill(new ImagePattern(new Image("C:\\Users\\kchri\\OneDrive\\Surface\\Eclipse EIBO\\eibo_platformer_javafx\\media\\player_sprite.png")));
		playerCollision = new PlayerCollision(rectangle);
		playerWallCheck = new PlayerWallCheck(rectangle);
	}
	
	/**
	 * Set the moving direction
	 * @param dir desired moving direction
	 */
	public void setMoveDir(Direction dir) {

		if(dir == Direction.RIGHT) {
			currentDir = new Point2D(1,0);
		}
		
		if(dir == Direction.LEFT) {
			currentDir = new Point2D(-1,0);
		}
		
		if(dir == Direction.UP) {
			currentDir = new Point2D(0,-1);
		}
		
		if(dir == Direction.DOWN) {
			currentDir = new Point2D(0,1);
		}
		
	}

	
	public void update(double delta, ArrayList<GameObject> currentLevel) {
		
		if(pause) return;
		
		
		double oldX = (rectangle.getTranslateX() + (speed * currentDir.getX()));
		
		double x = rectangle.getTranslateX() + (speed * currentDir.getX());
		double y = rectangle.getTranslateY();
		
		double newX = x;
		
		System.out.println(newX - oldX);
		/* Collision Checking and Wall checking */
		playerCollision.checkCollision(currentLevel);
		playerWallCheck.checkWalls(currentLevel);
		

		/* Player Collisions */
		if(playerCollision.bottom) {
			canJump = true;
			setInAir(false);
		} else {
			setInAir(true);
		}
		
		if(playerCollision.top) {
			isJumping = false;
		}
		
		
		if(playerCollision.spring) {
			jump();
		}
		
		if(playerCollision.speed) {
			speed = initSpeed * 2;
		}
		
		if(playerCollision.slow) {
			speed = initSpeed * 0.5;
		}
		
		/* Aply Gravity when in air */
		
		if(inAir) {
			canJump = false;
			velocityY += Settings.GRAVITY * 0.5 * Math.pow(myTime, 2);
			y += velocityY;
			myTime += .05;
		} else {
			canJump = true;
			myTime = 0;
			velocityY = 0;
		}
		
		/* Add pull when jump */
		if(initJump || isJumping) {
			canJump = false;
			isJumping = true;
			initJump = false;
			velocityYJump -= Settings.GRAVITY * 1.5 * Math.pow(myTimeJump, 2);
			y -= velocityYJump;
			myTimeJump += jumpDuration;
			
			if(velocityYJump <= 0) {
				/*At this point the player is not jumping anymore, he is just falling down */
				isJumping = false;
			}
		} else {
			myTimeJump = 0;
			velocityYJump = jumpPower;
		}
		
		
		/* Safe check if value is calculated outside walls */
		if(playerWallCheck.hasBottomWall) {
			if(y + Settings.PIXEL_SIZE > playerWallCheck.bottomWall) {
				y = playerWallCheck.bottomWall - Settings.PIXEL_SIZE;
			}
		}
		
		if(playerWallCheck.hasTopWall) {
			if(y < playerWallCheck.topWall) {
				y = playerWallCheck.topWall;
			}
		}

		
		/* Death by Collsison */
		if(playerCollision.right || playerCollision.spike || playerCollision.ball) {
			startDeathAnimation();
		}
			
		setPosition(x,y);
	}
	
	public void jump() {
		if(canJump) {
			initJump = true;
		}
	}
	
	public void startDeathAnimation() {
		playerCollision.setCheck(false);
		playerWallCheck.setCheck(false);
		jump();
		setSpeed(0);
	}
	
	public void startCollisionDetection() {
		playerCollision.setCheck(true);
		playerWallCheck.setCheck(true);
	}

 	/* Getter Setter */
	public Rectangle getPlayerNode() {
		return rectangle;
	}
	
	public void setSpeed(double speed) {
		this.speed = initSpeed = speed;
		
	}
	
	public void setPosition(double x, double y) {
		rectangle.setTranslateX(x);
		rectangle.setTranslateY(y);
	}
	
	public void addVelocity(double  x, double y) {
		rectangle.relocate(x, y);
	}
		
	public double getSpeed() {
		return speed;
	}

	public boolean isInAir() {
		return inAir;
	}

	public void setInAir(boolean inAir) {
		this.inAir = inAir;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
}
