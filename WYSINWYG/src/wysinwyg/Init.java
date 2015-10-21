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

import javax.swing.JComponent;

/**
 * The initializer interface of a possible MVC framework.
 * 
 * @author FelfoldiB.
 *
 */
public interface Init {

	/**
	 * The View object of the MVC framework.
	 * 
	 * @return a JComponent class.
	 * @see javax.swing.JComponent
	 */
	public JComponent getView();

	/**
	 * The Controller object of the MVC framework.
	 * 
	 * @return a Controller interface.
	 * @see Controller
	 */
	public Controller getController();

}
