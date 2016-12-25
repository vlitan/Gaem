/*developed by V.Litan and A.Potor*/
package mechanics;

import java.awt.Dimension;
import java.util.ArrayList;
import gameInput.PlayerCommand;

@SuppressWarnings("serial")
public class MovableObject extends Object{

	private float xSpeedIncrement = 0.02f;
	private float ySpeedIncrement = 0.02f;
	private float jumpIncrement = 0.2f;
	private float frictionCoefficient = 0.97f;
	private float gravityForce = 3;
	private float xMaxSpeed = 10;
	private float yMaxSpeed = 10;
	private float xSpeed = 0;
	private float ySpeed = 0;
	protected float lastXf;
	protected float lastYf;
	

	
	//all in ms
	private long lastNow;
	private int deltaT = 1;
	
	public MovableObject(int x, int y, Dimension size, int imgType, int imageID, int collisionLayerID, String tag) {
		super(x, y, size, imgType, imageID, collisionLayerID,true, tag);
		xf = x;
		yf = y;
	}
	
	/*runs a list of commands*/
	public void executeCommands(ArrayList<PlayerCommand> commands, long now){
		if (now < lastNow){
			lastNow = Long.MAX_VALUE - lastNow + now;
			System.out.println("[Creature] ROLL OVER!");
		}
		if (now - lastNow > deltaT){
			
			lastXf = xf;
			lastYf = yf;
			
			for (PlayerCommand com : commands){
				if (com == PlayerCommand.LEFT) xSpeed -= xSpeedIncrement;
				if (com == PlayerCommand.RIGHT) xSpeed += xSpeedIncrement;
				if (com == PlayerCommand.UP) ySpeed -= ySpeedIncrement;
				if (com == PlayerCommand.DOWN) ySpeed += ySpeedIncrement;
				if (com == PlayerCommand.SHOOT) ySpeed -= jumpIncrement;
			}
			speedToBounds();
			friction();
		    gravity();
			updatePosition();

		  
			lastNow = now;
		}
	}
	
	private void gravity() {
		yf += gravityForce;
	}

	public void interact(Object obj){
		switch (obj.getClass().toString()){
		case (ClassConsts.MOVABLE_OBJECT) : System.out.println("sam"); break;
		default : collide(obj);
		}
	}

	public void interact(MovableObject obj){
		System.out.println("[MovableObject] interact with another MovableObject");
	}
	
	protected void collide(Object obj){
		switch(getCollisionSideA(obj)){
		case LEFT: this.setX((int) (obj.getX() - this.getWidth()));//System.out.println("Left");
			break;
		case RIGHT:this.setX((int) (obj.getX() + obj.getWidth())); //System.out.println("right");
			break;
		case UP: this.setY((int) (obj.getY() - this.getHeight())); // System.out.println("up");
			break;
		case DOWN: this.setY((int) (obj.getY() + obj.getHeight()));//System.out.println("down");
			break;
		default:
			break;
		}
	}
	
	private void updatePosition(){
		xf += xSpeed;
		yf += ySpeed;
		x = (int) xf;
		y = (int) yf;
	}
	
	private void speedToBounds(){
		if (Math.abs(xSpeed) >= xMaxSpeed){
			xSpeed = xMaxSpeed * Math.signum(xSpeed);
		}
		if (Math.abs(ySpeed) >= yMaxSpeed){
			ySpeed = yMaxSpeed * Math.signum(ySpeed);
		}
	}
	
	private void friction(){
		xSpeed *= frictionCoefficient;
		ySpeed *= frictionCoefficient;
	}
	
	public void setX(int x){
		this.x = x;
		this.xf = x;
	}
	
	public void setY(int y){
		this.y = y;
		this.yf = y;
	}

	
	private CollisionSide getCollisionSideA(Object obj){
		if ((obj.contains(this.getDownRight()) || obj.contains(this.getUpRight())) && 
			!obj.contains(this.getUpLeft())  && !obj.contains(this.getDownLeft())){
			if (((int)(lastYf + this.getHeight()) > obj.getY()) && ((int)(lastYf + this.getHeight()) < obj.getY() + obj.getHeight()) || 
				((lastYf > obj.getY()) && (lastYf < obj.getY() + obj.getHeight())))
				return (CollisionSide.LEFT);
		}
		if ((obj.contains(this.getDownLeft()) || obj.contains(this.getUpLeft())) && 
			!obj.contains(this.getUpRight())  && !obj.contains(this.getDownRight())){
			if (((lastYf + this.getHeight() > obj.getY()) && (lastYf + this.getHeight() < obj.getY() + obj.getHeight())) || 
				((lastYf > obj.getY()) && (lastYf < obj.getY() + obj.getHeight())))
				return (CollisionSide.RIGHT);
		}
		if ((obj.contains(this.getUpRight()) || obj.contains(this.getUpLeft())) &&
			!obj.contains(this.getDownRight())  && !obj.contains(this.getDownLeft())){
			return (CollisionSide.DOWN);
		}
		if ((obj.contains(this.getDownRight()) || obj.contains(this.getDownLeft())) &&
			!obj.contains(this.getUpRight())  && !obj.contains(this.getUpLeft())){
			return (CollisionSide.UP);
		}
		return (CollisionSide.NO_COLLISION);
	}
	
	@Override
	public String getType() {
		return (this.getClass().toString());
	}
}


