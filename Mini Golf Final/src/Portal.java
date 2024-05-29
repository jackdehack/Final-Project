import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Portal extends CollisionObj {
    double radius = 25; // the size of the portal
    BufferedImage portalImage; // image of the portal
    Portal linkedPortal; // the other portal this one is connected to
    boolean invisible = false; // if true, the portal will not be visible

    // this is the constructor for the portal class
    // it sets the position and color of the portal
    public Portal(double x, double y, boolean isBlue) {
        super(x, y);
        this.circleHitBox = true; // make the portal have a circular hitbox
        if (isBlue) {
            try {
                portalImage = ImageIO.read(new File("bluePortal.png")); // load blue portal image
            } catch (IOException e) {
                e.printStackTrace(); // print an error if the image can't be loaded
            }
        } else {
            try {
                portalImage = ImageIO.read(new File("orangePortal.png")); // load orange portal image
            } catch (IOException e) {
                e.printStackTrace(); // print an error if the image can't be loaded
            }
        }
    }

    // set the linked portal
    public void setLinkedPortal(Portal linkedPortal) {
        this.linkedPortal = linkedPortal;
    }

    // get the linked portal
    public Portal getLinkedPortal() {
        return linkedPortal;
    }

    // draw the portal on the screen
    public void draw(Graphics2D g) {
        if (!invisible) { // only draw if the portal is not invisible
            g.drawImage(portalImage, (int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius),
                    null); // draw the portal image
        }
    }
}
