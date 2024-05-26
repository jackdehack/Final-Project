import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

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

    private Vector lineProjDist(double x1, double y1, double x2, double y2) {
        double ballX = this.x;
        double ballY = this.y;
        
        Vector linetoBall = new Vector((ballX - x1), (ballY - y1)); //vector from the first point on the rect to the ball's center
        Vector lineVector = new Vector((x2 - x1), (y2 - y1)); // vector of the rectangle side length
        
        double lineLengthSquared = lineVector.x * lineVector.x + lineVector.y * lineVector.y; //scalar for the dot product
        double projection = linetoBall.dotProduct(lineVector) / lineLengthSquared; //gives us a number of how alligned the ball line is with the rectangle wall
        
        projection = Math.max(0, Math.min(1, projection)); // Clamp projection between 0 and 1

        
        double nearestX = x1 + projection * lineVector.x;//nearest X point on redctangle side length
        double nearestY = y1 + projection * lineVector.y;//nearest Y point on redctangle side length

        return new Vector(ballX - nearestX, ballY - nearestY); //gives normal line
    }

    protected boolean checkCollides(CollisionObj other) {
        if (other.rectHitBox) {
            double radius = ((Ball) this).radius;
            Wall wall = (Wall) other;

            for (int i = 0; i < wall.points.length; i++) {
                Point2D p1 = wall.points[i];
                Point2D p2 = wall.points[(i + 1) % wall.points.length]; //goes around rectangle points, and wraps around for the last line
                Vector dist = lineProjDist(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                double distance = Math.sqrt(dist.x * dist.x + dist.y * dist.y);
                double deltaX = dist.x;
                double deltaY = dist.y;

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
        }
        
        
        if(other.circleHitBox) {
        	Vector dist = new Vector(x - other.x, y - other.y);
        	double distance = Math.sqrt(dist.x * dist.x + dist.y * dist.y);
        	
        	if (distance <= ((Ball) this).radius) {
        		if(((Ball)this).ballv.magnitude() <= 3) {
        		((Ball)this).ballv = new Vector(0, 0);
        		((Ball)this).isIn = true;
        		}
        	}
        	
        	if(distance < ((Hole)other).flagRadius) {
        		((Hole)other).animationTriggered = true;
        	}
        }

        return false;
    }
}