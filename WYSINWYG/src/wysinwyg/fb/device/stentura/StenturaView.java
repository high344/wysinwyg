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
package wysinwyg.fb.device.stentura;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import wysinwyg.fw.device.serial.SerialView;
import wysinwyg.utils.StenturaLayout;

public class StenturaView extends JPanel {

	private static final long serialVersionUID = -3816282299811234417L;

	private StenturaLayout stenturaLayout;
	private SerialView serialView;

	public StenturaView() {
		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Layout", null, panel_2, null);

		stenturaLayout = new StenturaLayout();
		panel_2.add(stenturaLayout);

		serialView = new SerialView();
		tabbedPane.addTab("Serial Communication", null, serialView, null);
	}

	public StenturaLayout getStenturaLayout() {
		return stenturaLayout;
	}

	public SerialView getSerialView() {
		return serialView;
	}

}
