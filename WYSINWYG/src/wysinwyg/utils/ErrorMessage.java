package wysinwyg.utils;

import java.awt.GraphicsEnvironment;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorMessage {

	public static void show(Exception e, boolean debug) {
		String msg = null;
		if (e == null) {
			e = new NullPointerException();
		}

		if (debug) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream s = new PrintStream(out);
			e.printStackTrace(s);
			msg = out.toString();
		} else {
			msg = e.getMessage();
		}

		if (GraphicsEnvironment.isHeadless()) {
			System.err.println(msg);
		} else {
			JFrame frame = new JFrame("WhatYouStrokeIsNotWhatYouGet");
			frame.setUndecorated(true);
			frame.setVisible(true);
			frame.setLocationRelativeTo(null);
			JOptionPane.showMessageDialog(null, msg, "WhatYouStrokeIsNotWhatYouGet", JOptionPane.ERROR_MESSAGE);
			frame.dispose();
		}
	}

	private ErrorMessage() {

	}

}
