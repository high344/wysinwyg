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
package wysinwyg.fb;

public class WysinwygException extends Exception {

	private static final long serialVersionUID = 2906117429772028951L;

	public WysinwygException() {
		super();
	}

	public WysinwygException(String message) {
		super(message);
	}

	public WysinwygException(String message, Throwable cause) {
		super(message, cause);
	}

	public WysinwygException(Throwable cause) {
		super(cause);
	}

}
