import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball extends CollisionObj {
    private BufferedImage ballPic;
    public double mass;
    public Vector ballv;
    public double radius = 10;

    public Ball(double x, double y, Vector v) {
        super(x, y);
        ballv = v;
        try {
            ballPic = ImageIO.read(new File("golfballpic.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(ballPic, (int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius), null);
    }
}
