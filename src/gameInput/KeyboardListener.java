/*developed by V.Litan*/

package gameInput;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;


public class KeyboardListener implements KeyListener{
	
	private ArrayList<Integer> pressedKeys;
    private Map<PlayerCommand, ArrayList<Integer>> commandsMap;
    private PlayerCommand comm;
	ArrayList<PlayerCommand> comms; 
	public KeyboardListener(JPanel panel){
		super();
		panel.addKeyListener(this);
		initCommandsMap();
		pressedKeys = new ArrayList<Integer>();
		comms = new ArrayList<PlayerCommand>();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//doNothing
		//logKey(e, "typed");
	}

	public ArrayList<PlayerCommand> getCommands(){
		return (new ArrayList<PlayerCommand>(comms));
	}
	
	public List<Integer> getPressedKeys(){
		return (new ArrayList<Integer>(pressedKeys));
	}
	
	@Override
	public void keyPressed(KeyEvent e) {	
		if (!pressedKeys.contains((Integer) e.getKeyCode()))
			pressedKeys.add(e.getKeyCode());
		//logKey(e, "pressed");
		comm = keyToCommand(e.getKeyCode());
		//TODO
		if ((!comms.contains((PlayerCommand)comm)) && (comm != PlayerCommand.EMPTY)){
			comms.add(comm);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressedKeys.remove((Integer) e.getKeyCode());
		//logKey(e, "released");
		comms.remove((PlayerCommand) keyToCommand(e.getKeyCode()));
	}
	
	private void initCommandsMap(){
		commandsMap = new HashMap<PlayerCommand, ArrayList<Integer>>();
		commandsMap.put(PlayerCommand.UP, keyMappings.UP_KEYS);
		commandsMap.put(PlayerCommand.DOWN, keyMappings.DOWN_KEYS);
		commandsMap.put(PlayerCommand.RIGHT, keyMappings.RIGHT_KEYS);
		commandsMap.put(PlayerCommand.LEFT, keyMappings.LEFT_KEYS);
		commandsMap.put(PlayerCommand.SHOOT, keyMappings.SHOOT_KEYS);
		commandsMap.put(PlayerCommand.INTERACT, keyMappings.INTERACT_KEYS);
		commandsMap.put(PlayerCommand.EMPTY, keyMappings.EMPTY_KEYS);
		commandsMap.put(PlayerCommand.ESCAPE, keyMappings.ESCAPE_KEYS);
	}
	
	private PlayerCommand keyToCommand(Integer keyCode){
		for(PlayerCommand com : PlayerCommand.values()){
			if (commandsMap.get(com).contains(keyCode)){
				return (com);
			}
		}
		return (PlayerCommand.EMPTY);
	}
	
	private void logKey(KeyEvent e, String action){
		System.out.println("[KeyboardListener] Key " + e.getKeyChar() + " " + action + ". Code " + e.getKeyCode());
	}
	
	public String toStringKeys(){
		String rez = "";
		ArrayList<Integer> pressedKeys =  (ArrayList<Integer>)this.getPressedKeys();
		rez = "" + pressedKeys.size() + " currently pressed keys:";
		for(int i = 0; i < pressedKeys.size(); i++){
			rez += " " + pressedKeys.get(i) + " ";
		}
		return (rez);
	}
	
	public String toStringComms(){
		String rez = "";
		try{
			ArrayList<PlayerCommand> auxComms = this.getCommands();
			rez = "" + auxComms.size() + " currently active commands:";
			for(int i = 0; i < auxComms.size(); i++){
				rez += " " + auxComms.get(i).toString() + " ";
			}
		}
		catch(Exception e){
			System.out.println("[KeyboardListener] I catched " + e.toString());
		}
		return (rez);
	}
	
}
