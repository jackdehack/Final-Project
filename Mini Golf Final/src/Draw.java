import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Draw extends JPanel{
	int width;
	int height;
	public Draw(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	//jhkhkhjkh
	public void paintComponent(Graphics k) {
		Graphics2D g = Graphics2D(k);
		Wall leftWall = new Wall(250, 10, 4, 100, 0);
		Wall tWall = new Wall(250, 10, 100, 4, 0);
		Wall rWall = new Wall(350, 10, 4, 400, 0);
		Wall bWall = new Wall(250, 110, 50, 4, 0);
		Wall sWall = new Wall(300, 110, 4, 200, 0);
		Wall btWall = new Wall(250, 310, 50, 4, 0);
		Wall blWall = new Wall(250, 310, 4, 100, 0);
		Wall bbWall = new Wall(250, 410, 100, 4, 0);
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
