import java.awt.Graphics;

public class Wall extends CollisionObj {
	private int rotation;
	private int pointx;
	private int pointy;
	private int width;
	private int height;

	public Wall(int x, int y, int width, int height,Graphics g) {
		
		super(x, y);
		pointx = x;
		pointy = y;
		Draw obstacle = new Draw(x,y,g,width,height);
		
		
	
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

}
