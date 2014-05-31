import java.awt.Graphics;


public abstract class Object {
	
	double mass;
	double massInv;
	
	boolean immovable = false;
	
	double elasticity;
	
	Vector pos;
	
	Vector vel;
	Vector accel;

	public Object (Vector pos, double mass, double elasticity){
		this.pos = new Vector(pos);
		
		this.mass = mass;
		if (mass != 0)
			this.massInv = 1/mass;
		else
			this.massInv = 0;
		
		this.elasticity = elasticity;
		
		vel = new Vector(0,0);
		accel = new Vector(0,0);
	}
	
	public boolean toggleMoveable(){
		immovable = !immovable;
		return immovable;
	}
	
	
	
	public void run(){
		vel.add(accel);
		if (immovable)
			vel.set(0,0);		
		pos.add(vel);
		accel.set(0, 0);
	}
	
	public void draw(Graphics g){
		
	}
	
}
