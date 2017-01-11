/*developed by A.Potor and V.Litan*/

import gameInput.*;
import mechanics.AnimationComputer;
import mechanics.CollisionManger;
import mechanics.Enemy;
import mechanics.MovableObject;
import mechanics.Object;
import mechanics.Player;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main{
   private static JFrame mainFrame;
   private static JPanel mainPanel;
   private static KeyboardListener input; 
   private static Renderer rend;
   private static Dimension size;
   private static Player player;
   private static Enemy enemy;
   private static Object obstacle;
   private static CollisionManger collisionManager;
   private static Map map;
   private static int drawDelay = 20; //milliseconds between displaying the next frame
   private static long lastNow = 0;

   public static void main(String[] args){
	   prepareGUI();
	   player = new Player(60, 60, new Dimension(40,80), 0, 0, 0, "player");
	   enemy = new Enemy(600, 10, new Dimension(40,80), 0, 0, 0, "enemy");
	  // obstacle = new Object(size.width/3, size.height/3, new Dimension(200,300), 0, 1, 0,false, "obstacke");
	   collisionManager = new CollisionManger();
	   
	   map = new Map("Data/Maps/Level01");
	   map.readObjects();
	   map.add(player);
	  // map.add(enemy);
	   collisionManager.addObjects(map);
	   run();
   }
   
   public static void run(){
	   while (true){   
		   player.executeCommands(input.getCommands(), now());
		   collisionManager.manageCollisions();
		   
		   //drawing
		   if(delay(now(), drawDelay)){
			   rend.clear(now());
			   rend.drawObjects(map, now());
			   rend.drawFrame();
		   }  
		 //  System.out.println("[Main] " + input.toStringComms());
	   }
   }

   public static long now(){
	   return (System.currentTimeMillis() % Long.MAX_VALUE);
   }
   
   private static void prepareGUI(){
	  size = new Dimension(1000,1000);
      mainFrame = new JFrame("Main frame");
      mainFrame.setSize(size);
      //mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      mainPanel = new JPanel();
      mainPanel.setLayout(new FlowLayout());
      mainPanel.setFocusable(true);
      mainPanel.requestFocusInWindow();
      mainPanel.setBackground(Color.BLUE);
      input = new KeyboardListener(mainPanel);
      mainFrame.add(mainPanel);
      mainFrame.setVisible(true);  
      rend = new Renderer(mainPanel);
	  rend.loadGenericImages();
   } 
   
   private static boolean delay(long now, int deltaT){
	   	if (now < lastNow){
			lastNow = Long.MAX_VALUE - lastNow + now;
			System.out.println("[Creature] ROLL OVER!");
		}
		if (now - lastNow > deltaT){
			lastNow = now;
			return true;
		}
		else 
			return false;
  }
}
