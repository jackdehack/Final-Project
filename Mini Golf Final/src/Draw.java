import java.awt.Graphics;

import javax.swing.JPanel;

public class Draw extends JPanel{
	int width;
	int height;
	public Draw(int width, int height) {
		this.width = width;
		this.height = height;
	}
	

	public void paintComponent(Graphics g) {
		Wall leftWall = new Wall(10, 10, 100, 4, g);
		leftWall.drawWall(g);
	}
	
	
}
