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
package wysinwyg.fb.translator.plover;

public class Element {

	private String stroke;
	private String translation;

	/*
	 * private int suffixType; private String printedString; private String
	 * removedString;
	 */

	public Element(String stroke, String translation) {
		this.stroke = stroke;
		this.translation = translation;
		/*
		 * this.suffixType = suffixType; this.printedString = printedString;
		 * this.removedString = removedString;
		 */
	}

	public String getStroke() {
		return stroke;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getTranslation() {
		return translation;
	}

	/*
	 * public void setSuffixType(int suffixType) { this.suffixType = suffixType;
	 * }
	 * 
	 * public int getSuffixType() { return suffixType; }
	 * 
	 * public void setPrintedString(String printedString) { this.printedString =
	 * printedString; }
	 * 
	 * public String getPrintedString() { return printedString; }
	 * 
	 * public void setRemovedString(String removedString) { this.removedString =
	 * removedString; }
	 * 
	 * public String getRemovedString() { return removedString; }
	 */

}
