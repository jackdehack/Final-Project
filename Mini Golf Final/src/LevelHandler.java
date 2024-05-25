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

    public LevelHandler(int width, int height) {
        try {
            bg = ImageIO.read(new File("Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.width = width;
        this.height = height;  

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
                    b.ballv = new Vector(dx * velocityFactor, dy * velocityFactor);
                    initialClick = null;
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // You can add visual feedback for the drag here if needed
            }
        });
    }

    public void levelOne() {
        b = new Ball(405, 190, new Vector(0, 0));
        h = new Hole(311, 50);

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
    }
}
