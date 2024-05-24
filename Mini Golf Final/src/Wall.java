import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Wall extends CollisionObj {
    public double width;
    public double length;
    public double theta;
    public Point2D[] points;

    public Wall(int x, int y, int length, double theta) {
        super(x, y);
        super.rectHitBox = true;
        this.width = 4; 
        this.theta = theta;
        this.length = length;
        createPoints();
    }
    
    public Wall(int x, int y, int width, int length, double theta) {
        super(x, y);
        super.rectHitBox = true;
        this.width = width; 
        this.theta = theta;
        this.length = length;
        createPoints();
    }
    
    private void createPoints() {
        points = new Point2D[4];
        points[0] = new Point2D.Double(x, y);
        points[1] = new Point2D.Double(x + length, y);
        points[2] = new Point2D.Double(x + length, y + width);
        points[3] = new Point2D.Double(x, y + width);

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(theta), x, y);
        for (int i = 0; i < points.length; i++) {
            transform.transform(points[i], points[i]);
        }
    }

    public void drawWall(Graphics2D g) {
        AffineTransform og = g.getTransform();
        g.rotate(Math.toRadians(theta), x, y);
        g.drawRect((int) x, (int) y, (int) length, (int) width);
        g.setTransform(og);
    }
}