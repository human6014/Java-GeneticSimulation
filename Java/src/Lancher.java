import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Lancher extends JFrame {
	Lancher() {
		setTitle("Lancher");
		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		Container c = getContentPane();
		c.setLayout(null);
		JButton bt1 = new JButton("º±≈√");
		bt1.addActionListener(new LancherActionListener());
		bt1.setSize(75, 50);
		bt1.setLocation(300, 450);

		c.add(bt1);
	}

	public static void main(String[] args) {
		new Lancher();
	}

	private class LancherActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Display();
		}
	}
}
