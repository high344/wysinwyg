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
package wysinwyg.fb;

import javax.swing.JComponent;

import wysinwyg.fw.Init;
import wysinwyg.fw.Model;

/**
 * Initializer for the What You Stroke Is Not What You Get (WYSINWYG).
 * 
 * @author FelfoldiB.
 *
 */
public class WysinwygInit implements Init {

	private WysinwygModel model;
	private WysinwygView view;
	private WysinwygController controller;

	/**
	 * Setting up an MVC for the WYSINWYG.
	 */
	public WysinwygInit() {
		model = new WysinwygModel();
		view = new WysinwygView(model);
		controller = new WysinwygController(model, view);
	}

	@Override
	public Model getModel() {
		return model;
	}
	
	@Override
	public JComponent getView() {
		return view;
	}

	@Override
	public WysinwygController getController() {
		return controller;
	}

	

}
