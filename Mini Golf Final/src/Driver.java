import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Driver {
    private static JFrame frame;
    private static LevelHandler gamePanel;
    static Label par;
    static Label shotsTaken;
    private static MenuPanel menuPanel;
    private static boolean gameStarted = false;
    static int level;
    static String difficulty = "";

    public static void main(String[] args) throws Exception {
        frame = new JFrame();
        frame.setTitle("Minigolf");
        frame.setSize(720, 847);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        menuPanel = new MenuPanel();
        frame.add(menuPanel);
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
        
        // action listeners for difficulty buttons
        for (Button difficultyButton : menuPanel.getDifficultyButtons()) {
            difficultyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // difficulty selection
                    System.out.println("Difficulty selected: " + difficultyButton.getLabel());
                    difficulty = (difficultyButton.getLabel());
                    System.out.println(difficultyButton.getLabel());
                }
            });
        }
        
        // action listeners for level buttons
        for (Button levelButton : menuPanel.getLevelButtons()) {
            levelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // level selection
                    System.out.println("Level selected: " + levelButton.getLabel());
                    level = Integer.parseInt((levelButton.getLabel().substring(6)));            
                }
            });
        }
    }

    private static void startGame() {
        // set default level and difficulty if not selected
        if(level == 0) {
            level = 1;
        }   
        
        if(difficulty.equals("")) {
            difficulty = "Easy";
        }
        
        gamePanel = new LevelHandler(720, 1280, level, difficulty);
        
        // labels for shots taken and par
        shotsTaken = new Label("Swings: " + gamePanel.swings);
        shotsTaken.setBounds(360, 0, 70, 20);
        gamePanel.add(shotsTaken);
        
        par = new Label("Par: " + gamePanel.par);
        par.setBounds(290, 0, 40, 20);
        gamePanel.add(par);
        
        // remove menu panel, add game panel to the frame
        frame.remove(menuPanel);
        frame.add(gamePanel);
        frame.revalidate();
        frame.repaint();
        gameStarted = true;
        
        // add mouse listener to the back button
        gamePanel.backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gamePanel.goBack = true;
            }
        });
        
        // start game loop in new thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameLoop();
            }
        }).start();
    }

    private static void gameLoop() {
        // set friction factor to slow down the ball
        double frictionFactor = 0.98;
        int counter = 0;

        // loop while the game is running
        while (gameStarted) {
            // flag animation if triggered
            if(gamePanel.h.animationTriggered) {
                counter++;
                if(counter <= 30) {
                    gamePanel.h.flagY -= counter * 5;
                    frame.repaint();
                }
                if(counter == 30) {
                    gamePanel.h.isVisible = false;
                }
            }
            
            // update the labels for shots taken and par
            shotsTaken.setText("Swings: " + gamePanel.swings);
            par.setText("Par: " + gamePanel.par);
            
            // check for collisions with walls
            for (Wall w : gamePanel.walls) {
                gamePanel.b.checkCollides(w);
            }
            
            // check for collisions with bouncing obstacles
            if(gamePanel.bouncys != null) {
                for(BouncingObstacle bob: gamePanel.bouncys) {
                    gamePanel.b.checkCollides(bob);
                }
            }
            
            // check for collisions with portals
            if(gamePanel.portals != null) {
                for(Portal p: gamePanel.portals) {
                    gamePanel.b.checkCollides(p);
                }
            }
            
            // check for collisions with hills
            if(gamePanel.hills != null) {
                for(Hill h: gamePanel.hills) {
                    h.checkApplyGravity(gamePanel.b);
                }
            }
            
            // check for collision with the hole
            gamePanel.b.checkCollides(gamePanel.h);
            
            // apply friction to the ball's velocity
            gamePanel.b.ballv.x *= frictionFactor;
            gamePanel.b.ballv.y *= frictionFactor;
            
            // update the ball's position
            gamePanel.b.x += gamePanel.b.ballv.x;
            gamePanel.b.y += gamePanel.b.ballv.y;
            
            // stop the ball if its velocity is very small
            if (gamePanel.b.ballv.magnitude() < 0.1) {
                gamePanel.b.ballv = new Vector(0, 0);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frame.repaint();
            
            // case when the ball is in the hole
            if(gamePanel.b.isIn) {
                if(LevelHandler.holeSwings[level - 1] == 0 || gamePanel.swings < LevelHandler.holeSwings[level - 1]) {
                    LevelHandler.holeSwings[level - 1] = gamePanel.swings;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            
            // case when the back button is clicked
            if(gamePanel.goBack) {
                break;
            }
        }

        // update the total swings taken across all levels
        int sumSwings = 0;
        for (int i = 0; i < LevelHandler.holeSwings.length; i++) {
            sumSwings += LevelHandler.holeSwings[i];
        }
        menuPanel.swings = sumSwings;

        // remove the game panel and add the menu panel back to the frame
        frame.remove(gamePanel);
        frame.add(menuPanel);
        menuPanel.refreshData();
        frame.repaint();
    }
}
