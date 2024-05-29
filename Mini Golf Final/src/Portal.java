import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Portal extends CollisionObj {
    double radius = 25; 
    BufferedImage portalImage; 
    Portal linkedPortal; 
    boolean invisible = false; 

    // constructor for the portal class
    public Portal(double x, double y, boolean isBlue) {
        super(x, y);
        this.circleHitBox = true; // make the portal have a circular hitbox
        if (isBlue) {
            try {
                portalImage = ImageIO.read(new File("bluePortal.png")); 
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        } else {
            try {
                portalImage = ImageIO.read(new File("orangePortal.png")); 
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        }
    }

    public void setLinkedPortal(Portal linkedPortal) {
        this.linkedPortal = linkedPortal;
    }

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
