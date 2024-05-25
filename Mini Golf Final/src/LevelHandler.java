import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LevelHandler extends JPanel {
    int width;
    int height;
    Ball b;
    ArrayList<Wall> walls;
    BufferedImage bg;
    Hole h;
    Point initialClick;
    ArrayList<Point> projectionPoints;
    double maxSpeed = 10.0; // Define maximum speed for the ball

    public LevelHandler(int width, int height) {
        try {
            bg = ImageIO.read(new File("Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.width = width;
        this.height = height;
        projectionPoints = new ArrayList<>();
        levelOne();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (b.contains(e.getPoint())) {
                    initialClick = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (initialClick != null) {
                    Point releasePoint = e.getPoint();
                    double dx = initialClick.x - releasePoint.x;
                    double dy = initialClick.y - releasePoint.y;
                    double velocityFactor = 0.1; // Adjust as needed for game balance
                    Vector newVelocity = new Vector(dx * velocityFactor, dy * velocityFactor);
                    b.ballv = capSpeed(newVelocity, maxSpeed);
                    initialClick = null;
                    projectionPoints.clear();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (initialClick != null) {
                    Point currentPoint = e.getPoint();
                    double dx = initialClick.x - currentPoint.x;
                    double dy = initialClick.y - currentPoint.y;
                    double velocityFactor = 0.1; // Same factor as in mouseReleased
                    Vector projectedVelocity = new Vector(dx * velocityFactor, dy * velocityFactor);
                    projectedVelocity = capSpeed(projectedVelocity, maxSpeed);
                    updateProjectionPoints(projectedVelocity);
                }
            }
        });
    }

    private Vector capSpeed(Vector velocity, double maxSpeed) {
        double speed = velocity.magnitude();
        if (speed > maxSpeed) {
            double scale = maxSpeed / speed;
            return new Vector(velocity.x * scale, velocity.y * scale);
        }
        return velocity;
    }

    private void updateProjectionPoints(Vector velocity) {
        projectionPoints.clear();
        double timeStep = 0.1;
        double dampingFactor = 0.98; // To simulate friction/air resistance
        double x = b.x;
        double y = b.y;
        double vx = velocity.x;
        double vy = velocity.y;

        for (int i = 0; i < 20; i++) {
            x += vx * timeStep;
            y += vy * timeStep;
            projectionPoints.add(new Point((int) x, (int) y));
            vx *= dampingFactor;
            vy *= dampingFactor;
        }
    }

    public void levelOne() {
        b = new Ball(340, 690, new Vector(0, 0));
        h = new Hole(340, 90);

        Wall lWall = new Wall(230, 35, 760, 90);
        Wall rWall = new Wall(455, 35, 760, 90);
        Wall tWall = new Wall(230, 35, 221, 0);
        Wall bWall = new Wall(230, 791, 221, 0);

        walls = new ArrayList<Wall>();
        walls.add(lWall);
        walls.add(rWall);
        walls.add(tWall);
        walls.add(bWall);
    }

    public void paintComponent(Graphics k) {
        super.paintComponent(k); // Call the superclass method to ensure the panel is properly rendered
        k.drawImage(bg, 0, 0, width, height, null);
        Graphics2D g = (Graphics2D) k;
        for (Wall w : walls) {
            w.drawWall(g);
        }
        b.draw(g);
        h.draw(g);

        if (initialClick != null) {
            drawProjectionLine(g);
        }
    }

    private void drawProjectionLine(Graphics2D g) {
        g.setColor(Color.RED);
        for (int i = 0; i < projectionPoints.size(); i++) {
            Point p = projectionPoints.get(i);
            int size = Math.max(2, 10 - i); // Dots get smaller further from the ball
            g.fillOval(p.x - size / 2, p.y - size / 2, size, size);
        }
    }
}
