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
package wysinwyg.utils;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberDocumentFilter extends DocumentFilter {

	private NumberFormat nf = NumberFormat.getInstance();

	private boolean testNumber(String str) {
		try {
			nf.parse(str);
		} catch (ParseException e) {
			if (str.equals(".")) {
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
		if (!testNumber(string)) {
			return;
		}
		super.insertString(fb, offset, string, attr);
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		if (!testNumber(text)) {
			return;
		}
		super.replace(fb, offset, length, text, attrs);
	}

}
