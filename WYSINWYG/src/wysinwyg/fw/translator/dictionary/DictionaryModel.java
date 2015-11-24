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
package wysinwyg.fw.translator.dictionary;

import wysinwyg.fw.Model;
import wysinwyg.fw.translator.Translator;

public class DictionaryModel implements Model {

	private Translator translator;
	private DictionaryTableModel dtm;

	public DictionaryModel(Translator translator) {
		this.translator = translator;
		this.dtm = new DictionaryTableModel(this);
	}

	public Translator getTranslator() {
		return translator;
	}

	public void setTranslator(Translator translator) {
		this.translator = translator;
		dtm.fireTableStructureChanged();
	}

	public DictionaryTableModel getDictionaryTableModel() {
		return dtm;
	}

}
