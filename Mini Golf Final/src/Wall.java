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
        this.width = 4; // Adjust the width based on wall orientation if needed
        this.theta = theta;
        this.length = length;
        createPoints();
    }
    
    public Wall(int x, int y,int width, int length, double theta) {
        super(x, y);
        super.rectHitBox = true;
        this.width = width; // Adjust the width based on wall orientation if needed
        this.theta = theta;
        this.length = length;
        createPoints();
    }
    
    private void createPoints(){
    	 points = new Point2D[] {new Point2D.Double(x, y), new Point2D.Double(x + length, y), new Point2D.Double(x, y+ width), new Point2D.Double(x + length, y + width)};
    }
    public void drawWall(Graphics2D g) {
        AffineTransform og = g.getTransform();
        g.rotate(Math.toRadians(theta), x, y);
        g.drawRect((int) x, (int) y, (int) length, (int) width);
        for(Point2D p: points) {
        g.getTransform().transform(p, p);
        }
        g.setTransform(og);
        
    }
}
