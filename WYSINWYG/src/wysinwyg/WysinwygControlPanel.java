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
package wysinwyg;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

/**
 * A "control" titled JPanel with a "start" JToggleButton.
 * 
 * @author FelfoldiB.
 *
 */
public class WysinwygControlPanel extends JPanel {

	private static final long serialVersionUID = 7398154840465421546L;

	private JToggleButton tglbtnStart;

	public WysinwygControlPanel() {
		setBorder(new TitledBorder(null, "Control", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		tglbtnStart = new JToggleButton("Start");
		add(tglbtnStart);
	}

	public JToggleButton getTglbtnStart() {
		return tglbtnStart;
	}

}
