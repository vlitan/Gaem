/*developed by V.Litan and A.Potor*/
package mechanics;

import java.util.ArrayList;

public class CollisionManger {
	private ArrayList<ArrayList<Object>> layers;
	
	public  CollisionManger(){
		layers = new ArrayList<ArrayList<Object>>();
		for(int i = 0; i < 5; i++){
			layers.add(new ArrayList<Object>());
		}
	}
	
	public void manageCollisions(){
		for(int i = 0; i < layers.size(); i++){
			manageCollisionLayer(i);
		}
	}
	
	private void manageCollisionLayer(int id){
		int layerSize = layers.get(id).size();
		for(int i = 0; i < layerSize; i++){
			for (int j = i + 1; j < layerSize; j++){
				if (layers.get(id).get(i).intersects(layers.get(id).get(j))){
					layers.get(id).get(i).interact(layers.get(id).get(j));
					layers.get(id).get(j).interact(layers.get(id).get(i));
				}
			}
		}
	}
	
	public void addObject(Object obj){
	// TODO dynamic init layers	if (layers.size() < obj.getCollisionLayerID())
		layers.get(obj.getCollisionLayerID()).add(obj);
	}
	
	public void addObjects(ArrayList<Object> objs){
		for (Object o : objs){
			addObject(o);
		}
	}
	
	public void removeObject(Object obj){
		layers.get(obj.getCollisionLayerID()).remove(obj);
	}
}
