import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.geom.AffineTransform; 
import java.awt.geom.Point2D; 

public class Wall extends CollisionObj { // wall class extends collisionobj class
    public double width; 
    public double length; 
    public double theta; 
    public Point2D[] points; 

    // constructor with length and angle
    public Wall(int x, int y, int length, double theta) {
        super(x, y); 
        super.rectHitBox = true; // set the hitbox type to rectangle
        this.width = 4; // default width of the wall
        this.theta = theta; // set the rotation angle
        this.length = length; // set the length
        createPoints(); // create the corner points
    }

    public Wall(int x, int y, int width, int length, double theta) {
        super(x, y); 
        super.rectHitBox = true; 
        this.width = width; // set the width
        this.theta = theta; // set the rotation angle
        this.length = length; // set the length
        createPoints(); // create the corner points
    }

    // create the corner points of the wall
    private void createPoints() {
        points = new Point2D[4]; 
        points[0] = new Point2D.Double(x, y); 
        points[1] = new Point2D.Double(x + length, y);
        points[2] = new Point2D.Double(x + length, y + width); 
        points[3] = new Point2D.Double(x, y + width); 

        AffineTransform transform = new AffineTransform(); 
        transform.rotate(Math.toRadians(theta), x, y); // rotate around the top-left point
        for (int i = 0; i < points.length; i++) {
            transform.transform(points[i], points[i]); // apply the rotation to each point
        }
    }

    // draw the wall on the screen
    public void drawWall(Graphics2D g) {
        AffineTransform og = g.getTransform(); 
        g.rotate(Math.toRadians(theta), x, y); 
        g.setColor(Color.black); 
        g.fillRect((int) x, (int) y, (int) length, (int) width); 
        g.setTransform(og); // reset the graphics context to the original transform
    }
}
