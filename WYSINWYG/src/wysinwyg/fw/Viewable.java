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
package wysinwyg.fw;

import javax.swing.JPanel;

/**
 * The specified class implementing this interface contains a graphical component on a {@linkplain JPanel}.
 * 
 * @author FelfoldiB.
 */
public interface Viewable {

	/**
	 * 
	 * @return The graphical component.
	 * @see {@linkplain Viewable}.
	 */
	public JPanel getView();

}
