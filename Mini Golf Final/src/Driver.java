import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Driver {
	private static JFrame frame;
	private static LevelHandler gamePanel;
	static Label par;
	static Label shotsTaken;
	
	private static MenuPanel menuPanel;
	private static boolean gameStarted = false;
	static int level;

	public static void main(String[] args) throws Exception {
		frame = new JFrame();
		frame.setTitle("Minigolf");
		frame.setSize(720, 1280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Initialize the menu panel
		menuPanel = new MenuPanel();
		frame.add(menuPanel);
		// Add action listeners to the buttons
		addMenuButtonListeners();
		frame.setVisible(true);
	}

	private static void addMenuButtonListeners() {
		menuPanel.getStartButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startGame();
			}
		});
		for (Button difficultyButton : menuPanel.getDifficultyButtons()) {
			difficultyButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Handle difficulty selection
					System.out.println("Difficulty selected: " + difficultyButton.getLabel());
				}
			});
		}
		for (Button levelButton : menuPanel.getLevelButtons()) {
			levelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Handle level selection
					System.out.println("Level selected: " + levelButton.getLabel());
					level = Integer.parseInt((levelButton.getLabel().substring(6)));
				
							
							
				}
			});
		}
	}

	private static void startGame() {
		if(level == 0) {
			level = 1;
		}	
		gamePanel = new LevelHandler(720, 1280, level);
		
		shotsTaken = new Label("Swings: " + gamePanel.swings);
		shotsTaken.setAlignment(Label.RIGHT);
		gamePanel.add(shotsTaken, BorderLayout.NORTH);
		
		par = new Label("Par: " + gamePanel.par);
		par.setAlignment(Label.RIGHT);
		gamePanel.add(par, BorderLayout.NORTH);
		
		
		frame.remove(menuPanel);
		
		frame.add(gamePanel);
		frame.revalidate();
		frame.repaint();
		gameStarted = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				gameLoop();
			}
		}).start();
	}

	private static void gameLoop() {
		double frictionFactor = 0.98; // Adjust to model friction; closer to 1 means less friction
		while (gameStarted) {
			
			shotsTaken.setText("Swings: " + gamePanel.swings);
			par.setText("Par: " + gamePanel.par);
			
			for (Wall w : gamePanel.walls) {
				gamePanel.b.checkCollides(w);
			}
			gamePanel.b.checkCollides(gamePanel.h);
			// Apply friction
			gamePanel.b.ballv.x *= frictionFactor;
			gamePanel.b.ballv.y *= frictionFactor;
			// Update ball position
			gamePanel.b.x += gamePanel.b.ballv.x;
			gamePanel.b.y += gamePanel.b.ballv.y;
			// Stop ball if velocity is very small
			if (gamePanel.b.ballv.magnitude() < 0.1) {
				gamePanel.b.ballv = new Vector(0, 0);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			frame.repaint();
			
			if(gamePanel.b.isIn) {
				break;
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.remove(gamePanel);
		frame.add(menuPanel);
		frame.repaint();
	}
	
	
	
	
}
