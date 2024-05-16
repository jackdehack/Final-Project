import java.awt.Graphics;

public class Wall extends CollisionObj{
	public double width;
	public double height;
	public Vector orientation; 

	public Wall(int x, int y,int width, int length, Graphics g) {
		super(x, y);
		super.rectHitBox = true;
		g.drawRect(x, y, width,  length);
		
		
	
	}
	
	
	public void drawWall(Graphics g) {
		g.drawRect((int)x, (int)y, (int)width, (int)height);
	}
	
	



}
