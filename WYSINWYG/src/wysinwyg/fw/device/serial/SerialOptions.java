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
package wysinwyg.fw.device.serial;

public interface SerialOptions {

	public String getCommPortName();

	public Integer getBaudrateValue();

	public Integer getDatabitsValue();

	public String getStopBitsValue();

	public String getParityValue();

	public boolean isChckbxTimeoutSelected();

	public String getTextFieldTimeout();

	public boolean isChckbxRTSCTSSelected();

	public boolean isCchckbxXonXoffSelected();

}
