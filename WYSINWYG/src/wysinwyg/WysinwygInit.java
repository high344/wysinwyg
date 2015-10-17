/*******************************************************************************
 * Copyright (c) 2015 Balázs Felföldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balázs Felföldi - initial API and implementation
 ******************************************************************************/
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
