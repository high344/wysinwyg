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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import wysinwyg.fw.device.Device;
import wysinwyg.fw.device.DeviceEvent;
import wysinwyg.fw.device.DeviceListener;
import wysinwyg.fw.device.serial.SerialBuilder;

public class StenturaDevice implements Device, DeviceListener {

	public static void main(String[] args) {
		byte[] b = new byte[] { 82, 69, 65, 76, 84, 73, 77, 69, 46, 48, 48, 48, };
		System.out.println(Arrays.toString(b));
		System.out.println(new StenturaDevice().CRC(b));
		byte[] c = new byte[] { 73, -14 };

		System.out.println(Short.toUnsignedInt(ByteBuffer.wrap(c).order(ByteOrder.LITTLE_ENDIAN).getShort()));
	}

	private StenturaSerialComm comm;
	private StenturaView panel;
	private List<DeviceListener> list;
	private Thread serialPortListenerThread;

	public StenturaDevice() {
		try {
			RXTXLibraryLoader.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		comm = new StenturaSerialComm();
		panel = new StenturaView();
		list = new ArrayList<DeviceListener>(10);
		comm.setSerialController(new SerialBuilder().setSerialModel(comm).setSerialView(panel.getSerialView()).build());
		serialPortListenerThread = new Thread(createSerialPortListenerThread(), "serialPortListenerThread");
	}

	@Override
	public JPanel getView() {
		return panel;
	}

	@Override
	public String getDisplayName() {
		return "Stentura";
	}

	@Override
	public void addDeviceListener(DeviceListener devListener) {
		list.add(devListener);
	}

	@Override
	public void removeDeviceListener(DeviceListener devListener) {
		list.remove(devListener);
	}

	@Override
	public void startDevice() {
		serialPortListenerThread.start();
	}

	@Override
	public void stopDevice() {
		serialPortListenerThread.interrupt();
	}

	@Override
	public void deviceEventOccurred(DeviceEvent e) {
		for (DeviceListener d : list) {
			d.deviceEventOccurred(e);
		}
	}
	
	private Runnable createSerialPortListenerThread() {
		return new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
			}
		};
	}

	/**
	 * Compute the Crc algorithm used by the stentura protocol. This algorithm is described by the Rocksoft^TM Model CRC
	 * Algorithm as follows:
	 * 
	 * <pre>
	 * Name : "CRC-16"
	 * Width : 16
	 * Poly : 8005
	 * Init : 0000
	 * RefIn : True
	 * RefOut : True
	 * XorOut : 0000
	 * Check : BB3D
	 * </pre>
	 * 
	 * @param data
	 *            The data to checksum. The data should be an iterable that returns bytes.
	 * @return The computed crc for the data.
	 */
	private int CRC(byte[] data) {
		int checksum = 0;
		for (byte b : data) {
			checksum = (CRC_TABLE[(checksum ^ b) & 0xff] ^ ((checksum >> 8) & 0xff));
		}
		return checksum;
	}

	/*
	 * Helper table for parsing strokes of the form: 11^#STKP 11WHRAO* 11EUFRPB 11LGTSDZ _STENO_KEY_CHART = ('^', '#',
	 * 'S-', 'T-', 'K-', 'P-', # Byte #1 'W-', 'H-', 'R-', 'A-', 'O-', '*', # Byte #2 '-E', '-U', '-F', '-R', '-P',
	 * '-B', # Byte #3 '-L', '-G', '-T', '-S', '-D', '-Z') # Byte #4
	 */
	// Byte 1
	public static final int sm = 0b11100000;
	public static final int hm = 0b11010000;
	public static final int s_ = 0b11001000;
	public static final int t_ = 0b11000100;
	public static final int k_ = 0b11000010;
	public static final int p_ = 0b11000001;

	// Byte 2
	public static final int w_ = 0b11100000;
	public static final int h_ = 0b11010000;
	public static final int r_ = 0b11001000;
	public static final int a_ = 0b11000100;
	public static final int o_ = 0b11000010;
	public static final int st = 0b11000001;

	// Byte 3
	public static final int _e = 0b11100000;
	public static final int _u = 0b11010000;
	public static final int _f = 0b11001000;
	public static final int _r = 0b11000100;
	public static final int _p = 0b11000010;
	public static final int _b = 0b11000001;

	// Byte 4
	public static final int _l = 0b11100000;
	public static final int _g = 0b11010000;
	public static final int _t = 0b11001000;
	public static final int _s = 0b11000100;
	public static final int _d = 0b11000010;
	public static final int _z = 0b11000001;

	public static final int Null = 0b11000000;

	private int[] CRC_TABLE = { 0x0000, 0xc0c1, 0xc181, 0x0140, 0xc301, 0x03c0, 0x0280, 0xc241, 0xc601, 0x06c0, 0x0780,
			0xc741, 0x0500, 0xc5c1, 0xc481, 0x0440, 0xcc01, 0x0cc0, 0x0d80, 0xcd41, 0x0f00, 0xcfc1, 0xce81, 0x0e40,
			0x0a00, 0xcac1, 0xcb81, 0x0b40, 0xc901, 0x09c0, 0x0880, 0xc841, 0xd801, 0x18c0, 0x1980, 0xd941, 0x1b00,
			0xdbc1, 0xda81, 0x1a40, 0x1e00, 0xdec1, 0xdf81, 0x1f40, 0xdd01, 0x1dc0, 0x1c80, 0xdc41, 0x1400, 0xd4c1,
			0xd581, 0x1540, 0xd701, 0x17c0, 0x1680, 0xd641, 0xd201, 0x12c0, 0x1380, 0xd341, 0x1100, 0xd1c1, 0xd081,
			0x1040, 0xf001, 0x30c0, 0x3180, 0xf141, 0x3300, 0xf3c1, 0xf281, 0x3240, 0x3600, 0xf6c1, 0xf781, 0x3740,
			0xf501, 0x35c0, 0x3480, 0xf441, 0x3c00, 0xfcc1, 0xfd81, 0x3d40, 0xff01, 0x3fc0, 0x3e80, 0xfe41, 0xfa01,
			0x3ac0, 0x3b80, 0xfb41, 0x3900, 0xf9c1, 0xf881, 0x3840, 0x2800, 0xe8c1, 0xe981, 0x2940, 0xeb01, 0x2bc0,
			0x2a80, 0xea41, 0xee01, 0x2ec0, 0x2f80, 0xef41, 0x2d00, 0xedc1, 0xec81, 0x2c40, 0xe401, 0x24c0, 0x2580,
			0xe541, 0x2700, 0xe7c1, 0xe681, 0x2640, 0x2200, 0xe2c1, 0xe381, 0x2340, 0xe101, 0x21c0, 0x2080, 0xe041,
			0xa001, 0x60c0, 0x6180, 0xa141, 0x6300, 0xa3c1, 0xa281, 0x6240, 0x6600, 0xa6c1, 0xa781, 0x6740, 0xa501,
			0x65c0, 0x6480, 0xa441, 0x6c00, 0xacc1, 0xad81, 0x6d40, 0xaf01, 0x6fc0, 0x6e80, 0xae41, 0xaa01, 0x6ac0,
			0x6b80, 0xab41, 0x6900, 0xa9c1, 0xa881, 0x6840, 0x7800, 0xb8c1, 0xb981, 0x7940, 0xbb01, 0x7bc0, 0x7a80,
			0xba41, 0xbe01, 0x7ec0, 0x7f80, 0xbf41, 0x7d00, 0xbdc1, 0xbc81, 0x7c40, 0xb401, 0x74c0, 0x7580, 0xb541,
			0x7700, 0xb7c1, 0xb681, 0x7640, 0x7200, 0xb2c1, 0xb381, 0x7340, 0xb101, 0x71c0, 0x7080, 0xb041, 0x5000,
			0x90c1, 0x9181, 0x5140, 0x9301, 0x53c0, 0x5280, 0x9241, 0x9601, 0x56c0, 0x5780, 0x9741, 0x5500, 0x95c1,
			0x9481, 0x5440, 0x9c01, 0x5cc0, 0x5d80, 0x9d41, 0x5f00, 0x9fc1, 0x9e81, 0x5e40, 0x5a00, 0x9ac1, 0x9b81,
			0x5b40, 0x9901, 0x59c0, 0x5880, 0x9841, 0x8801, 0x48c0, 0x4980, 0x8941, 0x4b00, 0x8bc1, 0x8a81, 0x4a40,
			0x4e00, 0x8ec1, 0x8f81, 0x4f40, 0x8d01, 0x4dc0, 0x4c80, 0x8c41, 0x4400, 0x84c1, 0x8581, 0x4540, 0x8701,
			0x47c0, 0x4680, 0x8641, 0x8201, 0x42c0, 0x4380, 0x8341, 0x4100, 0x81c1, 0x8081, 0x4040 };



}
