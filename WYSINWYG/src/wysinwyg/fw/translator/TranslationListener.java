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
package wysinwyg.fw.translator;

/**
 * The listener interface for receiving translation events. The class that is
 * interested in processing a {@linkplain TranslationEvent} implements this
 * interface.
 * 
 * @author FelfoldiB.
 *
 */
public interface TranslationListener {

	/**
	 * Invoked when a translation event occurs.
	 */
	public void translationEventOccurred(TranslationEvent e);

}
