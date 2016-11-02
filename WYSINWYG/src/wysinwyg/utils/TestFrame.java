package wysinwyg.utils;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFrame {
	
	public TestFrame(JPanel panel) {
		JFrame frame = new JFrame();
		frame.setContentPane(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new TestFrame(new TestQwe());
	}
	
}
