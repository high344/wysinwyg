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
package wysinwyg.evaluator.steno;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class StenoView extends JPanel {

	private static final long serialVersionUID = -3028516136522939678L;

	private JCheckBox chckbxArpeggiate;
	private ExtStenturaLayout stentura;

	public StenoView() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel_1.add(panel);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		chckbxArpeggiate = new JCheckBox("Arpeggiate");
		panel.add(chckbxArpeggiate);
		stentura = new ExtStenturaLayout();
		panel_1.add(stentura);
	}

	public JCheckBox getChckbxArpeggiate() {
		return chckbxArpeggiate;
	}

	public ExtStenturaLayout getStentura() {
		return stentura;
	}

}
