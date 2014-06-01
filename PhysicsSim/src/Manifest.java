
public class Manifest {
	
	public Object a;
	public Object b;
	public double penetration;
	public Vector normal;
	
	public Manifest (Object objA, Object objB){
		
		this.a = objA;
		this.b = objB;
		
		if ((objA instanceof Circle && objB instanceof AABB) ||( objA instanceof AABB && objB instanceof Circle)){	
			
		} else if (objA instanceof Circle && objB instanceof Circle){
			
			Circle a = (Circle)objA;
			Circle b = (Circle)objB;
			
			
			double r = a.radius + b.radius;
			Vector n = Vector.subtract(b.pos, a.pos);
			
			double d = n.length();
			
			if (d != 0)
			{
				penetration = r - d;
				normal = Vector.multiply(n,-1/d);
			}
			else{
				penetration = a.radius;
				normal = new Vector(1,0);	
			}
			
			
		} else if (objA instanceof AABB && objB instanceof AABB){
			
		}
		
	}
	
	public boolean resolveCollision(){
		
		if (penetration < 0)
			return false;
		
		if (a.immovable)
			a.vel.set(0,0);
		if (b.immovable)
			b.vel.set(0,0);
		
		//Relative Velocity
		Vector rv = Vector.subtract(a.vel,b.vel);
		
		double contactVel = Vector.dot(rv, normal);
		
		if (contactVel > 0)
			return false;
		
		
		double impulse1 = -(1 + a.elasticity)*contactVel;
		impulse1 /= a.massInv + b.massInv;
		
		double impulse2 = -(1+b.elasticity)*contactVel;
		impulse2 /= a.massInv + b.massInv;
		
		a.accel.add(Vector.multiply(normal,impulse1*a.massInv));
		b.accel.subtract(Vector.multiply(normal,impulse2*b.massInv));		
		
		
		
		return true;
		
	}
	
	public void resolveGravitation(){
		if (penetration >= -0.2)
			return;
		double g = 5;
		Vector r = Vector.subtract(b.pos,a.pos);
		Vector norm = Vector.normalize(r);
		Vector force = Vector.multiply(norm,g*a.mass*b.mass/r.lengthSquared());
		
		a.accel.add(Vector.multiply(force,a.massInv));
		b.accel.subtract(Vector.multiply(force,b.massInv));
	}
	
	public void resolvePosition(){
		double portionA;
		double portionB;
		
		if (a.immovable && !b.immovable){
			portionA = 0;
			portionB = 1;
		}else if (!a.immovable && b.immovable){
			portionA = 1;
			portionB = 0;
		} else{
			 portionA = b.mass/(a.mass+b.mass);
			 portionB = a.mass/(a.mass+b.mass);
		}
			
		
			a.pos.add(Vector.multiply(normal,(portionA)*(Math.max(penetration,0))));
			b.pos.subtract(Vector.multiply(normal,(portionB)*(Math.max(penetration,0))));
	}
}
