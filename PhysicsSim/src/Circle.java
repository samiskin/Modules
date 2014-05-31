import java.awt.Color;
import java.awt.Graphics;



public class Circle extends Object{
	
	double radius;
	Color color;
	
	public Circle(Vector pos, double radius, double mass, double restitution, Color color){
		super(pos, mass, restitution);
		this.radius = radius;
		this.color = color;
	}
	
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillOval((int)(pos.x-radius), (int)(pos.y-radius), (int)radius*2, (int)radius*2);
	}

	public void bounceWall(){
		if (pos.x - radius < 0 || pos.x + radius > Engine.WIDTH)
		{
			vel.x *= -1;
			pos.x += vel.x;
		}
		if (pos.y - radius < 0 || pos.y + radius > Engine.HEIGHT)
		{
			vel.y *= -1;
			pos.y += vel.y;
		}
	}
	
	public void run(){
		super.run();

	}
	
	public String toString(){
		return "R: " + radius + " " + pos;
	}
	
}
