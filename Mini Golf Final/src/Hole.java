import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hole extends CollisionObj {
    BufferedImage holePic;
    BufferedImage flagPic;
    int radius = 8;
    int flagRadius = 40;
    double flagX;
    double flagY;
    boolean animationTriggered = false;
    boolean isVisible = true;

    // constructor to initialize the hole with given properties
    public Hole(double x, double y) {
        super(x, y);
        flagX = x;
        flagY = y;
        super.circleHitBox = true; // set the hitbox type to circular

        // load the hole image
        try {
            holePic = ImageIO.read(new File("hole.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // load the flag image
        try {
            flagPic = ImageIO.read(new File("flag.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method to draw the hole and flag on the screen
    public void draw(Graphics g) {
        // draw the hole image
        g.drawImage(holePic, (int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius), null);

        // draw the flag and flag circle if visible
        if (isVisible) {
            g.setColor(Color.white);
            g.drawOval((int)(x - flagRadius), (int)(y - flagRadius), 2 * flagRadius, 2 * flagRadius); // draw the flag circle
            g.drawImage(flagPic, (int)flagX, (int)flagY - 40, 20, 40, null); // draw the flag image
        }
    }
}
