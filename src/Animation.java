
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	private ArrayList<BufferedImage> img = new ArrayList<BufferedImage>();
	private int next = 0;
	private int aux;
	private boolean backAndForth;
	private int indexDirection;
	private int deltaT = 500;

	
	public Animation(ArrayList<BufferedImage> images, boolean animationGoesBackAndForth){
		for(BufferedImage i: images){
			img.add(i);
		}
		next = 0;
		setBackAndForth(animationGoesBackAndForth);
		if(backAndForth)
			indexDirection = 1;
	}
	
	public Animation(boolean animationGoesBackAndForth){
		next = 0;
		setBackAndForth(animationGoesBackAndForth);
		if(backAndForth)
			indexDirection = 1;
	}
	
	public Animation(BufferedImage image, boolean animationGoesBackAndForth){
		img.add(image);
		next = 0;
		setBackAndForth(animationGoesBackAndForth);
		if(backAndForth)
			indexDirection = 1;
	}
	
	public void add(BufferedImage img){
		this.img.add(img);
	}
	
	public void remove(int i){
		this.img.remove(i);
	}
	
	public void remove(BufferedImage image){
		this.img.remove(image);
	}
	
	public void reset(){
		for(int i = img.size(); i>=0; i--){
			img.remove(i);
		}
		next = 0;
	}
	
	public BufferedImage getImage(int index){
		return img.get(index);
	}
	
	public int getNextIndex(int index, long now){
		return incNextIndex(index);
	}
	
	private int incNextIndex(int next){
		int next2 = next;
		if(!backAndForth){
			next2++;
			if(next2 >= img.size())
				next2 = 0;
		}
		else{
			next2 += indexDirection;
			if((next2 < 0)||(next2 >= img.size()))
			{
				indexDirection *= -1;
				next2 += 2*indexDirection;
			}
		}
		return next2;
	}

	public boolean isBackAndForth() {
		return backAndForth;
	}

	public void setBackAndForth(boolean backAndForth) {
		this.backAndForth = backAndForth;
	}

	public int getDeltaT() {
		return deltaT;
	}

	public void setDeltaT(int deltaT) {
		this.deltaT = deltaT;
	}
}
