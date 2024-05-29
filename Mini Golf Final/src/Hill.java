import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Hill {
    int x;
    int y;
    int width;
    int length;
    double theta;
    Point2D[] points;
    boolean isUpHill;
    BufferedImage hillPic;

    public Hill(int x, int y, int width, int length, double theta, boolean isUpHill) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.length = length;
        this.theta = theta;
        this.isUpHill = isUpHill;

        // load the appropriate hill image based on whether it's uphill or downhill
        if (isUpHill) {
            try {
                hillPic = ImageIO.read(new File("UpHill.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                hillPic = ImageIO.read(new File("downHill.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // create the points for the hill's shape
        createPoints();
    }

    // method to create the four corner points of the hill
    private void createPoints() {
        points = new Point2D[4];
        points[0] = new Point2D.Double(x, y);
        points[1] = new Point2D.Double(x + width, y);
        points[2] = new Point2D.Double(x + width, y + length);
        points[3] = new Point2D.Double(x, y + length);

        // rotate the points based on the hill's angle
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(theta), x, y);
        for (int i = 0; i < points.length; i++) {
            transform.transform(points[i], points[i]);
        }
    }

    // method to check if the ball is inside the hill and apply gravity if it is
    public void checkApplyGravity(Ball ball) {
        if (isInside(ball)) {
            double gravityEffect;
            if (isUpHill) {
                gravityEffect = 0.11;
            } else {
                gravityEffect = -0.11;
            }

            // apply the calculated gravity effect to the ball
            applyHillGravity(ball, gravityEffect);
        }
    }

    // check if the ball is inside the hill's area
    private boolean isInside(Ball ball) {
        Point2D ballPosition = new Point2D.Double(ball.x, ball.y);

        // rotate ball's position back to align with the hill's axis
        AffineTransform transform = new AffineTransform();
        transform.rotate(-Math.toRadians(theta), x, y);

        Point2D transformedBall = transform.transform(ballPosition, null);
        Point2D transformedTopLeft = transform.transform(points[0], null);
        Point2D transformedBottomRight = transform.transform(points[2], null);

        // check if the ball's position is within the hill's bounds
        return (transformedBall.getX() >= transformedTopLeft.getX() &&
                transformedBall.getX() <= transformedBottomRight.getX() &&
                transformedBall.getY() >= transformedTopLeft.getY() &&
                transformedBall.getY() <= transformedBottomRight.getY());
    }

    // apply gravity effect to the ball when on the hill
    private void applyHillGravity(Ball ball, double gravityEffect) {
        // calculate the direction of gravity on the hill
        Vector hillDirection = new Vector(Math.cos(Math.toRadians(theta + 90)), Math.sin(Math.toRadians(theta + 90)));
        Vector gravityVector = hillDirection.multiply(gravityEffect);
        
        // add the gravity vector to the ball's velocity
        ball.ballv = ball.ballv.add(gravityVector);
    }

    // method to draw the hill on the screen
    public void drawHill(Graphics2D g) {
        AffineTransform og = g.getTransform();
        
        // rotate the graphics context to draw the hill at the correct angle
        g.rotate(Math.toRadians(theta), x, y);
        g.setColor(Color.black);
        
        // draw the hill image
        g.drawImage(hillPic, x, y, width, length, null);
        
        // restore the original transform
        g.setTransform(og);
    }
}
