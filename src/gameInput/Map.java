package gameInput;

import java.io.IOException;
import java.util.ArrayList;
import mechanics.Object;

@SuppressWarnings("serial")
public class Map extends ArrayList<Object> {
	MapParser mp;
	public Map(String mapPath){
		mp = new MapParser(mapPath);
	}
	
	public void readObjects(){
		Object aux;
		try {
			mp.openFile();
		} catch (IOException e){
			e.printStackTrace();
		}
		while ((aux = mp.getNextObject()) != null){
			this.add(aux);
		}
	}
	
}
