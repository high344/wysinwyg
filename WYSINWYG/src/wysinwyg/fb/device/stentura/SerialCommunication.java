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
package wysinwyg.fb.device.stentura;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SerialCommunication {

	public static void main(String[] args) throws Exception {
		RXTXLibraryLoader.load();
		CommPort commPort = null;
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM1");

			if (portIdentifier.isCurrentlyOwned()) {
				// TODO error!
			} else {
				commPort = portIdentifier.open("TEST", 2000);

				if (commPort instanceof SerialPort) {
					SerialPort serialPort = (SerialPort) commPort;
					serialPort.setSerialPortParams(9600, 8, 1, 0);

					InputStream is = serialPort.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);

					byte[] b = new byte[1024];
					int n = 0;

					while ((n = is.read(b)) != -1) {
						if (n > 0) {
							System.out.println(n);
							System.out.println(Arrays.toString(b));
						}
					}

					// serialPort.getOutputStream().write("test".getBytes());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (commPort != null) {
				commPort.close();
			}
		}
	}
}
