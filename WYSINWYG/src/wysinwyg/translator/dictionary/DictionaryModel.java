/*******************************************************************************
 * Copyright (c) 2015 Balázs Felföldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balázs Felföldi - initial API and implementation
 ******************************************************************************/
package wysinwyg.translator.dictionary;

import wysinwyg.translator.Translator;

public class DictionaryModel {

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
