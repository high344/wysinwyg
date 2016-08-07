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
package wysinwyg.fb.device.keyboard;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class KeyboardView extends JPanel {

	private static final long serialVersionUID = 459644234125637827L;

	private JCheckBox chckbxEcho;
	private JCheckBox chckbxArpeggiate;

	public KeyboardView() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		chckbxEcho = new JCheckBox("Echo");
		chckbxArpeggiate = new JCheckBox("Arpeggiate");
		add(chckbxEcho);
		add(chckbxArpeggiate);
	}

	public JCheckBox getChckbxEcho() {
		return chckbxEcho;
	}

	public JCheckBox getChckbxArpeggiate() {
		return chckbxArpeggiate;
	}

}
