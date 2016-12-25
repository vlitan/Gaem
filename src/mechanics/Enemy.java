package mechanics;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class Enemy extends MovableObject{

	private int damage = 10;
	private int life = 10;
	
	public Enemy(int x, int y, Dimension size, int imgType, int imageID, int collisionLayerID, String tag) {
		super(x, y, size, imgType, imageID, collisionLayerID, tag);
		// TODO Auto-generated constructor stub
	}
	
	public void interact(Object obj){
		switch (obj.getClass().toString()){
		case (ClassConsts.MOVABLE_OBJECT) : /*nothing now*/ break;
		case (ClassConsts.PLAYER) : encounteredPlayer((Player)obj); break;
		default : super.collide(obj);
		}
	}

	private void encounteredPlayer(Player player) {
		player.getHurt(this.getDamage());
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
}
