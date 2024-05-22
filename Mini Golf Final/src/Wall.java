import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Wall extends CollisionObj {
    public double width;
    public double length;
    public double theta;

    public Wall(int x, int y, int length, double theta) {
        super(x, y);
        super.rectHitBox = true;
        this.width = 4; // Adjust the width based on wall orientation if needed
        this.theta = theta;
        this.length = length;
    }
    
    public Wall(int x, int y,int width, int length, double theta) {
        super(x, y);
        super.rectHitBox = true;
        this.width = width; // Adjust the width based on wall orientation if needed
        this.theta = theta;
        this.length = length;
    }

    public void drawWall(Graphics2D g) {
        AffineTransform og = g.getTransform();
        g.rotate(Math.toRadians(theta), x, y);
        g.drawRect((int) x, (int) y, (int) length, (int) width);
        g.setTransform(og);
    }
}
