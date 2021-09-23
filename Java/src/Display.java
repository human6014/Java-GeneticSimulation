//this
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Display extends JFrame {

	Image buffImg;
	Graphics buffG;

	int count=0;
	private int genrationCount;
	private static final int mapWidth = 1280;
	private static final int mapHeight = 820;
	private static final int safeZoneRadius = 100;
	private static final int safeZoneX = mapWidth/2-safeZoneRadius/2;
	private static final int safeZoneY = mapHeight/2-safeZoneRadius/2;
	private int generation = 0;
	private int preySize = 30;
	private int predatorSize = 4;
	private int callMoveCount;
	//private ArrayList<Prey> preys = new ArrayList<>();
	//private ArrayList<Predator> predators = new ArrayList<>();
	//private ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	Prey[] preys;
	Predator[] predators;
	public void start() {
		Timer timer = new Timer(0, (ae) -> repaint());
		timer.setDelay(5);
		timer.start();
	}
	public static void main(String[] args) {
		new Display().start();
	}
	Display() {
		JFrame controller= new JFrame();
		controller.setTitle("Controller");
		controller.setSize(384,mapHeight);
		controller.setLocation(mapWidth-10, 0);
		controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller.setVisible(true);
		controller.setResizable(false);
		
		JTextArea textarea = new JTextArea("�꽕紐�",19,14);
		JTextField textfield = new JTextField("諛곗냽 �엯�젰");
		ImageIcon img1 = new ImageIcon("playbutton_121290.png");
		ImageIcon img2 = new ImageIcon("pause_121328.png");
		ImageIcon displayImage1 = new ImageIcon(img1.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
		ImageIcon displayImage2 = new ImageIcon(img2.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
		JButton bt1=new JButton(displayImage1);
		JButton bt2=new JButton(displayImage2);

		textarea.setSize(300, 500);
		textfield.setSize(120, 30);
		textarea.setLocation(30, 30);
		textfield.setLocation(50, mapHeight-100);
		textarea.setFont(new Font(null,Font.BOLD,20));
		textfield.setFont(new Font(null,Font.BOLD,20));
		textarea.setEditable(false);
		
		bt1.setBorderPainted(false);
		bt2.setBorderPainted(false);
		bt1.setContentAreaFilled(false);
		bt2.setContentAreaFilled(false);	
		bt1.setSize(30, 30);
		bt2.setSize(30,30);	
		bt1.setLocation(300, mapHeight-100);
		bt2.setLocation(250, mapHeight-100);
		
		Container c= getContentPane();
		c.setLayout(null);
		c.add(bt1);
		c.add(bt2);
		c.add(textarea);
		c.add(textfield);
		controller.add(c);
		
		JFrame display= this;
		display.setTitle("GeneticSimulation");
		display.setSize(mapWidth, mapHeight);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setVisible(true);
		display.setResizable(false);
		
		arrangement();
	}
	public void arrangement() {
		genrationCount = 1;
		predators = new Predator[predatorSize];
		preys = new Prey[500];
		double x, y;
		for (int i = 0 ; i < predatorSize; i++) {
			predators[i] = new Predator();
			x = (Math.random() * (mapWidth - 30)) + 10;
			y = (Math.random() * (mapHeight - 60)) + 30;
			predators[i].setX(x);
			predators[i].setY(y);
		}
		for (int j = 0 ; j < preySize; j++) {
			preys[j] = new Prey();
			x = (Math.random() * (mapWidth - 30)) + 10;
			y = (Math.random() * (mapHeight - 60)) + 30;
			preys[j].setX(x);
			preys[j].setY(y);
		}
	}
	@Override
	public void paint(Graphics g) {
		buffImg = createImage(getWidth(), getHeight());
		buffG = buffImg.getGraphics();
		update(g);
	}
	@Override
	public void update(Graphics g) {
        double distance;
		buffG.clearRect(0, 0, mapWidth, mapHeight);
		buffG.setColor(Color.GREEN);
		buffG.fillOval(safeZoneX, safeZoneY, safeZoneRadius, safeZoneRadius);

		double x;
		double y;
		//�솒諭��뵠 1move 占쎌깈�빊占� + 域밸챶�봺疫뀐옙
		for(int i = 0 ; i <preySize; i++)
		{
			if (preys[i] == null) {
				preySize = preySize - 1;
				break;
			}
			preys[i].Move();
			x = preys[i].getX();
			y = preys[i].getY();
			buffG.setColor(Color.BLACK);
			buffG.fillOval((int)x, (int)y, (int)preys[i].getRadius(), (int)preys[i].getRadius());
		}

		//占쎈７占쎈뻼占쎌쁽 1move 占쎌깈�빊占� + 占쎈돗占쎈뻼占쎌쁽 甕곕뜆�뻼 占쎌뜎 域밸챶�봺疫뀐옙
		for (int i = 0; i < predatorSize; i++) {
			predators[i].Move();
			x = predators[i].getX();
			y = predators[i].getY();
			
			for (int j = 0; j < preySize; j++) {
					distance = (double) (Math.pow((predators[i].getX() - preys[j].getX()),2)
										+ Math.pow((predators[i].getY() - preys[j].getY()),2));
					distance = Math.sqrt(distance);

				if (distance <= preys[j].getRadius() + predators[i].getRadius()) { 
						//System.out.println("(i : "+ i +", j : "+ j +")");
						preys[j] = preys[preySize - 1];
						preys[preySize - 1] = null;
						j--;
						preySize--;						
				}
			}
			buffG.setColor(Color.RED);
			buffG.fillOval((int)x, (int)y, 20, 20);
		}
		genrationCount++;
		if(genrationCount % 5000 == 0) {
			int temNum = preySize;
			for (int t = 0; t < temNum ; t++) {
				temNum--;
				Prey tem = preys[t].reproduceBySelf();
				preys[preySize] = tem;
				preySize++;
				buffG.setColor(Color.BLACK);
				buffG.fillOval((int) tem.getX(), (int) tem.getY(), (int)tem.getRadius(), (int)tem.getRadius());
			}
			if(genrationCount == 5000) {
				genrationCount = 0;
			}
		}
		g.drawImage(buffImg,0,0,this);
		super.repaint();
	}
}
