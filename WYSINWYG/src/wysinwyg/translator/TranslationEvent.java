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
package wysinwyg.translator;

import java.util.EventObject;

public class TranslationEvent extends EventObject {

	private static final long serialVersionUID = -4815494823174358849L;

	private String stroke;
	private String translation;

	public TranslationEvent(Object source, String stroke, String translation) {
		super(source);
		this.stroke = stroke;
		this.translation = translation;
	}

	public String getStroke() {
		return stroke;
	}

	public String getTranslation() {
		return translation;
	}

	@Override
	public String toString() {
		return "TranslationEvent [strokeCount=" + stroke + ", translation=" + translation + "]";
	}

}
