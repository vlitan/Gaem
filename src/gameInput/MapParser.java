//Developed by V.Litan

package gameInput;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import mechanics.MovableObject;
import mechanics.Object;

public class MapParser {
	private String mapPath;
	private File file;
	private Scanner scan;
	
	public MapParser(String mapPath){
		this.setMapPath(mapPath);
		file = new File(this.mapPath);
		scan = null;
	}
	
	public void openFile() throws IOException{
		scan = new Scanner(file);
		scan.useDelimiter(Pattern.compile(";"));
	}
	
	private String removeComments(String in){
		return (in.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)", ""));
	}
	
	private String getType(String in){
		return (in.split("-")[0].replaceAll("\n", "").replaceAll("\r", "").replaceAll(" ", ""));
	}
	
	private String[] getParams(String in){
		String[] aux = in.split(",");
		for (int i = 0; i < aux.length; i++){
			aux[i] = aux[i].replaceAll(" ", "");
			aux[i] = aux[i].split(":")[0];
		}
		return (aux);
	}
	
	private String[] getParamValues(String in){
		String[] aux = in.split(",");
		for (int i = 0; i < aux.length; i++){
			aux[i] = aux[i].replaceAll(" ", "");
			aux[i] = aux[i].split(":")[1];
		}
		return (aux);
	}
	
	private String getNextLogicalLine(){
		return (removeComments(scan.next()).replaceAll("\n", "").replaceAll("\r", ""));
	}

	
	public Object getNextObject(){
		String nextLine = getNextLogicalLine();
		String type = getType(nextLine);
		nextLine = nextLine.substring(nextLine.indexOf("-") + 1);
		switch (type){
		case "Object" : return (createObject(nextLine));
		case "MovableObject" : return (createMovableObject(nextLine));
		}
		return (null);
	}
	
	private MovableObject createMovableObject(String nextLine) {
		String[] params = getParams(nextLine);
		String[] values = getParamValues(nextLine);
		MovableObject mo = new MovableObject(1,1,new Dimension(1, 1), 1, 1, 1, "default");
		for (int i = 0; i < params.length; i++){
			switch (params[i]){
			case "x" : mo.setX(Integer.parseInt(values[i])); break;
			case "y" : mo.setY(Integer.parseInt(values[i])); break;
			case "width" : mo.setWidth(Integer.parseInt(values[i])); break;
			case "height" : mo.setHeight(Integer.parseInt(values[i])); break;
			case "imgType" : mo.setImgType(Integer.parseInt(values[i])); break;
			case "imageID" : mo.setImageID(Integer.parseInt(values[i])); break;
			case "collisionLayerID" : mo.setCollisionLayerID(Integer.parseInt(values[i])); break;
			case "tag" : mo.setTag(values[i]); break;
			}
		}
		return (mo);
	}

	private Object createObject(String nextLine) {
		String[] params = getParams(nextLine);
		String[] values = getParamValues(nextLine);
		Object o = new Object(1,1,new Dimension(1, 1), 1, 1, 1, false, "default");
		for (int i = 0; i < params.length; i++){
			switch (params[i]){
			case "x" : o.setX(Integer.parseInt(values[i])); break;
			case "y" : o.setY(Integer.parseInt(values[i])); break;
			case "width" : o.setWidth(Integer.parseInt(values[i])); break;
			case "height" : o.setHeight(Integer.parseInt(values[i])); break;
			case "imgType" : o.setImgType(Integer.parseInt(values[i])); break;
			case "imageID" : o.setImageID(Integer.parseInt(values[i])); break;
			case "collisionLayerID" : o.setCollisionLayerID(Integer.parseInt(values[i])); break;
			case "movable" : o.setMovable(Boolean.parseBoolean(values[i])); break;
			case "tag" : o.setTag(values[i]); break;
			}
		}
		return (o);
	}

	public boolean fileEnded(){
		return (!scan.hasNext());
	}
	
	public void closeFile(){
		scan.close();
	}

	public String getMapPath() {
		return mapPath;
	}

	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}
	
					
}
