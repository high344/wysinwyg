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
package wysinwyg.device.keyboard;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

public class KeyboardView extends JPanel {

	private static final long serialVersionUID = 459644234125637827L;

	private JCheckBox chckbxEcho;

	public KeyboardView() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		chckbxEcho = new JCheckBox("Echo");
		add(chckbxEcho);
	}

	public JCheckBox getChckbxEcho() {
		return chckbxEcho;
	}

}
