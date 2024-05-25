import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hole extends CollisionObj{
	
	BufferedImage holePic;
	int x;
	int y;
	int radius = 16;

	public Hole(int x, int y) {
		super(x,y);
        try {
            holePic = ImageIO.read(new File("hole.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	public void draw(Graphics g) {
		 g.drawImage(holePic, x, y, (int)(2 * radius), (int)(2 * radius), null);
	}
	
	
	
}
