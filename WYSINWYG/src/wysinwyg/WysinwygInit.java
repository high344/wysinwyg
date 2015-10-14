package wysinwyg;

import javax.swing.JFrame;

public class WysinwygInit {

	public WysinwygInit() {
		WysinwygModel model = new WysinwygModel();
		WysinwygView view = new WysinwygView(model);
		new WysinwygController(model, view);

		JFrame frame = new JFrame("WhatYouStrokeIsNotWhatYouGet");
		frame.setContentPane(view);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
