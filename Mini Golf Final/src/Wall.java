
public class Wall extends CollisionObj{
	private int rotation;
	public int width;
	public int height;

	public Wall(double x, double y) {
		super(x, y);
		super.rectHitBox = true;
	
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

}
