/*******************************************************************************
 * Copyright (c) 2015 Balazs Felfoldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balazs Felfoldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.utils;

import java.awt.GraphicsEnvironment;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorMessage {

	public static void main(String[] args) {
		ErrorMessage.show(null, true);
	}
	
	public static void show(Exception e, boolean debug) {
		String msg = null;
		if (e == null) {
			e = new NullPointerException("Exception for the ErrorMessage is null!");
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
