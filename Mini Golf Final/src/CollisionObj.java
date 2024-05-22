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

    
    protected boolean checkCollides(CollisionObj other) {
        if (other.rectHitBox) {
            Wall wall = (Wall) other;
            double nearestX = x;
            double nearestY = y;

            // Calculate the nearest point on the wall to the ball
            if (wall.theta == 0) { // Horizontal wall
                if (x < wall.x) {
                    nearestX = wall.x;
                } else if (x > wall.x + wall.length) {
                    nearestX = wall.x + wall.length;
                }

                nearestY = wall.y;
            } else if (wall.theta == 90) { // Vertical wall
                nearestX = wall.x;

                if (y < wall.y) {
                    nearestY = wall.y;
                } else if (y > wall.y + wall.length) {
                    nearestY = wall.y + wall.length;
                }
            }

            double deltaX = this.x - nearestX;
            double deltaY = this.y - nearestY;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            if (distance < ((Ball) this).radius) {
                Vector normal = new Vector(deltaX, deltaY).normalize();
                Vector velocity = ((Ball) this).ballv;
                Vector reflectedVelocity = velocity.subtract(normal.multiply(2 * velocity.dotProduct(normal)));

                ((Ball) this).ballv = reflectedVelocity;

                double overlap = ((Ball) this).radius - distance;
                this.x += normal.x * overlap;
                this.y += normal.y * overlap;

                return true;
            }
        }

        return false;
    }


}
