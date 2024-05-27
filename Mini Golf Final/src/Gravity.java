import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Gravity extends CollisionObj {
    private double width;
    private double height;
    private double gravityEffect;

    public Gravity(double x, double y, double width, double height, double gravityEffect) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.gravityEffect = gravityEffect;
        this.rectHitBox = true;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(255, 165, 0, 128)); // Semi-transparent orange
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    public boolean contains(Point p) {
        return p.x >= x && p.x <= x + width && p.y >= y && p.y <= y + height;
    }

    public boolean ballInGravityField(Ball ball) {
        return ball.x >= x && ball.x <= x + width && ball.y >= y && ball.y <= y + height;
    }

    public void applyGravityEffect(Ball ball) {
        if (ballInGravityField(ball)) {
            ball.ballv.x *= gravityEffect;
            ball.ballv.y *= gravityEffect;
        }
    }
}
