import javax.swing.JFrame;

public class Driver {
	
    public static void main(String[] args) throws Exception {
    	
    	JFrame frame = new JFrame();
		frame.setTitle("Minigolf");
		frame.setSize(1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Draw d = new Draw(400, 400);
       
        frame.add(d);
        frame.setVisible(true);
        
    }
    
  //jhkhkhjkh
}
