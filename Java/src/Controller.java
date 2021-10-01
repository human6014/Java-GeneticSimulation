import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class Controller extends JFrame {
	private Simulation simulation;
	private final int mapWidth = 1280;
	private final int mapHeight = 820;

	public static int Acceleration = 1;
	public static double mutationRate = 50;

	private double[] geneAverage = new double[3];
	private boolean stop = false;

	private JFrame controller = new JFrame();
	private JPanel masterpanel = new JPanel();
	private JTabbedPane tab;
	private JPanel panel1,panel2,panel3;
	private JSlider slider1, slider2;
	private JTextField textfield1, textfield2;
	private JButton bt1, bt2, bt3;
	private JLabel label5, label6;
	private JLabel geneLabel1, geneLabel2, geneLabel3;

	Controller(Simulation simulation) {
		this.simulation = simulation;
		simulation.call(this);
		controller.setTitle("Controller");
		controller.setSize(384, mapHeight);
		controller.setLocation(mapWidth - 10, 0);
		controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller.setVisible(true);
		controller.setResizable(false);

		masterpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		tab = new JTabbedPane();
		tab.setPreferredSize(new Dimension(370, 720));

		CreateMasterPanel();
		CreateAboutPanel();
		CreateControllerPanel();
		CreateGeneticPanel();

		masterpanel.add(tab);
		masterpanel.add(bt2);
		masterpanel.add(bt1);
		masterpanel.add(bt3);
		controller.add(masterpanel);
	}

	void CreateMasterPanel() {
		ImageIcon img1 = new ImageIcon("Start.png");
		ImageIcon img2 = new ImageIcon("Pause.png");
		ImageIcon img3 = new ImageIcon("Restart.png");
		ImageIcon displayImage1 = new ImageIcon(img1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon displayImage2 = new ImageIcon(img2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon displayImage3 = new ImageIcon(img3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

		bt1 = new JButton(displayImage1);
		bt2 = new JButton(displayImage2);
		bt3 = new JButton(displayImage3);
		bt1.addActionListener(new bt1ActionListener());
		bt2.addActionListener(new bt2ActionListener());
		bt3.addActionListener(new bt3ActionListener());
		bt1.setBorderPainted(false);
		bt2.setBorderPainted(false);
		bt3.setBorderPainted(false);
		bt1.setContentAreaFilled(false);
		bt2.setContentAreaFilled(false);
		bt3.setContentAreaFilled(false);
	}

	void CreateAboutPanel() {
		panel1 = new JPanel();
		ImageIcon aboutImage = new ImageIcon(
				new ImageIcon("Genetic.png").getImage().getScaledInstance(mapWidth - 20, 720, Image.SCALE_SMOOTH));

		JLabel image = new JLabel(aboutImage);
		JScrollPane scrollbar = new JScrollPane(image);

		/*
		 * JTextArea textarea = new JTextArea("text", 19, 14);
		 * textarea.setPreferredSize(new Dimension(364, 690)); textarea.setFont(new
		 * Font(null, Font.BOLD, 20)); textarea.setEditable(false);
		 */

		panel1.add(scrollbar);
		tab.add("  About  ", panel1);
	}

	void CreateControllerPanel() {
		panel2 = new JPanel();
		panel2.setLayout(null);

		JLabel label1 = new JLabel("Acceleration:");
		label1.setFont(new Font(null, Font.HANGING_BASELINE, 13));
		label1.setBounds(10, 50, 150, 100);

		JLabel label2 = new JLabel("Number of preys:");
		label2.setFont(new Font(null, Font.HANGING_BASELINE, 13));
		label2.setBounds(10, 150, 150, 100);

		JLabel label3 = new JLabel("Number of predators:");
		label3.setFont(new Font(null, Font.HANGING_BASELINE, 13));
		label3.setBounds(10, 250, 150, 100);

		JLabel label4 = new JLabel("Mutation rate:");
		label4.setFont(new Font(null, Font.HANGING_BASELINE, 13));
		label4.setBounds(10, 350, 150, 100);

		slider1 = new JSlider(JSlider.HORIZONTAL, 1, 50, 1);
		slider1.addChangeListener(new Slider1ChangeListener());
		slider1.setBounds(150, 75, 200, 50);
		label5 = new JLabel("X1");
		label5.setBounds(230, 90, 50, 50);

		textfield1 = new JTextField("50");
		textfield1.setHorizontalAlignment(JTextField.CENTER);
		textfield1.setBounds(150, 190, 150, 20);

		textfield2 = new JTextField("4");
		textfield2.setHorizontalAlignment(JTextField.CENTER);
		textfield2.setBounds(150, 290, 150, 20);

		label6 = new JLabel("50%");
		label6.setBounds(230, 390, 100, 50);
		slider2 = new JSlider(JSlider.HORIZONTAL, 1, 50000, 50000);
		slider2.addChangeListener(new Slider2ChangeListener());
		slider2.setBounds(150, 375, 200, 50);

		panel2.add(label1);
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		panel2.add(label5);
		panel2.add(label6);
		panel2.add(textfield1);
		panel2.add(textfield2);
		panel2.add(slider1);
		panel2.add(slider2);

		tab.add("  Controller  ", panel2);
	}

	void CreateGeneticPanel() {
		panel3 = new JPanel();
		panel3.setLayout(null);
		
		geneLabel1 = new JLabel("asdfasdfa");
		geneLabel1.setLocation(50, 100);
		geneLabel1.setSize(200, 50);
		geneLabel2 = new JLabel("asdfasdfasdf");
		geneLabel2.setLocation(50, 250);
		geneLabel2.setSize(200, 50);
		geneLabel3 = new JLabel("asdfasdfasdf");
		geneLabel3.setLocation(50, 400);
		geneLabel3.setSize(200, 50);
		panel3.add(geneLabel1);
		panel3.add(geneLabel2);
		panel3.add(geneLabel3);
		
		tab.add("  Gene  ", panel3);
	}

	void averPrint(double radiusAver, double speedAver, double activityAver) {
		geneLabel1.setText("AverageRadius : "+Double.toString(radiusAver));
		geneLabel2.setText("AverageSpeed : "+Double.toString(speedAver));
		geneLabel3.setText("AverageActivity : "+Double.toString(activityAver));
	}

	private class Slider1ChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			label5.setText("X" + slider1.getValue());
			if (!stop)
				Acceleration = slider1.getValue();
		}
	}

	private class Slider2ChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			label6.setText(Double.toString(((double) slider2.getValue()) / 1000) + "%");
			mutationRate = ((double) slider2.getValue()) / 1000;
		}
	}

	private class bt1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stop = false;
			Acceleration = slider1.getValue();
		}
	}

	private class bt2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stop = true;
			Acceleration = 0;
		}
	}

	private class bt3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int text1, text2;
			text1 = Integer.parseInt(textfield1.getText());
			text2 = Integer.parseInt(textfield2.getText());
			simulation.reset(text1, text2);
		}
	}
}
