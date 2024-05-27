import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
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

        createPoints();
    }

    private void createPoints() {
        points = new Point2D[4];
        points[0] = new Point2D.Double(x, y);
        points[1] = new Point2D.Double(x + length, y);
        points[2] = new Point2D.Double(x + length, y + width);
        points[3] = new Point2D.Double(x, y + width);

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(theta), x, y);
        for (int i = 0; i < points.length; i++) {
            transform.transform(points[i], points[i]);
        }
    }

    public void checkApplyGravity(Ball ball) {
        if (isInside(ball)) {
            double gravityEffect = isUpHill ? -0.1 : 0.1;
            applyHillGravity(ball, gravityEffect);
        }
    }

    private boolean isInside(Ball ball) {
        Point2D ballPosition = new Point2D.Double(ball.x, ball.y);

        AffineTransform transform = new AffineTransform();
        transform.rotate(-Math.toRadians(theta), x, y);

        Point2D transformedBall = transform.transform(ballPosition, null);
        Point2D transformedTopLeft = transform.transform(points[0], null);
        Point2D transformedBottomRight = transform.transform(points[2], null);

        return (transformedBall.getX() >= transformedTopLeft.getX() &&
                transformedBall.getX() <= transformedBottomRight.getX() &&
                transformedBall.getY() >= transformedTopLeft.getY() &&
                transformedBall.getY() <= transformedBottomRight.getY());
    }

    private void applyHillGravity(Ball ball, double gravityEffect) {
    	if(ball.ballv.magnitude() > 0) {
        Vector hillDirection = new Vector(Math.cos(Math.toRadians(theta + 90)), Math.sin(Math.toRadians(theta + 90)));
        Vector gravityVector = hillDirection.multiply(gravityEffect);
        ball.ballv.add(gravityVector);
    	}
    }

    public void drawHill(Graphics2D g) {
        AffineTransform og = g.getTransform();
        g.rotate(Math.toRadians(theta), x, y);
        g.setColor(Color.black);
        g.drawImage(hillPic, x, y, width, length, null);
        g.setTransform(og);
    }
}