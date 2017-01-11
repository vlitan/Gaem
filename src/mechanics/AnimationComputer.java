package mechanics;
import java.util.ArrayList;

import gameInput.PlayerCommand;

public class AnimationComputer {
	
	private String creatureTag;
	private boolean lastDirection; //left == true
	private int lastXPos;
	private int lastYPos;
 	
	public AnimationComputer(String creatureTag){
		this.creatureTag = creatureTag;
	}
	
	public String getAnimation(int xPos, int yPos, ArrayList<PlayerCommand> commands){
		String answer = "";
		int xSpeed = xPos - lastXPos;
		int ySpeed = yPos - lastYPos;
		
		if(ySpeed == 0){
			if(xSpeed < 0){
				answer = "WalkLeft";
				lastDirection = true;	
			}
			else if (xSpeed > 0){
				answer = "WalkRight";
				lastDirection = false;
			}
			else{
				if (lastDirection){
					answer = "StaticLeft";
				}
				else{
					answer = "StaticRight";
				}
			}
		}
		else if(ySpeed < 0){
			if(xSpeed < 0){
				answer = "JumpLeft";
				lastDirection = true;	
			}
			else if (xSpeed > 0){
				answer = "JumpRight";
				lastDirection = false;
			}
			else{
				if (lastDirection){
					answer = "JumpLeft";
				}
				else{
					answer = "JumpRight";
				}
			}
		}
		else{//yspeed > 0
			if(xSpeed < 0){
				answer = "FallLeft";
				lastDirection = true;	
			}
			else if (xSpeed > 0){
				answer = "FallRight";
				lastDirection = false;
			}
			else{
				if (lastDirection){
					answer = "FallLeft";
				}
				else{
					answer = "FallRight";
				}
			}
		}
		//TODO add commands override
		lastXPos = xPos;
		lastYPos = yPos;
		return (creatureTag + answer);
	}
	
	boolean aproxEqual(int x, int y, int tol){
		return ((x < y + tol) || (x > y - tol));
	}
	
}
