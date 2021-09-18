
//this
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display extends JFrame {

	Image buffImg;
	Graphics buffG;

	private int genrationCount;
	private static final int mapWidth = 1024;
	private static final int mapHeight = 760;
	private static final int safeZoneRadius = 100;
	private static final int safeZoneX = 462;
	private static final int safeZoneY = 340;
	private int generation = 0;
	private int preySize = 30;
	private int predatorSize = 10;
	private int callMoveCount;
	private ArrayList<Prey> preys = new ArrayList<>();
	private ArrayList<Predator> predators = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	private boolean runScreen = false;
	public static void main(String[] args) {
		new Display();
	}
	//1세대 지남
	//속도 조작 
	//기타 사항 조

	Display() {
		JFrame controller= new JFrame();
		controller.setTitle("Controller");
		controller.setSize(384,mapHeight);
		controller.setLocation(mapWidth, 0);
		controller.setDefaultCloseOperation(controller.EXIT_ON_CLOSE);
		controller.setVisible(true);
		Container c= getContentPane();
		c.setLayout(null);
		JButton bt1=new JButton("Run");
		bt1.setSize(100, 50);
		bt1.setLocation(142, mapHeight-100);
		c.add(bt1);
		controller.add(c);

		setTitle("GeneticSimulation");
		setSize(mapWidth, mapHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		genrationCount = 0;
	}
	
	@Override
	public void paint(Graphics g) {
		// �씠以� 踰꾪띁留� 肄붾뱶
		buffImg = createImage(getWidth(), getHeight());
		buffG = buffImg.getGraphics();
		// update==媛쒖껜 洹몃━湲� 諛� �씠�룞
		update(g);
	}

	@Override
	public void repaint() {
	}
	
	public void ObjectInitiate()
	{
		for (int i = 0; i < preySize; i++) {
			double x, y;
			if (runScreen == false) {
				x = (int) (Math.random() * (mapWidth - 30)) + 10;
				y = (int) (Math.random() * (mapHeight - 60)) + 30;
				preys.add(new Prey(x, y));
			}
		}
		for (int i = 0; i < predatorSize; i++) {
			double x, y;
			if (runScreen == false) {
				x = (int) (Math.random() * (mapWidth - 30)) + 10;
				y = (int) (Math.random() * (mapHeight - 60)) + 30;
				predators.add(new Predator(x, y));
			}
		}
		
	}

	@Override
	public void update(Graphics g) {
        double distance;
        double distanceFromSafe;
        //ObjectInitiate();
        for (int i = 0; i < preySize; i++) {
			double x, y;
			if (runScreen == false) {
				x = (int) (Math.random() * (mapWidth - 30)) + 10;
				y = (int) (Math.random() * (mapHeight - 60)) + 30;
				preys.add(new Prey(x, y));
			}
		}
		for (int i = 0; i < predatorSize; i++) {
			double x, y;
			if (runScreen == false) {
				x = (int) (Math.random() * (mapWidth - 30)) + 10;
				y = (int) (Math.random() * (mapHeight - 60)) + 30;
				predators.add(new Predator(x, y));
			}
		}
		buffG.clearRect(0, 0, mapWidth, mapHeight);
		buffG.setColor(Color.GREEN);
		buffG.fillOval(safeZoneX, safeZoneY, safeZoneRadius, safeZoneRadius);

		//ObjectReset(); 
		double x;
		double y;
		for(int i = 0 ; i <preys.size(); i++)
		{
			preys.get(i).Move();
			x = preys.get(i).getX();
			y = preys.get(i).getY();
		
			buffG.setColor(Color.BLACK);
			buffG.fillOval((int)x, (int)y, 20, 20);
		}

		for (int i = 0; i < predatorSize; i++) {
			predators.get(i).Move();
			x = predators.get(i).getX();
			y = predators.get(i).getY();
			
			for (int j = 0; j < preys.size(); j++) {
						distance = (double) (Math.pow((predators.get(i).getX() - preys.get(j).getX()),2)
										   + Math.pow((predators.get(i).getY() - preys.get(j).getY()),2));
				distanceFromSafe = (double) (Math.pow((predators.get(i).getX()-safeZoneX),2)
										   + Math.pow((predators.get(i).getY()-safeZoneY),2));
						distance = Math.sqrt(distance);
				distanceFromSafe = Math.sqrt(distanceFromSafe);

				if ((distanceFromSafe <= safeZoneRadius - predators.get(i).getRadius())) { 
					buffG.setColor(Color.GREEN);
					buffG.fillOval(50, 50, 30, 30);
				}
				if (distance <= preys.get(j).getRadius() + predators.get(i).getRadius()) { 
						System.out.println("(i : "+ i +", j : "+ j +")");
						preys.remove(j);
						preySize--;						
				}
			}
			for(int t = 0 ; t <preys.size(); t++)
			{
				Prey tem = preys.get(t).ReproducebySelf();
				preys.add(tem);
				buffG.setColor(Color.BLACK);
				buffG.fillOval((int)tem.getX(), (int)tem.getY(), 20, 20);
			}

			buffG.setColor(Color.RED);
			buffG.fillOval((int)x, (int)y, 20, 20);
		}
		runScreen=true;
		g.drawImage(buffImg,0,0,this);
		super.repaint();
	}
}
