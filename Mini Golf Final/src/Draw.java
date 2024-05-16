import java.awt.Graphics;

import javax.swing.JPanel;

public class Draw extends JPanel{
	int width;
	int height;
	public Draw(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	//jhkhkhjkh
	public void paintComponent(Graphics g) {
		Wall leftWall = new Wall(250, 10, 4, 100, g);
		Wall tWall = new Wall(250, 10, 100, 4, g);
		Wall rWall = new Wall(350, 10, 4, 400, g);
		Wall bWall = new Wall(250, 110, 50, 4, g);
		Wall sWall = new Wall(300, 110, 4, 200, g);
		Wall btWall = new Wall(250, 310, 50, 4, g);
		Wall blWall = new Wall(250, 310, 4, 100, g);
		Wall bbWall = new Wall(250, 410, 100, 4, g);
		leftWall.drawWall(g);
		tWall.drawWall(g);
		rWall.drawWall(g);
		bWall.drawWall(g);
		btWall.drawWall(g);
		
	}
	
	
}
