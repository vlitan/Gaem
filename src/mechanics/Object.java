/*developed by A.Potor*/
package mechanics;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Object extends Rectangle {
	private int imageID;
	private int imgType; // 0 = generic image, 1 = level image
	private int collisionLayerID;
	protected boolean movable;
	protected float xf;
	protected float yf;
	protected boolean collidable;
	
	public Object(int x, int y, Dimension size, int imgType, int imageID, int collisionLayerID, boolean isMovable) {
		this.x = x;
		this.y = y;
		this.setSize(size);
		this.setImgType(imgType);
		this.setImageID(imageID);
		this.setCollisionLayerID(collisionLayerID);
		movable = isMovable;
	}


	public void interact(Object obj){
		switch (this.getClass().toString()){
		case (ClassConsts.MOVABLE_OBJECT) : ((MovableObject)this).interact(obj); break;
		default: this.collide(obj);
		}
	}
	
	private void collide(Object obj){
		/*System.out.println("[Object] not really okcollision");
		if(this.movable){
			System.out.println("[Object] NONSENS!!!ERROR");
			Rectangle r = this.intersection(obj);
			int difX = r.width;
			int difY = r.height;
			int both;
			if(obj.movable){
				both = 2;
			}
			else{
				both = 1;
			}
			
			if(difX > difY){
				if(x < obj.x){
					x = x - difX / both;
				}
				else{
					x = x + difX / both;
				}
			}
			else{
				if(y < obj.y){
					y = y - difY / both;
				}
				else{
					y = y + difY / both;
				}
			}
			xf = x;
			yf = y;
		}*/
	}
	
	public Point getUpLeft(){
		return (new Point((int)this.getX(), (int)this.getY()));
	}
	public Point getUpRight(){
		return (new Point((int)(this.getX() + this.getWidth()), (int)this.getY()));
	}
	public Point getDownLeft(){
		return (new Point((int)this.getX(), (int)(this.getY() + this.getHeight())));
	}
	public Point getDownRight(){
		return (new Point((int)(this.getX() + this.getWidth()), (int)(this.getY() + this.getHeight())));
	}
	
	public void ChangeImgID(int imageID) {
		this.setImageID(imageID);
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public int getImgType() {
		return imgType;
	}

	public void setImgType(int imgType) {
		this.imgType = imgType;
	}

	public int getCollisionLayerID() {
		return collisionLayerID;
	}

	public void setCollisionLayerID(int collisionLayerID) {
		this.collisionLayerID = collisionLayerID;
	}
}
