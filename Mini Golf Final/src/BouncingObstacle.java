import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BouncingObstacle extends CollisionObj {
    double radius = 25;
    BufferedImage bouncyPic;

    public BouncingObstacle(double x, double y) {
        super(x, y);
        
        try {
            bouncyPic = ImageIO.read(new File("Bouncy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.circleHitBox = true;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawImage(bouncyPic, (int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius), null);
    }
    
}
