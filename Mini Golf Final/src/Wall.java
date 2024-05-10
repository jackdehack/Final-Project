import java.awt.Graphics;

public class Wall extends CollisionObj{
	private int rotation;
	public int width;
	public int height;

	public Wall(int x, int y,int width, int length, Graphics g) {
		super(x, y);
		super.rectHitBox = true;
		Draw obstacle = new Draw(x,y,width,length,g);
		
	
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

}
