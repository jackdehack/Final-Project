import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BouncingObstacle extends CollisionObj {
    // radius of obstacle = 25 
    double radius = 25;
    BufferedImage bouncyPic;

    // constructor to initialize the obstacle with a position
    public BouncingObstacle(double x, double y) {
        super(x, y);
        
        try {
            bouncyPic = ImageIO.read(new File("Bouncy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // set the hit box to be circular
        this.circleHitBox = true;
    }

    // draw the obstacle on the screen
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        // draw the obstacle image at its position, scaling it to the radius size
        g.drawImage(bouncyPic, (int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius), null);
    }
}
