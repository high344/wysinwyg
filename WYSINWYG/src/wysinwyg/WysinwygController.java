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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Objects;

import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

public class WysinwygController implements Controller, ActionListener {

	private WysinwygModel model;
	private JToggleButton tglbtnStart;

	public WysinwygController(WysinwygModel model, WysinwygView view) {
		Objects.requireNonNull(model);
		Objects.requireNonNull(view);
		this.model = model;

		tglbtnStart = view.getControlPanel().getTglbtnStart();
		tglbtnStart.getInputMap(JComponent.WHEN_FOCUSED).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");

		tglbtnStart.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tglbtnStart) {
			if (tglbtnStart.isSelected()) {
				model.start();
			} else {
				model.stop();
			}
		}
	}

}
