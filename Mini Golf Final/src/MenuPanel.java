import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
	private Button startButton;
	private Button[] difficultyButtons;
	private Button[] levelButtons;

	public MenuPanel() {
		setLayout(null);
		// Background
//		JLabel background = new JLabel(new ImageIcon("background.png"));
//		background.setBounds(0, 0, 720, 1280);
//		add(background);
//
//		// Logo (optional)
//		JLabel logo = new JLabel(new ImageIcon("logo.png"));
//		logo.setBounds(210, 50, 300, 150);
//		add(logo);
		// Start button
		startButton = new Button("Start");
		startButton.setBounds(310, 440, 100, 50);
		add(startButton);
		// Difficulty buttons
		String[] difficulties = { "Easy", "Medium", "Hard" };
		difficultyButtons = new Button[3];
		for (int i = 0; i < difficulties.length; i++) {
			difficultyButtons[i] = new Button(difficulties[i]);
			difficultyButtons[i].setBounds(220 + i * 100, 30, 80, 50);
			add(difficultyButtons[i]);
		}
		// Level buttons
		levelButtons = new Button[10];
		for (int i = 0; i < 10; i++) {
			levelButtons[i] = new Button("Level " + (i + 1));
			levelButtons[i].setBounds(120 + (i % 5) * 100, 800 + (i / 5) * 60, 80, 50);
			add(levelButtons[i]);
		}
	}

	// Getter methods for the buttons
	public Button getStartButton() {
		return startButton;
	}

	public Button[] getDifficultyButtons() {
		return difficultyButtons;
	}

	public Button[] getLevelButtons() {
		return levelButtons;
	}
}
