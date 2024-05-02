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
	
	protected boolean collides(CollisionObj other) {
		
		/*my thinking here is eventually we will put all collision objects in an
		 * array where we will check if the ball collides with any of the objectsS
		 */
		 
		if(this instanceof Ball && other instanceof Wall) {
			
		}
		
		return false;
	}
    
}
