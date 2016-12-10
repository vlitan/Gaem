/*developed by A.Potor and V.Litan*/

import gameInput.*;
import mechanics.CollisionManger;
import mechanics.MovableObject;
import mechanics.Object;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main{
   private static JFrame mainFrame;
   private static JPanel mainPanel;
   private static KeyboardListener input; 
   private static Renderer rend;
   private static Dimension size;
   private static MovableObject player;
   private static Object obstacle;
   private static CollisionManger collisionManager;
   private static Map map;
   public static void main(String[] args){
	   prepareGUI();
	   player = new MovableObject(30, 30, new Dimension(40,80), 0, 0, 0, "player");
	   obstacle = new Object(size.width/3, size.height/3, new Dimension(200,300), 0, 1, 0,false, "obstacke");
	   collisionManager = new CollisionManger();
	   map = new Map("Data/Maps/Level01");
	   map.readObjects();
	   map.add(player);
	   collisionManager.addObjects(map);
	   run();
   }
   
   public static void run(){
	   while (true){
		   rend.clear(now());
		   player.executeCommands(input.getCommands(), now());
		   collisionManager.manageCollisions();
		   rend.drawObjects(map);
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
}
