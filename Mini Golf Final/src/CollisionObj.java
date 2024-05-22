public class CollisionObj {
	protected boolean circleHitBox = false;
	protected boolean rectHitBox = false;
	protected double x;
	protected double y;

	public CollisionObj(double x, double y) {
		this.x = x;
		this.y = y;
	}
	//jhkhkhjkh
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

	protected boolean checkCollides(CollisionObj other) {

		/*
		 * my thinking here is eventually we will put all collision objects in an array
		 * where we will check if the ball collides with any of the objectsS
		 */

		if (other.rectHitBox) {
			double nearestX;
			double nearestY;

			// need to handle for rotated walls too!!!! - figure out!!
			if (x < ((Wall) (other)).x) {
				nearestX = other.x;
			} else if (x > ((Wall) (other)).x + ((Wall) other).length) {
				nearestX = ((Wall) (other)).x + ((Wall) other).length;
			} else {
				nearestX = x;
			}

			if (y < ((Wall) (other)).y) {
				nearestY = other.y;
			} else if (y > ((Wall) (other)).y + ((Wall) other).width) {
				nearestY = ((Wall) (other)).y + ((Wall) other).width;
			} else {
				nearestY = y;
			}
			System.out.println(this.x);
			System.out.println(this.y);
			System.out.println("----------");
			System.out.println(nearestX);
			System.out.println(nearestY);
			
			if(other.rectHitBox) {
			if(Math.sqrt((nearestX - x)*(nearestX - x) + (nearestY-y)*(nearestY-y)) <= ((Ball)this).radius) {
				
				Vector dist = new Vector(((Ball)this).ballv.x - nearestX, ((Ball)this).ballv.y - nearestY);
				Vector dnormal = new Vector(- dist.y, dist.x);
				double normal_angle = Math.atan2(dnormal.y, dnormal.x);
				double incoming_angle = Math.atan2(((Ball)this).ballv.y, ((Ball)this).ballv.x);
				double theta = normal_angle - incoming_angle;
				((Ball)this).ballv = ((Ball)this).ballv.rotate(-2*theta);
				return true;
			}
			}
			
		
		
		if(other.circleHitBox) {
			if((other.x - x)*(other.x - x) + (other.y-y)*(other.y-y) <= ((Ball)this).radius){
				
				Vector dist = new Vector(((Ball)this).ballv.x - other.x, ((Ball)this).ballv.y - other.y);
				Vector dnormal = new Vector(- dist.y, dist.x);
				double normal_angle = Math.atan2(dnormal.y, dnormal.x);
				double incoming_angle = Math.atan2(((Ball)this).ballv.y, ((Ball)this).ballv.x);
				double theta = normal_angle - incoming_angle;
				((Ball)this).ballv = ((Ball)this).ballv.rotate(2*theta);
				return true;
				
			}
		}
		
		
		}
		
		return false;
	}
	
	
}
