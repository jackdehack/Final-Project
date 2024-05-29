import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class CollisionObj {
	//hitboxes initially set to false
    protected boolean circleHitBox = false;
    protected boolean rectHitBox = false;

    // variables to store the x and y position of the object
    protected double x;
    protected double y;

    // constructor to initialize the object with x and y position
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

    // calculate the closest distance from a point to a line segment
    private Vector lineProjDist(double x1, double y1, double x2, double y2) {

        double ballX = this.x;
        double ballY = this.y;

        // vector from first point of line to object's center
        Vector linetoBall = new Vector((ballX - x1), (ballY - y1));

        // vector representing line segment
        Vector lineVector = new Vector((x2 - x1), (y2 - y1));

        // calculate length of line segment squared
        double lineLengthSquared = lineVector.x * lineVector.x + lineVector.y * lineVector.y;

        // calculate projection of object onto line segment
        double projection = linetoBall.dotProduct(lineVector) / lineLengthSquared;

        // restrict projection between 0 and 1
        projection = Math.max(0, Math.min(1, projection));

        // calculate nearest point on line segment to object
        double nearestX = x1 + projection * lineVector.x;
        double nearestY = y1 + projection * lineVector.y;

        // return vector from nearest point to object's center
        return new Vector(ballX - nearestX, ballY - nearestY);
    }

    // check if the object collides with another object
    protected boolean checkCollides(CollisionObj other) {
        // check collision with rectangular hitboxes
        if (other.rectHitBox) {
            // cast this object to a Ball and get its radius
            double radius = ((Ball) this).radius;
            Wall wall = (Wall) other;

            // loop through all points of the rectangle (wall)
            for (int i = 0; i < wall.points.length; i++) {
                // get current and next point of rectangle
                Point2D p1 = wall.points[i];
                Point2D p2 = wall.points[(i + 1) % wall.points.length]; // wrap around for the last line

                // calculate distance from ball to rectangle side
                Vector dist = lineProjDist(p1.getX(), p1.getY(), p2.getX(), p2.getY());
                double distance = Math.sqrt(dist.x * dist.x + dist.y * dist.y);

                // if distance is less than radius, there is a collision
                if (distance < radius) {
                    // calculate normal vector
                    Vector normal = new Vector(dist.x, dist.y).normalize();
                    Vector velocity = ((Ball) this).ballv;

                    // calculate reflected velocity
                    Vector reflectedVelocity = velocity.subtract(normal.multiply(2 * velocity.dotProduct(normal)));

                    // update ball's velocity
                    ((Ball) this).ballv = reflectedVelocity;

                    // calculate overlap distance and adjust ball's position
                    double overlap = radius - distance;
                    this.x += normal.x * overlap;
                    this.y += normal.y * overlap;

                    // return true indicating collision occurred
                    return true;
                }
            }
        }

        // check collision with circular hitboxes
        if (other.circleHitBox) {
            if (other instanceof Hole) {
                // calculate distance between ball and hole
                Vector dist = new Vector(x - other.x, y - other.y);
                double distance = Math.sqrt(dist.x * dist.x + dist.y * dist.y);

                // if distance is less than ball's radius = collision
                if (distance < ((Ball) this).radius) {
                    // if the ball's velocity is slow enough falls into hole
                    if (((Ball) this).ballv.magnitude() <= 3) {
                        ((Ball) this).ballv = new Vector(0, 0);
                        ((Ball) this).x = ((Hole) other).x;
                        ((Ball) this).y = ((Hole) other).y;
                        ((Ball) this).isIn = true;
                        return true;
                    }
                }

                // if distance is less than hole's flag radius = trigger animation
                if (distance < ((Hole) other).flagRadius) {
                    ((Hole) other).animationTriggered = true;
                }
            }

            if (other instanceof BouncingObstacle) {
                // get radius of ball and obstacle
                double ballRadius = ((Ball) this).radius;
                BouncingObstacle obstacle = (BouncingObstacle) other;
                double obstacleRadius = obstacle.radius;

                // calculate distance between ball and obstacle
                Vector distance = new Vector(this.x - obstacle.x, this.y - obstacle.y);

                // if distance is less than sum of their radii = collision
                if (distance.magnitude() <= ballRadius + obstacleRadius) {
                    // calculate normal vector
                    Vector normal = distance.normalize();
                    Vector velocity = ((Ball) this).ballv;

                    // calculate reflected velocity with added constant force
                    Vector reflectedVelocity = velocity.subtract(normal.multiply(2 * velocity.dotProduct(normal)));
                    Vector constant = normal.multiply(3);
                    reflectedVelocity = reflectedVelocity.add(constant);

                    // update ball's velocity
                    ((Ball) this).ballv = reflectedVelocity;

                    // calculate overlap distance and adjust ball's position
                    double overlap = ballRadius + obstacleRadius - distance.magnitude();
                    this.x += normal.x * overlap;
                    this.y += normal.y * overlap;

                    // return true indicating collision occurred
                    return true;
                }
            }

            if (other instanceof Portal) {
                // get entry and exit portals
                Portal entryPortal = (Portal) other;
                Portal exitPortal = entryPortal.getLinkedPortal();

                // calculate distance between ball and entry portal
                Vector distance = new Vector(entryPortal.x - x, entryPortal.y - y);

                // if distance is less than entry portal's radius, teleport the ball
                if (distance.magnitude() <= entryPortal.radius) {
                    if (exitPortal != null) {
                        // calculate displacement for ball's new position
                        Vector displacement = ((Ball) this).ballv.normalize().multiply(2 * distance.magnitude());

                        // update ball's position to exit portal
                        this.x = exitPortal.x + displacement.x;
                        this.y = exitPortal.y + displacement.y;

                        // return true indicating collision occurred
                        return true;
                    }
                }
            }
        }

        // return false if no collision occurred
        return false;
    }
}
