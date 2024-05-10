import java.awt.Graphics;

import javax.swing.JPanel;

public class Draw extends JPanel{
	
	
	

	public Draw(int x, int y, Graphics g, int width, int height) {
		g.drawRect(x, y, width, height);
	}
	
	
}
