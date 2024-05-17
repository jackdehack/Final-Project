import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Draw extends JPanel{
	int width;
	int height;
	Ball b;
	
	public Draw(int width, int height) {
		this.width = width;
		this.height = height;
		Vector ballv = new Vector(2,2);
		b = new Ball(290,20,ballv);
	}
	
	//jhkhkhjkh
	public void paintComponent(Graphics k) {
		Graphics2D g = (Graphics2D)k;
		Wall leftWall = new Wall(250, 10, 100, 90);
		Wall tWall = new Wall(250, 10, 100,  0);
		Wall rWall = new Wall(350, 10, 400, 90);
		Wall bWall = new Wall(250, 110, 50, 0);
		Wall sWall = new Wall(300, 110, 200, 90);
		Wall btWall = new Wall(250, 310, 50, 0);
		Wall blWall = new Wall(250, 310, 100, 90);
		Wall bbWall = new Wall(250, 410, 100, 0);
		b.draw(g);
		leftWall.drawWall(g);
		tWall.drawWall(g);
		rWall.drawWall(g);
		bWall.drawWall(g);
		btWall.drawWall(g);
		sWall.drawWall(g);
		blWall.drawWall(g);
		bbWall.drawWall(g);
		
	}
	
	
}
