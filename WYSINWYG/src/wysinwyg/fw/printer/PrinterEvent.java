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
package wysinwyg.fw.printer;

public class PrinterEvent {

	private String print;
	private int command;

	public PrinterEvent(String printedString) {
		this.print = printedString;
	}

	public PrinterEvent(int commandValue) {
		this.command = commandValue;
	}

	public String getPrint() {
		return print;
	}

	public Integer getCommand() {
		return command;
	}

	public boolean isCommand() {
		return (print == null);
	}

}
