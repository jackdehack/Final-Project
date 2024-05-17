import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Wall extends CollisionObj{
	public double width;
	public double length;
	public Vector orientation; 
	public double theta;
	
	

	public Wall(int x, int y, int length, double theta) {
		super(x, y);
		super.rectHitBox = true;
		this.width = 4;
		this.theta = theta;
		this.length = length;
		orientation = new Vector(1,0);
		orientation = orientation.rotate(theta);
	}
	
	
	
	public void drawWall(Graphics2D g) {
		AffineTransform og = g.getTransform();
		g.rotate(Math.toRadians(theta), x, y);
		g.drawRect((int)x, (int)y, (int)length, (int)width);
		g.setTransform(og);
	}





}


