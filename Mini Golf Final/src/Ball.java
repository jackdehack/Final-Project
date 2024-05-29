import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball extends CollisionObj {

    boolean isIn = false;
    private BufferedImage ballPic;
    public double mass;
    
    // velocity vector of ball
    public Vector ballv;
    public double radius = 8;

    // ball constructor - position and vector
    public Ball(double x, double y, Vector v) {
        super(x, y);
        // set the velocity of the ball
        ballv = v;
        try {
            ballPic = ImageIO.read(new File("golfballpic.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // draw the ball
    public void draw(Graphics g) {
        // draw the ball image at its position, scaling it to the radius size
        g.drawImage(ballPic, (int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius), null);
    }

    // method to check if a point is inside the ball
    public boolean contains(Point p) {
        // calculate the distance between the point and the center of the ball
        double dx = p.x - x;
        double dy = p.y - y;
        // check if the distance is less than or equal to the radius of the ball
        return (dx * dx + dy * dy) <= (radius * radius);
    }
}
