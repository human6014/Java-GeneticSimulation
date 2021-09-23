import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Controller extends JFrame{
	private static final int mapWidth = 1280;
	private static final int mapHeight = 820;
	JFrame controller = new JFrame();
	Controller(){
		controller.setTitle("Controller");
		controller.setSize(384, mapHeight);
		controller.setLocation(mapWidth - 10, 0);
		controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller.setVisible(true);
		controller.setResizable(false);
		
		CreateContents();
	}
	void CreateContents() {
		JMenuBar menu = new JMenuBar();
		controller.setJMenuBar(menu);
		JMenu explanation = new JMenu("설명");
		JMenu settings = new JMenu("설정");
		menu.add(explanation);
		menu.add(settings);
		explanation.addActionListener(new ControllerActionListener());
		settings.addActionListener(new ControllerActionListener());
		
		JTextArea textarea = new JTextArea("�꽕紐�", 19, 14);
		JTextField textfield = new JTextField("諛곗냽 �엯�젰");
		ImageIcon img1 = new ImageIcon("playbutton_121290.png");
		ImageIcon img2 = new ImageIcon("pause_121328.png");
		ImageIcon displayImage1 = new ImageIcon(img1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		ImageIcon displayImage2 = new ImageIcon(img2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		JButton bt1 = new JButton(displayImage1);
		JButton bt2 = new JButton(displayImage2);

		textarea.setSize(300, 500);
		textfield.setSize(120, 30);
		textarea.setLocation(30, 30);
		textfield.setLocation(50, mapHeight - 100);
		textarea.setFont(new Font(null, Font.BOLD, 20));
		textfield.setFont(new Font(null, Font.BOLD, 20));
		textarea.setEditable(false);

		bt1.setBorderPainted(false);
		bt2.setBorderPainted(false);
		bt1.setContentAreaFilled(false);
		bt2.setContentAreaFilled(false);
		bt1.setSize(30, 30);
		bt2.setSize(30, 30);
		bt1.setLocation(300, mapHeight - 100);
		bt2.setLocation(250, mapHeight - 100);

		Container c = getContentPane();
		c.setLayout(null);
		c.add(bt1);
		c.add(bt2);
		c.add(textarea);
		c.add(textfield);
		controller.add(c);
	}
	private class ControllerActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "설명":
				System.out.println("설명");
				break;
				
			case "설정": 
				System.out.println("설정");
				break;
			default:
				System.out.println("디폴트");
				break;
			}
		}
	}
}
