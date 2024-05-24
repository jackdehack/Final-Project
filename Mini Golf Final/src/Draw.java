import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Draw extends JPanel {
    int width;
    int height;
    Ball b;
    ArrayList<Wall> walls;

    public Draw(int width, int height) {
        this.width = width;
        this.height = height;
        Vector ballv = new Vector(0, -2);
        b = new Ball(405, 190, ballv);

        // Horizontal walls
        Wall tWall = new Wall(250, 10, 500, 0);
        Wall bWall = new Wall(250, 290, 500, 0);

        // Vertical walls
        Wall lWall = new Wall(250, 10, 80, 90);
        Wall rWall = new Wall(400, 40, 20, 50, 90);
        Wall l2Wall = new Wall(750, 10, 280, 90);

        walls = new ArrayList<Wall>();
        walls.add(tWall);
        walls.add(bWall);
        walls.add(lWall);
        walls.add(l2Wall);
        walls.add(rWall);
       
        
    }

    public void paintComponent(Graphics k) {
        Graphics2D g = (Graphics2D) k;
        for (Wall w : walls) {
            w.drawWall(g);
        }
        b.draw(g);
    }
}
