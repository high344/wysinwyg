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
package wysinwyg.evaluator;

import java.util.EventObject;

public class EvaluationEvent extends EventObject {

	private static final long serialVersionUID = -7561597978391111059L;

	private String result;

	public EvaluationEvent(Object source, String result) {
		super(source);
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "EvaluationEvent [result=" + result + "]";
	}

}
