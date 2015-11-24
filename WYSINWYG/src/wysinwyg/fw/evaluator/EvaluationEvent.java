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
package wysinwyg.fw.evaluator;

import java.util.EventObject;

/**
 * The event that is passed on to the registered {@linkplain EvaluationListener
 * device listeners}.
 * 
 * @author FelfoldiB.
 *
 */
public class EvaluationEvent extends EventObject {

	private static final long serialVersionUID = -7561597978391111059L;

	private String result;

	/**
	 * Creating an event, that is passed on to the listeners.
	 * 
	 * @param source
	 *            Where the event is originated from.
	 * @param result
	 *            Representation of one or multiple strokes.
	 */
	public EvaluationEvent(Object source, String result) {
		super(source);
		this.result = result;
	}

	/**
	 * Get the representation of one or multiple strokes.
	 * 
	 * @return a String class.
	 */
	public String getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "EvaluationEvent [result=" + result + "]";
	}

}
