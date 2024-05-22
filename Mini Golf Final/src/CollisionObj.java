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
            double ballX = this.x;
            double ballY = this.y;
            double radius = ((Ball) this).radius;

            // Wall's start and end points
            double wallStartX = wall.x;
            double wallStartY = wall.y;
            double wallEndX = wall.x + wall.length * Math.cos(Math.toRadians(wall.theta));
            double wallEndY = wall.y + wall.length * Math.sin(Math.toRadians(wall.theta));

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
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

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
