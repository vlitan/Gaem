package mechanics;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class Player extends MovableObject{

	private int life = 100;
	private int damage = 10;
	
	public Player(int x, int y, Dimension size, int imgType, int imageID, int collisionLayerID, String tag) {
		super(x, y, size, imgType, imageID, collisionLayerID, tag);
		// TODO Auto-generated constructor stub
	}

	public void interact(Object obj){
		switch (obj.getClass().toString()){
		case (ClassConsts.MOVABLE_OBJECT) : System.out.println("I encountered an enemy"); break;
		case (ClassConsts.ENEMY) : encounteredEnemy((Enemy)obj); break;
		default : collide(obj);
		}
	}
	
	private void encounteredEnemy(Enemy enemy) {
		System.out.println("[Player] encountered enemy " + enemy.tag);
		collide(enemy);
	}
	
	
	public void getHurt(int damage){
		this.setLife(this.getLife() - damage);
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
		if (life <= 0){
			System.out.println("[Player] I`m DEAD!!!");	
		}
		else{
			System.out.println("[Player] remainng life: " + this.getLife());
		}
			
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

}
