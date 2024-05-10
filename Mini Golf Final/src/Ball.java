import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//hello

//euler added thissssssssssssssss

public class Ball extends CollisionObj{
	private BufferedImage ballPic; //lol
	public double mass;
	public Vector ballv;
	public double radius;
	
	
	public Ball(double x, double y) {
		super(x, y);
		ballv.x = x;
		ballv.y = y;
		try {
			ballPic = ImageIO.read(new File("golfballpic"));
		} catch (IOException e) {
		}
	}
	



	public void draw(Graphics g) {
		g.drawImage(ballPic, (int)(x-radius), (int)(y-radius), (int)(92 * scaleFactor), (int)(92*scaleFactor), null, null);
	}
	
	
	
}
