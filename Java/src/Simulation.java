import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
public class Simulation extends JFrame {
	Image buffImg;
	Graphics2D buffG;
	
	private Controller controller;
	private int genrationCount;
	private final int mapWidth = 1280;
	private final int mapHeight = 820;
	private final int safeZoneRadius = 150;
	private final int safeZoneX = mapWidth / 2 - safeZoneRadius / 2;
	private final int safeZoneY = mapHeight / 2 - safeZoneRadius / 2;
	private int generation = 0;
	private int preySize = 50;//50
	private int predatorSize = 4;//4
	private double[] average= {0,0,0};
	private ArrayList<Prey> preys = new ArrayList<>();
	private ArrayList<Predator> predators = new ArrayList<>();
	public void reset(int preySize,int predatorSize) {
		generation=0;
		genrationCount=0;
		preys.clear();
		predators.clear();
		this.preySize=preySize;
		this.predatorSize=predatorSize;
		arrangement(this.preySize,this.predatorSize);
	}
	public void call(Controller controller) {
		this.controller=controller;
	}
	public void start() {
		Timer timer = new Timer(0, (ae) -> repaint());
		timer.setDelay(5);
		timer.start();
	}

	public static void main(String[] args) {
		new Simulation().start();
	}

	Simulation() {	
		this.setTitle("GeneticSimulation");
		this.setSize(mapWidth, mapHeight);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		new Controller(this);
		arrangement(preySize,predatorSize);
	}

	public void arrangement(int preySize,int predatorSize) {
		genrationCount = 1;
		double x, y;
		for (int i = 0; i < predatorSize; i++) 
			predators.add(new Predator());
		for (int j = 0; j < preySize; j++) 
			preys.add(new Prey());
	}

	@Override
	public void paint(Graphics g) {
		buffImg = createImage(getWidth(), getHeight());
		buffG = (Graphics2D) buffImg.getGraphics();
		update(g);
	}

	@Override
	public void update(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		buffG.clearRect(0, 0, mapWidth, mapHeight);
		buffG.setColor(Color.GREEN);
		buffG.fillOval(safeZoneX, safeZoneY, safeZoneRadius+30, safeZoneRadius+30);
		double distance;
		double x;
		double y;
		for (int i = 0; i < preySize; i++) {
			preys.get(i).Move();
			average[0]=preys.get(i).getRadius();
			average[1]=preys.get(i).getSpeed();
			average[2]=preys.get(i).SgetActivity();
			x = preys.get(i).getX();
			y = preys.get(i).getY();
			buffG.setColor(Color.BLACK);
			buffG.fillOval((int) x, (int) y, (int) preys.get(i).getRadius(), (int) preys.get(i).getRadius());
		}
		controller.averPrint(average[0]/preySize, average[1]/preySize, average[2]/preySize);
		
		/*
		Font font = new Font(null, Font.PLAIN, 10);
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(45), 0, 0);
		Font rotatedFont = font.deriveFont(affineTransform);
		g2d.setFont(rotatedFont);
		g2d.drawString("A String",100,100);
		*/
		
		for (int i = 0; i < predatorSize; i++) {
			predators.get(i).Move();
			x = predators.get(i).getX();
			y = predators.get(i).getY();
			for (int j = 0; j < preySize; j++) {
				distance = (double) (Math.pow((predators.get(i).getX() - preys.get(j).getX()), 2)
									+ Math.pow((predators.get(i).getY() - preys.get(j).getY()), 2));
				distance = Math.sqrt(distance);

				if (distance <= preys.get(j).getRadius() + predators.get(i).getRadius()) {
					// System.out.println("(i : "+ i +", j : "+ j +")");
					preys.remove(j);
					preySize--;
				}
			}
			buffG.setColor(Color.RED);
			buffG.fillOval((int) x, (int) y, predators.get(i).getRadius()+10, predators.get(i).getRadius()+10);
		}
		genrationCount++;

		if (genrationCount * controller.Acceleration >= 2000) {
			int temNum = preySize;
			generation++;
			if (preySize < 300) {
				int probability;
				for (int t = 0; t < temNum; t++) {
					probability = (int) (Math.random() * 10 + 1);
					if (probability > 5) {
						if (!preys.get(t).isBreedingComplete()) {
							Prey tem = preys.get(t).reproduceBySelf();
							preys.add(tem);
							preySize++;
							buffG.setColor(Color.BLACK);
							buffG.fillOval((int) tem.getX(), (int) tem.getY(), (int) tem.getRadius(), (int) tem.getRadius());
						}
					}
				}
			}
			genrationCount = 0;
		}

		buffG.setColor(Color.BLACK);
		buffG.setFont(new Font("SansSerif", Font.BOLD, 15));
		buffG.drawString("Generation : " + Integer.toString(generation), 20, 50);
		buffG.drawString("number of preys : " + Integer.toString(preySize), 20,70);
		g2d.drawImage(buffImg, 0, 0, this);
		super.repaint();
	}
}
