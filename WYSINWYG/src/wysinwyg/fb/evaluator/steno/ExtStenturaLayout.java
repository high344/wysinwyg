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
package wysinwyg.fb.evaluator.steno;

import java.awt.event.MouseListener;

import javax.swing.JToggleButton;

import wysinwyg.utils.StenturaLayout;

public class ExtStenturaLayout extends StenturaLayout {

	private static final long serialVersionUID = -3747293377727146545L;

	public JToggleButton getJToggleButton(String actionCommand) {
		return map.get(actionCommand);
	}

	public void setAllJToggleButtonSelected(boolean b) {
		for (JToggleButton gt : map.values()) {
			gt.setSelected(b);
		}
	}

	public void removeAllJToggleButtonMouseListener() {
		for (JToggleButton gt : map.values()) {
			for (MouseListener ml : gt.getMouseListeners()) {
				gt.removeMouseListener(ml);
			}
		}
	}

}
