import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BouncingObstacle extends CollisionObj {
    // variable to store the radius of the obstacle, set to 25 units
    double radius = 25;
    
    // variable to store the image of the obstacle
    BufferedImage bouncyPic;

    // constructor to initialize the obstacle with a position
    public BouncingObstacle(double x, double y) {
        // call the constructor of the parent class with x and y
        super(x, y);
        
        try {
            // try to load the image of the obstacle
            bouncyPic = ImageIO.read(new File("Bouncy.png"));
        } catch (IOException e) {
            // print error if image cannot be loaded
            e.printStackTrace();
        }
        // set the hit box to be circular
        this.circleHitBox = true;
    }

    // method to draw the obstacle on the screen
    public void draw(Graphics g) {
        // set the color to blue
        g.setColor(Color.BLUE);
        // draw the obstacle image at its position, scaling it to the radius size
        g.drawImage(bouncyPic, (int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius), null);
    }
}
