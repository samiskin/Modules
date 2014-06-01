import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;


public class Engine {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final double FRAMERATE = 120;
	
	ArrayList<Color> colors;
	
	Vector clickStart;
	
	ObjectHandler objectHandler;
	
	public Engine(){

		colors = new ArrayList<Color>();
		colors.add(Color.blue);
		colors.add(Color.cyan);
		colors.add(Color.green);
		colors.add(Color.magenta);
		colors.add(Color.orange);
		colors.add(Color.red);
		
		objectHandler = new ObjectHandler();
		
	}
	
	
	public void run(){
		
		
		objectHandler.run();
		
	}
	
	public void draw(Graphics g){
		
		objectHandler.draw(g);
	}
	

	
	public void mousePressed(MouseEvent event)
	{
		if (event.getButton() == MouseEvent.BUTTON3)
		{
			Circle c = new Circle(new Vector(event.getPoint()),3000,Color.orange);
			c.toggleMoveable();
			objectHandler.addObject(c);
		}
		else if (event.getButton() == MouseEvent.BUTTON2){

			Circle c = new Circle(new Vector(event.getPoint()),3000,Color.orange);
			c.toggleMoveable();
			objectHandler.addObject(c);
		}
		else
			clickStart = new Vector(event.getPoint());
		
	}

	public void mouseReleased(MouseEvent event)
	{
		if (clickStart == null)
			return;
		Vector clickEnd = new Vector(event.getPoint());
		Vector dif = Vector.subtract(clickEnd, clickStart);
		double mass = Math.random()*300+700;
		Circle c = new Circle(clickStart, mass, Color.green);
		c.vel = dif.multiply(0.1);
		objectHandler.addObject(c);
		clickStart = null;
		
	}

	public void mouseMoved(MouseEvent event)
	{
		
	}
	
	public void mouseDragged(MouseEvent event)
	{		
	
	}
	
	public void keyPressed(KeyEvent event)
	{
		
	}

}
