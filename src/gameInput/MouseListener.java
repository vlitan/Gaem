/*developed by V.Litan*/
package gameInput;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;


public class MouseListener implements java.awt.event.MouseListener {

	private Point position;
	private boolean[] buttonState;
	private int clickCount;
	
	public MouseListener(JPanel panel){
		super();
		buttonState = new boolean[3];
		panel.addMouseListener(this);
		position = new Point();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//logMouse(e, "clicked");
		//do nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttonState[e.getButton() - 1] = true;
		position = e.getPoint();
		clickCount = e.getClickCount();
		logMouse(e, "pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttonState[e.getButton() - 1] = false;
		position = e.getPoint();
		logMouse(e, "released");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	//	logMouse(e, "entered");
		//do nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
	//	logMouse(e, "exited");
		//do nothing
	}
	
	private void logMouse(MouseEvent e, String action){
		System.out.println("[MouseListener] Mouse " + action + " button " + e.getButton() + "\t@ x: " + e.getX() + "\ty: " + e.getY() + "\ttimes: " + e.getClickCount());
	}
	
	public String toString(){
		String rez = "";
		rez += "X: " + position.x + "\tY: " + position.y;
		rez += " clickCount : " + clickCount;
		rez += " Left: " + buttonState[0] + "\t Right: " + buttonState[2] + "\t Wheel: " + buttonState[1];
		return (rez);
	}
	
	public Point getPoisition(){
		return (position);
	}
	
	public boolean getButton(int index){
		return (buttonState[index - 1]);
	}
	
	public int getClickCount(){
		return (clickCount);
	}
	
	

}
