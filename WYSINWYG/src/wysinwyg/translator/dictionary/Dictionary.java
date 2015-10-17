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

import java.util.Map;

public interface Dictionary {

	public String getPath();

	public void loadDictionary();

	public void saveDictionary();

	public boolean addTranslation(String strokes, String translation);

	public boolean changeTranslation(String strokes, String translation);

	public boolean removeTranslation(String strokes);

	public Map<String, String> getTranslation(String strokes);

}
