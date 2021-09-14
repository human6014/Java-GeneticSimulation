
//this
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {

	Image buffImg;
	Graphics buffG;

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
		new Main();
	}

	Main() {
		setTitle("GeneticSimulation");
		setSize(mapWidth, mapHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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

	@Override
	public void update(Graphics g) {
        double distance;
        double distanceFromSafe;
        
		buffG.clearRect(0, 0, mapWidth, mapHeight);
		// �븞�쟾吏��� �깮�꽦
		buffG.setColor(Color.GREEN);
		buffG.fillOval(safeZoneX, safeZoneY, safeZoneRadius, safeZoneRadius);
		// 癒뱀씠 �쐞移� �옄�룞 �깮�꽦
		for (int i = 0; i < preySize; i++) {
			int x, y;
			if (runScreen == false) {
				x = (int) (Math.random() * (mapWidth - 30)) + 10;
				y = (int) (Math.random() * (mapHeight - 60)) + 30;
				preys.add(new Prey(x, y));
			}
			preys.get(i).Move(); // 臾댁옉�쐞 �씠�룞 Move()�븿�닔 �셿�꽦 �떆 援ъ껜�솕
			x = preys.get(i).getX();
			y = preys.get(i).getY();
			
			if(preys.get(i).getVisible()==true) {
				buffG.setColor(Color.BLACK);
				buffG.fillOval(x, y, 20, 20);
			}
		}
		// �룷�떇�옄 �쐞移� �옄�룞 �깮�꽦
		for (int i = 0; i < predatorSize; i++) {
			int x, y;
			if (runScreen == false) {
				x = (int) (Math.random() * (mapWidth - 30)) + 10;
				y = (int) (Math.random() * (mapHeight - 60)) + 30;
				predators.add(new Predator(x, y));
			}
			x = predators.get(i).getX();
			y = predators.get(i).getY();
			predators.get(i).Move();// 臾댁옉�쐞 �씠�룞 Move()�븿�닔 �셿�꽦 �떆 援ъ껜�솕
			
			for (int j = 0; j < preys.size(); j++) {
						distance = (double) (Math.pow((predators.get(i).getX() - preys.get(j).getX()),2)
										   + Math.pow((predators.get(i).getY() - preys.get(j).getY()),2));
				distanceFromSafe = (double) (Math.pow((predators.get(i).getX()-safeZoneX),2)
										   + Math.pow((predators.get(i).getY()-safeZoneY),2));
						distance = Math.sqrt(distance);
				distanceFromSafe = Math.sqrt(distanceFromSafe);
				// 占쎈툧占쎌읈�뤃�딅열 占쎈툧占쎈퓠 揶쏆뮇猿쒎첎占� 鈺곕똻�삺占쎈릭占쎈뮉 野껋럩�뒭
				if ((distanceFromSafe <= safeZoneRadius - predators.get(i).getRadius())) { // �룷�떇�옄媛� 以묒븰 �븞�쟾吏����뿉 �떯�븯�쓣 �븣
					buffG.setColor(Color.GREEN);
					buffG.fillOval(50, 50, 30, 30);
				}
				if (distance <= preys.get(j).getRadius() + predators.get(i).getRadius()) { // �룷�떇�옄媛� 癒뱀씠�뿉 �떯�븯�쓣 �븣
					
					if(preys.get(j).getVisible()==true) {
						System.out.println("(i : "+ i +", j : "+ j +")");
						preys.get(j).setVisible(false);
						//preys.remove(j);
						preys.add(new Prey((int) (Math.random() * (mapWidth - 30)) + 10,(int) (Math.random() * (mapHeight - 60)) + 30));
						preySize++;
					}
				}
			}
			buffG.setColor(Color.RED);
			buffG.fillOval(x, y, 20, 20);
		}
		runScreen=true;
		g.drawImage(buffImg,0,0,this);
		super.repaint();
	}
}
