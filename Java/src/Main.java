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
	private int predatorSize = 5;
	private int callMoveCount;
	private ArrayList<Prey> preys = new ArrayList<>();
	private ArrayList<Predator> predators = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	private boolean runScreen = false;

	public static void main(String[] args) {
		new Main();
	}

	Main() {
		setTitle("Main");
		setSize(mapWidth, mapHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// 이중 버퍼링 코드
		buffImg = createImage(getWidth(), getHeight());
		buffG = buffImg.getGraphics();
		// update==개체 그리기 및 이동
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
		// 안전지대 생성
		buffG.setColor(Color.GREEN);
		buffG.fillOval(safeZoneX, safeZoneY, safeZoneRadius, safeZoneRadius);
		// 먹이 위치 자동 생성
		for (int i = 0; i < preySize; i++) {
			int x, y;
			if (runScreen == false) {
				x = (int) (Math.random() * (mapWidth - 40)) + 20;
				y = (int) (Math.random() * (mapHeight - 50)) + 30;
				preys.add(new Prey(x, y));
			}
			preys.get(i).Move(); // 무작위 이동 Move()함수 완성 시 구체화
			x = preys.get(i).getX();
			y = preys.get(i).getY();

			buffG.setColor(Color.BLACK);
			buffG.fillOval(x, y, 10, 10);
		}
		// 포식자 위치 자동 생성
		for (int i = 0; i < predatorSize; i++) {
			int x, y;
			if (runScreen == false) {
				x = (int) (Math.random() * (mapWidth - 40)) + 20;
				y = (int) (Math.random() * (mapHeight - 50)) + 30;
				predators.add(new Predator(x, y));
			}
			predators.get(i).Move(); // 무작위 이동 Move()함수 완성 시 구체화
			x = predators.get(i).getX();
			y = predators.get(i).getY();
			
			for (int j = 0; j < preys.size(); j++) {
						distance = (double) (Math.pow((predators.get(i).getX() - preys.get(j).getX()),2)
										   + Math.pow((predators.get(i).getY() - preys.get(j).getY()),2));
				distanceFromSafe = (double) (Math.pow((safeZoneX - preys.get(j).getX()),2)
										   + Math.pow((safeZoneY - preys.get(j).getY()),2));
						distance = Math.sqrt(distance);
				distanceFromSafe = Math.sqrt(distanceFromSafe);
				// �븞�쟾援ъ뿭 �븞�뿉 媛쒖껜媛� 議댁옱�븯�뒗 寃쎌슦
				if ((safeZoneRadius - preys.get(j).getRadius()) <= distanceFromSafe) {
					continue;
				}
				if (distance <= predators.get(i).getRadius() + preys.get(j).getRadius()) {
					preys.remove(j);
				}
			}
			
			buffG.setColor(Color.RED);
			buffG.fillOval(x, y, 10, 10);
		}

		runScreen = true;

		g.drawImage(buffImg, 0, 0, this);
		super.repaint();
	}
}
