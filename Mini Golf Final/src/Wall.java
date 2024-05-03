
public class Wall extends CollisionObj{
	private int rotation;

	public Wall(double x, double y) {
		super(x, y);
	
	}

	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

}
