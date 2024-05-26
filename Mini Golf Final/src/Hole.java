import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hole extends CollisionObj{
	
	BufferedImage holePic;
	BufferedImage flagPic;
	int radius = 8;
	int flagRadius = 40;
	double flagX;
	double flagY;
	boolean animationTriggered = false;
	boolean isVisible = true;

	

	public Hole(double x, double y) {
		super(x,y);
			flagX = x;
			flagY = y;
		super.circleHitBox = true;
        try {
            holePic = ImageIO.read(new File("hole.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            flagPic = ImageIO.read(new File("flag.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	
	public void draw(Graphics g) {
		g.drawImage(holePic, (int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius), null);
		if(isVisible) {
			g.setColor(Color.white);
			g.drawOval((int)(x - flagRadius), (int)(y - flagRadius), 2*flagRadius, 2*flagRadius);
			g.drawImage(flagPic, (int)flagX, (int)flagY-40, 20, 40, null);
		}
		
		
	}
	
	
	
}
