import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//hello

//euler added thissssssssssssssss
//jhkhkhjkh

//euler push test #2

public class Ball extends CollisionObj{
	private BufferedImage ballPic; //lol
	public double mass;
	public Vector ballv;
	public double radius = 20;
	
	
	public Ball(double x, double y, Vector v) {
		super(x, y);
		ballv = v;
		try {
			ballPic = ImageIO.read(new File("golfballpic.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	



	public void draw(Graphics g) {
		g.drawImage(ballPic, (int)(x-radius), (int)(y-radius), (int)(radius), (int)(radius), null, null);
	}
	
	
	
}
