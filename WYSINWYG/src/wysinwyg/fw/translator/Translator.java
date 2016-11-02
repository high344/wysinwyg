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

import wysinwyg.fw.Viewable;
import wysinwyg.utils.renderer.ComboboxDisplayName;

public interface Translator extends ComboboxDisplayName, Viewable {

	public void translate(String translate);

	public void addTranslationListener(TranslationListener transListener);

	public void removeTranslationListener(TranslationListener transListener);

	public void startTranslation();

	public void stopTranslation();

}
