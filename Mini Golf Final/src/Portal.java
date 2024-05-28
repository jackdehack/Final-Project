import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Portal extends CollisionObj {
	double radius = 25;
	private BufferedImage portalImage;
	private Portal linkedPortal;

	public Portal(double x, double y, boolean isBlue) {
		super(x, y);
		this.circleHitBox = true;
		if (isBlue) {
			try {
				portalImage = ImageIO.read(new File("bluePortal.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				portalImage = ImageIO.read(new File("orangePortal.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
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

	public void draw(Graphics2D g) {
		g.drawImage(portalImage, (int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius), null);
	}
}
