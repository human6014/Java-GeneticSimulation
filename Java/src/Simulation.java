import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class Simulation extends JFrame {
	Image buffImg;
	Graphics2D buffG;

	private int genrationCount;
	private final int mapWidth = 1280;
	private final int mapHeight = 820;
	private final int safeZoneRadius = 150;
	private final int safeZoneX = mapWidth / 2 - safeZoneRadius / 2;
	private final int safeZoneY = mapHeight / 2 - safeZoneRadius / 2;
	private int generation = 0;
	private int preySize = 50;// 50
	private int predatorSize = 3;// 4
	private double[] average = { 0, 0, 0 };
	private ArrayList<Prey> preys = new ArrayList<>();
	private ArrayList<Predator> predators = new ArrayList<>();

	public void reset(int preySize, int predatorSize) {
		generation = 0;
		genrationCount = 0;
		preys.clear();
		predators.clear();
		this.preySize = preySize;
		this.predatorSize = predatorSize;
		arrangement(this.preySize, this.predatorSize);
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
		arrangement(preySize, predatorSize);
	}

	public void arrangement(int preySize, int predatorSize) {
		genrationCount = 1;
		for (int i = 0; i < predatorSize; i++)
			predators.add(new Predator());
		for (int j = 0; j < preySize; j++)
			preys.add(new Prey());
	}

	public int dot(float x1, float y1, float x2, float y2) {
		return (int)((x1 * x2) + (y1 * y2));
	}

	public boolean isIn(Prey tem, Predator predator) {
		float tempX[] = tem.getX();
		float tempY[] = tem.getY();

		float xAB = tempX[0] - tempX[1];
		float xAM = predator.getX()+17 - tempX[1];
		float xBC = tempX[3] - tempX[0];
		float xBM = predator.getX()+17 - tempX[0];

		float yAB = tempY[0] - tempY[1];
		float yAM = predator.getY()+17 - tempY[1];
		float yBC = tempY[3] - tempY[0];
		float yBM = predator.getY()+17 - tempY[0];

		float dotABBC = dot(xAB, yAB, xBC, yBC);
		float dotABAM = dot(xAB, yAB, xAM, yAM);
		float dotABAB = dot(xAB, yAB, xAB, yAB);
		float dotBCBM = dot(xBC, yBC, xBM, yBM);
		float dotBCBC = dot(xBC, yBC, xBC, yBC);

		//System.out.println(dotABBC);
		System.out.println(dotABAM);
		System.out.println(dotABAB);
		System.out.println(dotBCBM);
		System.out.println(dotBCBC+"\n");
		//System.out.println("tempX : "+tempX[0]+"\t"+tempX[1]+"\t"+tempX[2]);
		//System.out.println("tempY : "+tempY[0]+"\t"+tempY[1]+"\t"+tempY[2]);
		//System.out.println("dotABAM : "+dotABAM+"\tdotABAB : "+dotABAB+"\tdotBCBM : "+dotBCBM+"\tdotBCBC : "+dotBCBC);
		return  (0 <= dotABAM && dotABAM <= dotABAB && 0 <= dotBCBM && dotBCBM <= dotBCBC);
	}

	@Override
	public void paint(Graphics g) {
		buffImg = createImage(getWidth(), getHeight());
		buffG = (Graphics2D) buffImg.getGraphics();
		update(g);
	}

	@Override
	public void update(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		buffG.clearRect(0, 0, mapWidth, mapHeight);
		buffG.setColor(Color.GREEN);
		buffG.fillOval(safeZoneX, safeZoneY, safeZoneRadius + 30, safeZoneRadius + 30);
		double x;
		double y;

		int tempX[] = new int[4];
		int tempY[] = new int[4];
		buffG.setColor(Color.BLACK);
		for (int i = 0; i < preys.size(); i++) {
			preys.get(i).Move();
			// average[0]=preys.get(i).getRadius();
			average[1] = preys.get(i).getSpeed();
			average[2] = preys.get(i).SgetActivity();
			
			float tempx[]=preys.get(i).getX();
			float tempy[]=preys.get(i).getY();
			for(int j=0;j<4;j++) {
				tempX[j]=(int)tempx[j];
				tempY[j]=(int)tempy[j];
			}

			buffG.fillPolygon(tempX, tempY, 4);
		}

		Controller.averPrint(average[0] / preySize, average[1] / preySize, average[2] / preySize);

		for (int i = 0; i < predators.size(); i++) {
			predators.get(i).Move();
			for (int j = 0; j < preys.size(); j++) {
				if (isIn(preys.get(j),predators.get(i)))
				{
					System.out.println("먹음!------------------------- ");
					preys.remove(j);
				}
			}
			buffG.setColor(Color.RED);
			buffG.fillOval((int) predators.get(i).getX(), (int) predators.get(i).getY(), predators.get(i).getRadius(),
					predators.get(i).getRadius());
		}
		
		genrationCount++;

		if (genrationCount * Controller.Acceleration >= 2000) {
			int temNum = preySize;
			generation++;
			if (preys.size() < 300) {
				int probability;
				for (int t = 0; t < temNum; t++) {
					probability = (int) (Math.random() * 10 + 1);
					if (probability > 5) {
						if (!preys.get(t).isBreedingComplete()) {
							Prey tem = preys.get(t).reproduceBySelf();
							preys.add(tem);
							buffG.setColor(Color.BLACK);
							// buffG.fillPolygon( tem.getX(), tem.getY(), 4);
						}
					}
				}
			}
			genrationCount = 0;
		}

		buffG.setColor(Color.BLACK);
		buffG.setFont(new Font("SansSerif", Font.BOLD, 15));
		buffG.drawString("Generation : " + Integer.toString(generation), 20, 50);
		buffG.drawString("number of preys : " + Integer.toString(preys.size()), 20, 70);
		g2d.drawImage(buffImg, 0, 0, this);
		super.repaint();
	}
}
