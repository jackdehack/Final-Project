import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball extends CollisionObj {
    // flag to check if the ball is in some specific state, initially false
    boolean isIn = false;
    
    // variable to store the image of the ball
    private BufferedImage ballPic;
    
    // variable to store the mass of the ball
    public double mass;
    
    // variable to store the velocity vector of the ball
    public Vector ballv;
    
    // variable to store the radius of the ball, set to 8 units
    public double radius = 8;

    // constructor to initialize the ball with a position and velocity
    public Ball(double x, double y, Vector v) {
        // call the constructor of the parent class with x and y
        super(x, y);
        // set the velocity of the ball
        ballv = v;
        try {
            // try to load the image of the ball
            ballPic = ImageIO.read(new File("golfballpic.png"));
        } catch (IOException e) {
            // print error if image cannot be loaded
            e.printStackTrace();
        }
    }

    // method to draw the ball on the screen
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
