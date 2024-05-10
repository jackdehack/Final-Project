public class CollisionObj {
	protected boolean circleHitBox = false;
	protected boolean rectHitBox = false;
	protected double x;
	protected double y;
	protected double scaleFactor;

	public CollisionObj(double x, double y) {
		this.x = x;
		this.y = y;
	}

	protected double getX() {
		return x;
	}

	protected void setX(double x) {
		this.x = x;
	}

	protected double getY() {
		return y;
	}

	protected void setY(double y) {
		this.y = y;
	}

	protected void checkCollides(CollisionObj other) {

		/*
		 * my thinking here is eventually we will put all collision objects in an array
		 * where we will check if the ball collides with any of the objectsS
		 */

		if (other.rectHitBox) {
			double nearestX;
			double nearestY;
			if (x < ((Wall) (other)).x) {
				nearestX = other.x;
			} else if (x > ((Wall) (other)).x + ((Wall) other).width) {
				nearestX = ((Wall) (other)).x + ((Wall) other).width;
			} else {
				nearestX = x;
			}

			if (y < ((Wall) (other)).y) {
				nearestY = other.y;
			} else if (y > ((Wall) (other)).y + ((Wall) other).height) {
				nearestY = ((Wall) (other)).y + ((Wall) other).height;
			} else {
				nearestY = y;
			}
			
			
			if(Math.sqrt((nearestX - x)*(nearestX - x) + (nearestY-y)*(nearestY-y)) <= ((Ball)this).radius) {
				//collision logic
			}

			
		}
		
		if(other.circleHitBox) {
			if()
		}
		
		
		
		
		
	}
	
	
}
