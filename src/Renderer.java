/*developed by A.Potor*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import mechanics.ClassConsts;
import mechanics.MovableObject;
import mechanics.Object;

public class Renderer {
	Graphics g;
	int resX, resY;
	ArrayList<Animation> img = new ArrayList<Animation>();
	int deltaT = 20;
	BufferedImage workspace;
	Graphics g2;
	Map<String, Integer> animationMap;
	
	public Renderer(JPanel panel) {
		g = panel.getGraphics();
		resX = panel.getWidth();
		resY = panel.getHeight();
		workspace = new BufferedImage(resX, resY, BufferedImage.TYPE_INT_ARGB);
		g2 = workspace.getGraphics();
		animationMap = new HashMap<String, Integer>();
	}

	public void loadGenericImages() {
		int n = 0;
		newAnimation(n, true);
		loadGenericImage(n, "Data/Images/1.png"); // 0
		loadGenericImage(n, "Data/Images/2.png"); // 0
		loadGenericImage(n++, "Data/Images/3.png"); // 0
		animationMap.put("rand1", n-1);
		newAnimation(n, false);
		img.get(n).setDeltaT(300);
		loadGenericImage(n, "Data/Images/3.png"); // 1
		loadGenericImage(n, "Data/Images/2.png"); // 1
		loadGenericImage(n++, "Data/Images/1.png"); // 2
		animationMap.put("rand2", n-1);
		loadGenericImage(n, "Data/Images/PlayerWalkLeft0.png"); // 2
		loadGenericImage(n++,  "Data/Images/PlayerWalkLeft1.png"); // 2
		animationMap.put("playerWalkLeft", n-1);
		loadGenericImage(n, "Data/Images/PlayerWalkRight0.png"); // 2
		loadGenericImage(n++,  "Data/Images/PlayerWalkRight1.png"); // 2
		animationMap.put("playerWalkRight", n-1);
		loadGenericImage(n, "Data/Images/PlayerJumpLeft0.png"); // 2
		loadGenericImage(n++,  "Data/Images/PlayerJumpLeft1.png"); // 2
		animationMap.put("playerJumpLeft", n-1);
		loadGenericImage(n, "Data/Images/PlayerJumpRight0.png"); // 2
		loadGenericImage(n++,  "Data/Images/PlayerJumpRight1.png"); // 2
		animationMap.put("playerJumpRight", n-1);
		loadGenericImage(n, "Data/Images/PlayerFallLeft0.png"); // 2
		loadGenericImage(n++,  "Data/Images/PlayerFallLeft1.png"); // 2
		animationMap.put("playerFallLeft", n-1);
		loadGenericImage(n, "Data/Images/PlayerFallRight0.png"); // 2
		loadGenericImage(n++,  "Data/Images/PlayerFallRight1.png"); // 2
		animationMap.put("playerFallRight", n-1);
		loadGenericImage(n++,  "Data/Images/PlayerStaticRight0.png"); // 2
		animationMap.put("playerStaticRight", n-1);
		loadGenericImage(n++,  "Data/Images/PlayerStaticLeft0.png"); // 2
		animationMap.put("playerStaticLeft", n-1);
	}
	
	//loads the image into the arraylist of animations, into the desired animation
	public void loadGenericImage(int animationIndex, String address) {  	
		try {
			if(((animationIndex>=0)&&(animationIndex<img.size()))&&(img.get(animationIndex) != null))
				img.get(animationIndex).add(ImageIO.read(new File(address)));
			else
				img.add(animationIndex, new Animation(ImageIO.read(new File(address)), false));
		} catch (IOException e) {
			System.out.println("Image couldn't be loaded.");
		}
	}
	
	public void newAnimation(int animationIndex, boolean goesBackAndForth){
		img.add(animationIndex, new Animation(goesBackAndForth));
	}

	public void drawObjectArray(Object[] obj, long now) {
		for (int i = 0; i < obj.length; i++) {
			drawObject(obj[i], now);
		}
	}
	
	public void drawObject(Object obj, long now){
		if (!obj.getClass().toString().equals(ClassConsts.OBJECT)){
			int newImageID = animationMap.get(((MovableObject)obj).getCurrentAnimation());
			if (newImageID != obj.getImageID()){
				obj.changeImage(newImageID);
			}
		}
		Animation a = img.get(obj.getImageID());
		g2.drawImage(a.getImage(obj.getAnimationIndex()), obj.x, obj.y, obj.getSize().width, obj.getSize().height, null);
		obj.setNextAnimationIndex(obj.getAnimationIndex(), a.getNextIndex(obj.getAnimationIndex(), now), a.getDeltaT(), now);
	}

	public void clear(long now) {
		g2.clearRect(0, 0, resX, resY);
	}
	
	public void drawFrame(){
		g.drawImage(workspace, 0, 0, resX, resY, null);
	}

	public void drawObjects(ArrayList<Object> objs, long now) {
		for (Object o : objs) {
			drawObject(o, now);
		}
	}
}
