import java.awt.Point;


public class Vector {
	
	double x;
	double y;

	public Vector(Vector v) {
		x = v.x;
		y = v.y;
	}
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Vector(Point p){
		this.x = p.x;
		this.y = p.y;
	}
	
	public void set(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector v){
		this.x = v.x;
		this.y = v.y;
	}
	
	public double lengthSquared(){
		return x*x + y*y;
	}
	
	public double length(){
		return Math.sqrt(lengthSquared());
	}

	public static double dot (Vector a, Vector b){
		return a.x*b.x + a.y*b.y;
	}
	
	public double dot (Vector b){
		return dot(this,b);
	}
	
	public Vector add(Vector b){
		x += b.x;
		y += b.y;
		return this;
	}
	
	public Vector subtract(Vector b){
		x -= b.x;
		y -= b.y;
		return this;
	}
	
	public Vector multiply(double scaling){
		x *= scaling;
		y *= scaling;
		return this;
	}
	
	public static Vector multiply (Vector v, double scaling){
		Vector v2 = new Vector(v);
		v2.multiply(scaling);
		return v2;
	}
	
	public static Vector add(Vector a, Vector b){
		Vector c = new Vector(a);
		c.add(b);
		return c;
	}
	
	public static Vector subtract(Vector a, Vector b){
		Vector c = new Vector(a);
		c.subtract(b);
		return c;
	}
	
	public static double cross (Vector a, Vector b){
		return a.x*b.y - a.y*b.x;
	}
	
	public double cross (Vector b){
		return cross(this,b);
	}
	
	public Vector normalize (){
		if (x != 0 || y != 0)
		{
			double length = length();
			x /= length;
			y /= length;
		}
		return this;
	}
	
	public static Vector normalize(Vector v){
		Vector n = new Vector(v);
		n.normalize();
		return n;
	}
	

	public String toString(){
		return "X: " + x + " Y: " + y;
	}
}
