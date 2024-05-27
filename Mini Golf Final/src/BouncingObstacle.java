import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class BouncingObstacle extends CollisionObj {
    private double radius;

    public BouncingObstacle(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
        this.circleHitBox = true;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
    }

    public boolean contains(Point p) {
        double dx = p.x - x;
        double dy = p.y - y;
        return (dx * dx + dy * dy) <= (radius * radius);
    }
}
