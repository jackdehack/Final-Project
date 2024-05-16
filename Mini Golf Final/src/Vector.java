
public class Vector {
	public double x;
	public double y;
	public double theta;
	
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double magnitude() {
		return(Math.sqrt(x*x + y*y));
	}
	
	public void add(Vector other) {
		x += other.x;
		y += other.y;
	}
	
	public Vector multiply(double factor) {
		return new Vector(x * factor,y * factor);
	}
	
	public Vector subtract(Vector other) {
		return new Vector(x - other.x,y - other.y);
	}
	
	public double dotProduct(Vector other) {
		return this.x*other.x + this.y*other.y;
	}
	//jhkhkhjkh
	
	public Vector rotate(double theta) {
		double cosTheta = Math.cos(theta);
	    double sinTheta = Math.sin(theta);
	    
	    double newX = x * cosTheta - y * sinTheta;
	    double newY = x * sinTheta + y * cosTheta;
	    
	    return new Vector(newX, newY);
	}

	
	public Vector crossProduct(Vector other) {
		double newX = this.y * other.x - this.x * other.y;
        double newY = this.x * other.y - this.y * other.x;
		return new Vector(newX, newY);
	}

}
