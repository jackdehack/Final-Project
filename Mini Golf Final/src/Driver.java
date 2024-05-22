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

        while (true) {
            for (Wall w : d.walls) {
                d.b.checkCollides(w);
            }
            d.b.x += d.b.ballv.x;
            d.b.y += d.b.ballv.y;
            
            Thread.sleep(10);
            frame.repaint();
        }
    }
}
