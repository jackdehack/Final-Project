import java.awt.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LevelHandler extends JPanel {
	int width;
	int height;
	Ball b;
	ArrayList<Wall> walls;
	BufferedImage bg;
	Hole h;
	Point initialClick;
	ArrayList<Point> projectionPoints;
	ArrayList<BouncingObstacle> bouncys;
	ArrayList<Hill> hills;
	
	static int[] holeSwings = new int[5];
	int swings;
	int par;
	
	String difficulty;

	double maxSpeed = 6.5;


	public static int calculateHoleStars(int swings, int par) {
		if (swings <= par && swings > 0) {
			return 3;
		}

		if (swings == par + 1) {
			return 2;
		}

		if (swings == par + 2) {
			return 1;
		}

		return 0;
	}

	public LevelHandler(int width, int height, int level, String difficulty) {
		try {
			bg = ImageIO.read(new File("Background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.difficulty = difficulty;

		this.width = width;
		this.height = height;
		projectionPoints = new ArrayList<>();

		if (level == 1) {
			levelOne();
		}

		if (level == 2) {
			levelTwo();
		}

		if (level == 3) {
			levelThree();
		}

		if (level == 4) {
			levelFour();
		}

		if (level == 5) {
			levelFive();
		}

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (b.contains(e.getPoint()) && b.ballv.magnitude() == 0) {
					initialClick = e.getPoint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (initialClick != null) {
					Point releasePoint = e.getPoint();
					double dx = initialClick.x - releasePoint.x;
					double dy = initialClick.y - releasePoint.y;
					double velocityFactor = 0.1; // Adjust as needed for game balance
					Vector newVelocity = new Vector(dx * velocityFactor, dy * velocityFactor);
					b.ballv = capSpeed(newVelocity, maxSpeed);
					initialClick = null;
					projectionPoints.clear();
					swings++;
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (initialClick != null) {
					Point currentPoint = e.getPoint();
					double dx = initialClick.x - currentPoint.x;
					double dy = initialClick.y - currentPoint.y;
					double velocityFactor = 0.1; // Same factor as in mouseReleased
					Vector projectedVelocity = new Vector(dx * velocityFactor, dy * velocityFactor);
					projectedVelocity = capSpeed(projectedVelocity, maxSpeed);
					updateProjectionPoints(projectedVelocity);
				}
			}
		});
	}

	private Vector capSpeed(Vector velocity, double maxSpeed) {
		double speed = velocity.magnitude();
		if (speed > maxSpeed) {
			double scale = maxSpeed / speed;
			return new Vector(velocity.x * scale, velocity.y * scale);
		}
		return velocity;
	}

	private void updateProjectionPoints(Vector velocity) {
		projectionPoints.clear();
		double timeStep = 4;
		double x = b.x;
		double y = b.y;
		double vx = velocity.x;
		double vy = velocity.y;

		for (int i = 0; i < velocity.magnitude() * 1.5; i++) {
			x += vx * timeStep;
			y += vy * timeStep;
			projectionPoints.add(new Point((int) x, (int) y));
		}
	}

	public void levelOne() {
		par = 2;

		b = new Ball(340, 690, new Vector(0, 0));
		h = new Hole(340, 90);
		
		hills = new ArrayList<Hill>();
		
		Hill h = new Hill(230, 400, 221, 200, 0, true);
		
		hills.add(h);
		
		

		Wall lWall = new Wall(230, 35, 760, 90);
		Wall rWall = new Wall(455, 35, 760, 90);
		Wall tWall = new Wall(230, 35, 221, 0);
		Wall bWall = new Wall(230, 791, 221, 0);

		walls = new ArrayList<Wall>();
		walls.add(lWall);
		walls.add(rWall);
		walls.add(tWall);
		walls.add(bWall);
	}

	public void levelTwo() {
		par = 3;

		b = new Ball(240, 670, new Vector(0, 0));
		h = new Hole(230, 90);

		Wall wall1 = new Wall(190, 35, 200, 90);
		Wall wall2 = new Wall(190, 35, 100, 0);
		Wall wall3 = new Wall(290, 35, 150, 45);
		Wall obstacle = new Wall(320, 180, 40, 40, -15);
		Wall wall4 = new Wall(186, 235, 104, 0);
		Wall wall5 = new Wall(396, 141, 450, 90);
		Wall wall6 = new Wall(290, 235, 250, 90);
		Wall wall7 = new Wall(290, 485, 150, 135);
		Wall wall8 = new Wall(396, 591, 106, 180);
		Wall wall9 = new Wall(186, 587, 100, 90);
		Wall wall10 = new Wall(182, 687, 110, 0);
		Wall wall11 = new Wall(294, 591, 100, 90);

		walls = new ArrayList<Wall>();

		walls.add(wall1);
		walls.add(wall2);
		walls.add(wall3);
		walls.add(wall4);
		walls.add(wall5);
		walls.add(wall6);
		walls.add(wall7);
		walls.add(wall8);
		walls.add(wall9);
		walls.add(wall10);
		walls.add(wall11);
		walls.add(obstacle);

	}

	public void levelThree() {
		par = 4;

		b = new Ball(440, 650, new Vector(0, 0));
		h = new Hole(320, 140);

		
		bouncys = new ArrayList<BouncingObstacle>();
		BouncingObstacle bOb1 = new BouncingObstacle(300, 590);
		bouncys.add(bOb1);
		
		
		Wall wall1 = new Wall(380, 700, 130, 0);
		Wall wall2 = new Wall(380, 600, 130, 0);
		Wall wall3 = new Wall(510, 600, 104, 90);
		Wall obstacle = new Wall(300, 220, 50, 50, 15);
		Wall obstacle2 = new Wall(290, 420, 10, 60, -15);
		Wall wall4 = new Wall(380, 700, 100, 90);
		Wall wall5 = new Wall(380, 800, 120, 180);
		Wall wall6 = new Wall(380, 100, 120, 180);
		Wall wall7 = new Wall(380, 600, 504, -90);
		Wall wall8 = new Wall(260, 96, 704, 90);

		walls = new ArrayList<Wall>();

		walls.add(wall1);
		walls.add(wall2);
		walls.add(wall3);
		walls.add(wall4);
		walls.add(wall5);
		walls.add(wall6);
		walls.add(wall7);
		walls.add(wall8);
		walls.add(obstacle);
		walls.add(obstacle2);
	}

	public void levelFour() {
		par = 5;

		// Starting position of the ball
		b = new Ball(30, 670, new Vector(0, 0));
		// Position of the hole
		h = new Hole(400, 100);

		// Define walls and obstacles to create a difficult path
		walls = new ArrayList<>();

		// Outer boundaries
		walls.add(new Wall(50, 50, 100, 0));
		walls.add(new Wall(344, 47, 120, 0)); // Top boundary

		// Bottom boundary

		walls.add(new Wall(50, 50, 200, 90)); // left corner
		walls.add(new Wall(150, 50, 100, 90));
		walls.add(new Wall(50, 247, 141, 45));
		walls.add(new Wall(146, 148, 100, 0));
		walls.add(new Wall(243, 148, 142, -45));
		walls.add(new Wall(464, 50, 100, 90));

		// rigjt box
		walls.add(new Wall(460, 148, 100, 0));
		walls.add(new Wall(560, 148, 100, 90));
		walls.add(new Wall(460, 248, 100, 0));
		walls.add(new Wall(464, 250, 230, 90));

		walls.add(new Wall(10, 630, 80, 90));
		walls.add(new Wall(180, 550, 160, 90));
		walls.add(new Wall(7, 707, 170, 0));
		walls.add(new Wall(7, 626, 85, 0));
		walls.add(new Wall(92, 550, 80, 90));
		walls.add(new Wall(90, 550, 80, -45));
		walls.add(new Wall(150, 346, 150, 90));
		walls.add(new Wall(180, 550, 100, 0));
		walls.add(new Wall(276, 550, 100, -45));
		walls.add(new Wall(345, 480, 118, 0));

		// Boundaries near the hole

		walls.add(new Wall(200, 280, 50, 50, 30));
		walls.add(new Wall(260, 250, 100, 100, 0));
		walls.add(new Wall(200, 380, 25, 25, 30));
		walls.add(new Wall(175, 480, 40, 40, -10));
		walls.add(new Wall(75, 80, 35, 25, 30));
		walls.add(new Wall(140, 650, 30, 30, 45));
		walls.add(new Wall(410, 200, 45, 45, 45));

		// Path to the hole

		// Add more walls and obstacles as needed to match the map
	}

	public void levelFive() {

	}

	public void paintComponent(Graphics k) {
		super.paintComponent(k); // Call the superclass method to ensure the panel is properly rendered
		k.drawImage(bg, 0, 0, width, height, null);
		Graphics2D g = (Graphics2D) k;
		for (Wall w : walls) {
			w.drawWall(g);
		}
		
		if(bouncys != null) {
			for(BouncingObstacle b: bouncys) {
				b.draw(g);
			}
		}
		
		
		if(hills != null) {
			for(Hill h: hills) {
				h.drawHill(g);
			}
		}
		
		h.draw(g);
		b.draw(g);

		if (initialClick != null) {
			drawProjectionLine(g);
		}
	}

	private void drawProjectionLine(Graphics2D g) {
		g.setColor(Color.white);
		
		if(this.difficulty.equals("Easy")) {
		for (int i = 0; i < projectionPoints.size(); i++) {
			Point p = projectionPoints.get(i);
			g.fillOval(p.x, p.y, 4, 4);
		}
		}
		
		
		
		if(this.difficulty.equals("Medium")) {
		for (int i = 0; i < Math.min(projectionPoints.size(), 4); i++) {
			Point p = projectionPoints.get(i);
			g.fillOval(p.x, p.y, 4, 4);
		}
		}
		
		
		
		
		if(this.difficulty.equals("Hard")) {
		for (int i = 0; i < Math.min(projectionPoints.size(), 1); i++) {
			Point p = projectionPoints.get(i);
			g.fillOval(p.x, p.y, 4, 4);
		}
		}
		
		
	}

}
