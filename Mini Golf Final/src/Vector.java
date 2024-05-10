
public class Vector {
	public double x;
	public double y;
	public double theta;
	
	
	public Vector(int x, int y, int t) {
		this.x = x;
		this.y = y;
		this.theta = t;
	}
	
	public double getMagnitude() {
		return(Math.sqrt(x*x + y*y));
	}
	
	public void add(Vector other) {
		x += other.x;
		y += other.y;
	}

}
