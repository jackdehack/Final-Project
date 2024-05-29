import java.awt.Color; // imports color class for setting colors
import java.awt.Graphics; // imports graphics class for drawing
import java.awt.Graphics2D; // imports graphics2d class for more drawing features
import java.awt.geom.AffineTransform; // imports affine transform class for rotations
import java.awt.geom.Point2D; // imports point2d class for handling 2d points

public class Wall extends CollisionObj { // wall class extends collisionobj class
    public double width; // width of the wall
    public double length; // length of the wall
    public double theta; // angle of rotation for the wall
    public Point2D[] points; // array to store the four corners of the wall

    // constructor with length and angle
    public Wall(int x, int y, int length, double theta) {
        super(x, y); // call the parent class constructor
        super.rectHitBox = true; // set the hitbox type to rectangle
        this.width = 4; // default width of the wall
        this.theta = theta; // set the rotation angle
        this.length = length; // set the length
        createPoints(); // create the corner points
    }

    // constructor with width, length, and angle
    public Wall(int x, int y, int width, int length, double theta) {
        super(x, y); // call the parent class constructor
        super.rectHitBox = true; // set the hitbox type to rectangle
        this.width = width; // set the width
        this.theta = theta; // set the rotation angle
        this.length = length; // set the length
        createPoints(); // create the corner points
    }

    // method to create the corner points of the wall
    private void createPoints() {
        points = new Point2D[4]; // initialize the array for four points
        points[0] = new Point2D.Double(x, y); // top-left point
        points[1] = new Point2D.Double(x + length, y); // top-right point
        points[2] = new Point2D.Double(x + length, y + width); // bottom-right point
        points[3] = new Point2D.Double(x, y + width); // bottom-left point

        AffineTransform transform = new AffineTransform(); // create a new affine transform
        transform.rotate(Math.toRadians(theta), x, y); // rotate around the top-left point
        for (int i = 0; i < points.length; i++) {
            transform.transform(points[i], points[i]); // apply the rotation to each point
        }
    }

    // method to draw the wall on the screen
    public void drawWall(Graphics2D g) {
        AffineTransform og = g.getTransform(); // save the original transform
        g.rotate(Math.toRadians(theta), x, y); // rotate the graphics context
        g.setColor(Color.black); // set the color to black
        g.fillRect((int) x, (int) y, (int) length, (int) width); // draw the wall as a filled rectangle
        g.setTransform(og); // reset the graphics context to the original transform
    }
}
