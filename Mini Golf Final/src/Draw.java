import java.awt.Graphics;

import javax.swing.JPanel;

public class Draw extends JPanel{
	
	
	

	public Draw(int x, int y, int width, int height,Graphics g) {
		g.drawRect(x, y, width, height);
	}
	
	
}
