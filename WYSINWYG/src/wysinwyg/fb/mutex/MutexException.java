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
package wysinwyg.fb.mutex;

public class MutexException extends Exception {

	private static final long serialVersionUID = 8187788255886177840L;

	public MutexException() {
		super();
	}

	public MutexException(String message) {
		super(message);
	}

	public MutexException(String message, Throwable cause) {
		super(message, cause);
	}

	public MutexException(Throwable cause) {
		super(cause);
	}

}