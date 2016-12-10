/*developed by V.Litan*/

package gameInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;


public class InputListener {
    private KeyboardListener keyInput;
    private MouseListener mouseInput;
    private Map<PlayerCommand, ArrayList<Integer>> commandsMap;
    
	public InputListener(JPanel panel){
		keyInput = new KeyboardListener(panel);
		mouseInput = new MouseListener(panel);
		initCommandsMap();
	}
	
	public ArrayList<PlayerCommand> getCommands(){
		ArrayList<PlayerCommand> comms = new ArrayList<PlayerCommand>();
		ArrayList<Integer> pressedKeys = new ArrayList<Integer>(keyInput.getPressedKeys());
		PlayerCommand comm;
		for(Integer key : pressedKeys){
			comm = keyToCommand(key);
			//System.out.println("[InputListener] key: " + key + "\tgenerated command: " + comm.toString());
			if (comm != PlayerCommand.EMPTY){
				comms.add(comm);
			}
		}
		return (new ArrayList<PlayerCommand>(comms));
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
}
