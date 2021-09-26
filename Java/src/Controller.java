import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Controller extends JFrame {
	private static final int mapWidth = 1280;
	private static final int mapHeight = 820;
	public static int Acceleration = 1;
	private boolean Stop = false;
	private JFrame controller = new JFrame();
	private JTextField textfield;
	private JTabbedPane tab;
	private JPanel panel1;
	Controller() {
		controller.setTitle("Controller");
		controller.setSize(384, mapHeight);
		controller.setLocation(mapWidth - 10, 0);
		controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller.setVisible(true);
		controller.setResizable(false);

		CreateMain();
		CreatePanel();
		controller.add(tab);
	}
	void CreateMain() {
		tab = new JTabbedPane();

		JTextArea textarea = new JTextArea("text", 19, 14);
		textarea.setSize(300, 500);
		textarea.setLocation(30, 30);
		textarea.setFont(new Font(null, Font.BOLD, 20));
		textarea.setEditable(false);

		tab.add("�꽕紐�",textarea);		
	}
	void CreatePanel() {
		panel1=new JPanel();
		panel1.setLayout(null);
		
		JLabel label1=new JLabel("            Acceleration:");
		label1.setFont(new Font(null, Font.HANGING_BASELINE, 13));
		label1.setBounds(10, 50, 150, 100);
		
		JLabel label2=new JLabel("      Number of preys:");
		label2.setFont(new Font(null, Font.HANGING_BASELINE, 13));
		label2.setBounds(10, 150, 150, 100);
		
		JLabel label3=new JLabel("Number of predators:");
		label3.setFont(new Font(null, Font.HANGING_BASELINE, 13));
		label3.setBounds(10, 250, 150, 100);
		
		JLabel label4=new JLabel(" 	         Mutation rate:");
		label4.setFont(new Font(null, Font.HANGING_BASELINE, 13));
		label4.setBounds(10, 350, 150, 100);
		
		JLabel label5=new JLabel("            Variant rate:");
		label5.setFont(new Font(null, Font.HANGING_BASELINE, 13));
		label5.setBounds(10, 450, 150, 100);
		
		JSlider slider1=new JSlider(JSlider.HORIZONTAL,1,26,1);
		slider1.setMinorTickSpacing(1);
		slider1.setMajorTickSpacing(5);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setBounds(150, 90, 200, 50);
		
		JTextField textfield1=new JTextField("50");
		textfield1.setHorizontalAlignment(JTextField.CENTER);
		textfield1.setBounds(200, 190, 100, 25);
		
		JTextField textfield2=new JTextField("4");
		textfield2.setHorizontalAlignment(JTextField.CENTER);
		textfield2.setBounds(200, 290, 100, 25);
		
		JSlider slider2=new JSlider(JSlider.HORIZONTAL,1,11,1);
		slider2.setMinorTickSpacing(1);
		slider2.setMajorTickSpacing(5);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		slider2.setBounds(150, 390, 200, 50);
		
		JSlider slider3=new JSlider(JSlider.HORIZONTAL,1,11,1);
		slider3.setMinorTickSpacing(1);
		slider3.setMajorTickSpacing(5);
		slider3.setPaintTicks(true);
		slider3.setPaintLabels(true);
		slider3.setBounds(150, 490, 200, 50);
		
		ImageIcon img1 = new ImageIcon("playbutton_121290.png");
		ImageIcon img2 = new ImageIcon("pause_121328.png");
		ImageIcon displayImage1 = new ImageIcon(img1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon displayImage2 = new ImageIcon(img2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JButton bt1 = new JButton(displayImage1);
		bt1.addActionListener(new bt1ActionListener());
		JButton bt2 = new JButton(displayImage2);
		bt2.addActionListener(new bt2ActionListener());
		
		textfield = new JTextField("1");
		textfield.setSize(120, 30);
		textfield.setLocation(50, mapHeight - 100);
		textfield.setFont(new Font(null, Font.BOLD, 20));
		
		bt1.setBorderPainted(false);
		bt2.setBorderPainted(false);
		bt1.setContentAreaFilled(false);
		bt2.setContentAreaFilled(false);
		bt1.setSize(30, 30);
		bt2.setSize(30, 30);
		bt1.setLocation(300, mapHeight - 100);
		bt2.setLocation(250, mapHeight - 100);
		
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		panel1.add(label4);
		panel1.add(label5);
		panel1.add(textfield1);
		panel1.add(textfield2);
		panel1.add(slider1);
		panel1.add(slider2);
		panel1.add(slider3);
		panel1.add(bt1);
		panel1.add(bt2);
		panel1.add(textfield);
		
		tab.add("�꽕�젙",panel1);
	}
	private class bt1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Acceleration = Integer.parseInt(textfield.getText());
		}
	}

	private class bt2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Stop == false) Acceleration = 0;
			else Acceleration = Integer.parseInt(textfield.getText());
			Stop = !Stop;
		}
	}
}
