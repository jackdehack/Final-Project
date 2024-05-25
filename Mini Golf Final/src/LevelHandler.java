import java.awt.Graphics;
import java.awt.Graphics2D;
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

    public LevelHandler(int width, int height) {
    	try {
            bg = ImageIO.read(new File("Background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
        this.width = width;
        this.height = height;  
        
        
      //if(levelOne ...){}
        levelOne();
    }
    
    
    
    public void levelOne() {
    	Vector ballv = new Vector(0, -2);
        b = new Ball(340, 190, ballv);
        h = new Hole(340, 90);
        
        Wall lWall = new Wall (230, 35, 760, 90);
        Wall rWall = new Wall (455, 35, 760, 90);
        Wall tWall = new Wall (230, 35, 221, 0);
        Wall bWall = new Wall (230, 791, 221, 0);

    

        walls = new ArrayList<Wall>();
        walls.add(lWall);
        walls.add(rWall);
        walls.add(tWall);
        walls.add(bWall);
        
        
  
    }

    public void paintComponent(Graphics k) {
    	k.drawImage(bg, 0, 0, width, height, null);
        Graphics2D g = (Graphics2D) k;
        for (Wall w : walls) {
            w.drawWall(g);
        }
        b.draw(g);
        h.draw(g);
    }
}
