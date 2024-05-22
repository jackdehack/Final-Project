import java.awt.geom.AffineTransform;

public class CollisionObj {
    protected boolean circleHitBox = false;
    protected boolean rectHitBox = false;
    protected double x;
    protected double y;

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

    
   private Vector getLineDistance(double x, double y, double length, CollisionObj other) {
	   
	   Wall wall = (Wall) other;
       double ballX = this.x;
       double ballY = this.y;
      

       // Wall's start and end points
       double wallStartX = x;
       double wallStartY = y;
       double wallEndX = wall.x + length * Math.cos(Math.toRadians(wall.theta));
       double wallEndY = wall.y + length * Math.sin(Math.toRadians(wall.theta));

       // Vector from wall start to ball position
       double wallToBallX = ballX - wallStartX;
       double wallToBallY = ballY - wallStartY;

       // Wall vector
       double wallVectorX = wallEndX - wallStartX;
       double wallVectorY = wallEndY - wallStartY;
       double wallLengthSquared = wallVectorX * wallVectorX + wallVectorY * wallVectorY;

       // Project ball position onto wall vector to find the nearest point on the wall segment
       double projection = (wallToBallX * wallVectorX + wallToBallY * wallVectorY) / wallLengthSquared;
       projection = Math.max(0, Math.min(1, projection)); // Clamp projection between 0 and 1

       // Calculate the nearest point on the wall
       double nearestX = wallStartX + projection * wallVectorX;
       double nearestY = wallStartY + projection * wallVectorY;

       double deltaX = ballX - nearestX;
       double deltaY = ballY - nearestY;
	   return new Vector(deltaX, deltaY);  
   }
    
    
    
    
    
    
    protected boolean checkCollides(CollisionObj other) {
        if (other.rectHitBox) {
        	 double radius = ((Ball) this).radius;
        	 Wall wall = (Wall) other;
        	 //Vector dist1 = getLineDistance(wall.x, wall.y, wall.length, wall);
        	
        	 //idk fix this ig
        	 Vector dist1 = getLineDistance(wall.points[2].getX(), wall.points[2].getY(), wall.length, wall);
        	 //Vector dist3 = getLineDistance();
        	// Vector dist4 = getLineDistance();
        	 
        	 
        	 double distance = Math.sqrt(dist1.x * dist1.x + dist1.y * dist1.y);
        	 double deltaX = dist1.x;
        	 double deltaY = dist1.y;

            if (distance < radius) {
                Vector normal = new Vector(deltaX, deltaY).normalize();
                Vector velocity = ((Ball) this).ballv;
                Vector reflectedVelocity = velocity.subtract(normal.multiply(2 * velocity.dotProduct(normal)));

                ((Ball) this).ballv = reflectedVelocity;

                double overlap = radius - distance;
                this.x += normal.x * overlap;
                this.y += normal.y * overlap;

                return true;
            }
        }

        return false;
    }



}
