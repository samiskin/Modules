import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;


public class ObjectHandler {
	

	public ArrayList <Object> objects;
	public LinkedList <Manifest> manifests;
	
	public ObjectHandler(){
		objects = new ArrayList <Object>();
	}
	
	
	public void addObject(Object o){
		objects.add(o);
	}
	
	public void resolveInteractions(){

		manifests = new LinkedList<Manifest>();
		for (int i = 0; i < objects.size()-1; i++){
			for (int j = i+1; j < objects.size(); j++){
				manifests.add(new Manifest(objects.get(i), objects.get(j)));
			}
		}
		
		for (Manifest m : manifests){
			//m.resolveGravitation();
			
			m.resolveCollision();
			
		}
		
		
	}
	
	public void run(){
		resolveInteractions();
		for (Manifest m : manifests)
			m.resolvePosition();
		for (Object o : objects)
			o.run();
	}


	public void draw(Graphics g) {
		
		
		for (Object o : objects)
			o.draw(g);
		
	}
	
	
	
	
	
	
}
