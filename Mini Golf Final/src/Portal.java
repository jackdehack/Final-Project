import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Portal extends CollisionObj {
	private BufferedImage portalImage;
	private Portal linkedPortal;
	private boolean isEntry;

	public Portal(double x, double y, String imagePath, boolean isEntry) {
		super(x, y);
		this.isEntry = isEntry;
		this.circleHitBox = true;
		try {
			portalImage = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setLinkedPortal(Portal linkedPortal) {
		this.linkedPortal = linkedPortal;
	}

	public Portal getLinkedPortal() {
		return linkedPortal;
	}

	public boolean isEntry() {
		return isEntry;
	}

	public void draw(Graphics2D g) {
		g.drawImage(portalImage, (int) x - portalImage.getWidth() / 2, (int) y - portalImage.getHeight() / 2, null);
	}
}