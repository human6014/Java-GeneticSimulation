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

		tab.add("설명",textarea);		
	}
	void CreatePanel() {
		panel1=new JPanel();
		panel1.setLayout(null);
		
		JLabel label1=new JLabel("           가속 :");
		label1.setBounds(20, 50, 80, 50);
		
		JLabel label2=new JLabel("      먹이 수 :");
		label2.setBounds(20, 150, 80, 50);
		
		JLabel label3=new JLabel("  포식자 수 :");
		label3.setBounds(20, 250, 80, 50);
		
		JLabel label4=new JLabel("돌연변이율 :");
		label4.setBounds(20, 350, 80, 50);
		
		JLabel label5=new JLabel("        변이율 :");
		label5.setBounds(20, 450, 80, 50);
		
		JSlider slider1=new JSlider(JSlider.HORIZONTAL,1,26,1);
		slider1.setMinorTickSpacing(1);
		slider1.setMajorTickSpacing(5);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setBounds(100, 50, 200, 50);
		
		JTextField textfield1=new JTextField("50");
		textfield1.setHorizontalAlignment(JTextField.CENTER);
		textfield1.setBounds(150, 165, 100, 25);
		
		JTextField textfield2=new JTextField("4");
		textfield2.setHorizontalAlignment(JTextField.CENTER);
		textfield2.setBounds(150, 265, 100, 25);
		
		JSlider slider2=new JSlider(JSlider.HORIZONTAL,1,11,1);
		slider2.setMinorTickSpacing(1);
		slider2.setMajorTickSpacing(5);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		slider2.setBounds(100, 350, 200, 50);
		
		JSlider slider3=new JSlider(JSlider.HORIZONTAL,1,11,1);
		slider3.setMinorTickSpacing(1);
		slider3.setMajorTickSpacing(5);
		slider3.setPaintTicks(true);
		slider3.setPaintLabels(true);
		slider3.setBounds(100, 450, 200, 50);
		
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
		
		tab.add("설정",panel1);
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
