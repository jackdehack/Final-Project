import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball {
	private double xVector;
	private double yVector;
	private BufferedImage ballPic; //lol
	private double radius;
	private double x;
	private double y;
	private double mass;
	private double scaleFactor = 1;
	
	
	public Ball(double x, double y, double xVector, double yVector, double scaleFactor) {
		this.x = x;
		this.y = y;
		this.xVector = xVector;
		this.yVector = yVector;
		this.scaleFactor = scaleFactor;
		this.radius = 46 * scaleFactor;
		try {
			ballPic = ImageIO.read(new File("golfballpic"));
		} catch (IOException e) {
		}
	}
	
	
	public void draw(Graphics g) {
		
	}
	
	
	
	
	
	
	
	
}
