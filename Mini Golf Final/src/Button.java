import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Button extends JButton implements MouseListener {
    private Color defaultColor = Color.LIGHT_GRAY;
    private Color clickedColor = Color.DARK_GRAY;
    private boolean isClicked = false;

    public Button(String text) {
        super(text);
        setFocusPainted(false);
        setBackground(defaultColor);
        setOpaque(true);
        setBorderPainted(false);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle click actions here if needed
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isClicked = true;
        setBackground(clickedColor);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isClicked = false;
        setBackground(defaultColor);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
