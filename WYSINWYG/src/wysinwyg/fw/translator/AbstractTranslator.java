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

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTranslator implements Translator, TranslationListener {

	protected List<TranslationListener> list = new ArrayList<TranslationListener>(10);
	
	@Override
	public void addTranslationListener(TranslationListener transListener) {
		list.add(transListener);
	}

	@Override
	public void removeTranslationListener(TranslationListener transListener) {
		list.remove(transListener);
	}

	@Override
	public void translationEventOccurred(TranslationEvent e) {
		for (TranslationListener trans : list) {
			trans.translationEventOccurred(e);
		}
	}

}
