import java.awt.Point;

// Axis Aligned Bounding Box
public class AABB extends Object{
	
	Point min;
	Point max;
	
	public AABB (Point min, Point max){
		super(new Vector(min), 1, 0);
		this.min = new Point(min);
		this.max = new Point(max);
	}
	
	
	public boolean collides(AABB b){
		
		AABB a = this;
	
		// Exit with no intersection if found separated along an axis
		if(a.max.x < b.min.x || a.min.x > b.max.x) return false;
		if(a.max.y < b.min.y || a.min.y > b.max.y) return false;
						
		// No separating axis found
		return true;
				  
	}


}
