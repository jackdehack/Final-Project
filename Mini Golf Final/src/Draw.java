import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Draw extends JPanel{
	int width;
	int height;
	Ball b;
	ArrayList<Wall> walls;
	
	public Draw(int width, int height) {
		this.width = width;
		this.height = height;
		Vector ballv = new Vector(2,2);
		b = new Ball(290,50,ballv);
		
		
		//Wall leftWall = new Wall(250, 10, 100, 90);
		Wall tWall = new Wall(250, 10, 500,  0);
		
		Wall rWall = new Wall(250, 90, 500,  0);
		//Wall rWall = new Wall(350, 10, 400, 90);
		//Wall bWall = new Wall(250, 110, 50, 0);
		//Wall sWall = new Wall(300, 110, 200, 90);
		//Wall btWall = new Wall(250, 310, 50, 0);
		//Wall blWall = new Wall(250, 310, 100, 90);
		//Wall bbWall = new Wall(250, 410, 100, 0);
		
		walls = new ArrayList<Wall>();
		
		//walls.add(leftWall);
		//walls.add(bbWall);
		//walls.add(blWall);
		//walls.add(btWall);
		//walls.add(sWall);
		//walls.add(bWall);
		//walls.add(rWall);
		walls.add(tWall);
		walls.add(rWall);
	}
	

	public void paintComponent(Graphics k) {
		Graphics2D g = (Graphics2D)k;
		for(Wall w: walls) {
			w.drawWall(g);
		}
		
		b.draw(g);
	}
	
	
}
