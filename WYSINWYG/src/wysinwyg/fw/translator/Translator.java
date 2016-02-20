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

import java.io.File;

import wysinwyg.fw.evaluator.EvaluationListener;
import wysinwyg.fw.translator.dictionary.Dictionary;
import wysinwyg.utils.FileFilter;
import wysinwyg.utils.renderer.ComboboxDisplayName;

public interface Translator extends ComboboxDisplayName, EvaluationListener {

	public boolean addDictionary(File file);

	public boolean removeDictionary(File file);

	public boolean removeDictionary(int index);

	public boolean changeDictionaryOrders(int a, int b);

	public int getDictionaryCount();

	public Dictionary getDictionary(int index);

	public FileFilter getDictionaryFileFilter();

	public boolean loadUpDictionary(int index);

	public void loadUpAllDictionary();

	public void addTranslationListener(TranslationListener transListener);

	public void removeTranslationListener(TranslationListener transListener);

	public void startTranslation();

	public void stopTranslation();

}
