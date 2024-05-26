import javax.swing.JFrame;

public class Driver {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setTitle("Minigolf");
        frame.setSize(720, 1280);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LevelHandler d = new LevelHandler(720, 1280);
        frame.add(d);
        frame.setVisible(true);

        double frictionFactor = 0.9865; // Adjust to model friction; closer to 1 means less friction

        while (true) {
            for (Wall w : d.walls) {
                d.b.checkCollides(w);
            }
            
            d.b.checkCollides(d.h);

            // Apply friction
            d.b.ballv.x *= frictionFactor;
            d.b.ballv.y *= frictionFactor;

            // Update ball position
            d.b.x += d.b.ballv.x;
            d.b.y += d.b.ballv.y;

            // Stop ball if velocity is very small
            if (d.b.ballv.magnitude() < 0.1) {
                d.b.ballv = new Vector(0, 0);
            }

            Thread.sleep(10);
            frame.repaint();
        }
    }
}
