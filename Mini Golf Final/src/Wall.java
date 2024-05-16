import java.awt.Graphics;
import java.awt.Graphics2D;

public class Wall extends CollisionObj{
	public double width;
	public double length;
	public Vector orientation; 
	public double theta;
	
	//public Wall()

	public Wall(int x, int y,int width, int length, double theta) {
		super(x, y);
		super.rectHitBox = true;
		this.width = width;
		this.theta = theta;
		this.length = length;
		orientation = new Vector(1,0);
		orientation = orientation.rotate(theta);
	}
	
	
	public void drawWall(Graphics2D g) {
		//g.rotate(Math.toRadians(theta), x, y);
		g.drawRect((int)x, (int)y, (int)width, (int)length);
	}
	
	
//jhkhkhjkh


}
