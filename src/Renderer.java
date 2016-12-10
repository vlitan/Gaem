/*developed by A.Potor*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import mechanics.Object;

public class Renderer {
	Graphics g;
	int resX, resY;
	BufferedImage img[];
	int deltaT = 20;
	long lastNow;
	
	public Renderer(JPanel panel) {
		g = panel.getGraphics();
		resX = panel.getWidth();
		resY = panel.getHeight();
	}

	public void loadGenericImages() {
		int n = 0;
		img = new BufferedImage[4];
		loadGenericImage(n++, "Data/Images/LeftWalk.png"); // 0
		loadGenericImage(n++, "Data/Images/RightWalk.png"); // 1
		loadGenericImage(n++, "Data/Images/LeftJump.png"); // 2
		loadGenericImage(n++, "Data/Images/RightJump.png"); // 3
	}

	private void loadGenericImage(int n, String address) {
		try {
			img[n] = ImageIO.read(new File(address));
		} catch (IOException e) {
			System.out.println("Image couldn't be loaded.");
		}
	}

	public void drawObjects(ArrayList<Object> objs) {
		for (Object o : objs) {
			drawObject(o);
		}
	}
	
	public void drawObject(Object obj){
		g.drawImage(img[obj.getImageID()], obj.x, obj.y, obj.getSize().width,obj.getSize().height, null);
	}

	public void clear(long now) {
		if (now < lastNow){
			lastNow = Long.MAX_VALUE - lastNow + now;
			System.out.println("[Creature] ROLL OVER!");
		}
		if (now - lastNow > deltaT){
			g.clearRect(0, 0, resX, resY);
			lastNow = now;
		}
	}
}
