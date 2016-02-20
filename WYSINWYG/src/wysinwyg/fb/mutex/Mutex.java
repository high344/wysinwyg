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

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import wysinwyg.fb.WysinwygPath;
import wysinwyg.utils.PropertiesUtils;

public class Mutex {

	private static final File DEFAULT_MUTEX_file;

	static {
		DEFAULT_MUTEX_file = new File(WysinwygPath.getHome() + File.separator + "mutex.properties");
	}

	private static Mutex mutex;

	public static synchronized void block() throws MutexException {
		try {
			if (mutex == null) {
				mutex = new Mutex();
			}

			if (!mutex.blocking) {
				if (DEFAULT_MUTEX_file.exists()) {
					mutex.mutexNumber = mutex.loadMutexNumber();
				}

				if (!mutex.block0()) {
					throw new MutexException("Program is already running!");
				}
			}
		} catch (IOException e) {
			throw new MutexException(e);
		}
	}

	public static synchronized void release() throws MutexException {
		if (mutex != null && mutex.blocking) {
			try {
				mutex.release0();
			} catch (Exception e) {
				throw new MutexException(e);
			}
		}
	}

	private int mutexNumber;
	private Thread d;
	private ServerSocket serverSocket;
	private boolean blocking;

	private Mutex() {

	}

	private boolean block0() throws IOException {
		if (mutexNumber == 0) {
			serverSocket = new ServerSocket(mutexNumber);
			mutexNumber = serverSocket.getLocalPort();

			d = new Thread(createSocketListener(), "MutexListenerThread");
			d.setDaemon(true);
			d.start();

			mutex.blocking = true;
			saveMutexNumber();

			return true;
		} else {
			Socket s1 = null;
			try {
				s1 = new Socket("localhost", mutexNumber);
				s1.getOutputStream().write("wysinwyg?".getBytes());

				if (determineStringFromSocket(s1, "yes")) {
					return false;
				} else {
					mutexNumber = 0;
					return block0();
				}

			} catch (IOException e) {
				mutexNumber = 0;
				return block0();
			} finally {
				if (s1 != null) {
					s1.close();
				}
			}
		}
	}

	private boolean release0() throws Exception {
		mutex.blocking = false;
		serverSocket.close();
		d.join();
		mutexNumber = 0;
		saveMutexNumber();
		return true;
	}

	private int loadMutexNumber() throws IOException {
		Properties prop = PropertiesUtils.loadProperties(DEFAULT_MUTEX_file);
		return Integer.parseInt(prop.getProperty("mutex"));
	}

	private void saveMutexNumber() throws IOException {
		Properties prop = new Properties();
		prop.setProperty("mutex", String.valueOf(mutexNumber));
		PropertiesUtils.saveProperties(prop, DEFAULT_MUTEX_file);
	}

	private Runnable createSocketListener() {
		return new Runnable() {

			@Override
			public void run() {
				while (true) {
					Socket s = null;
					try {
						s = serverSocket.accept();
						if (determineStringFromSocket(s, "wysinwyg?")) {
							s.getOutputStream().write("yes".getBytes());
						}
						s.close();
					} catch (IOException e) {
						if (Mutex.this.blocking) {
							e.printStackTrace();
						} else {
							break;
						}
					} finally {
						if (s != null) {
							try {
								s.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		};
	}

	private boolean determineStringFromSocket(Socket s1, String str) throws IOException {
		byte[] b = new byte[str.length()];
		s1.getInputStream().read(b);
		if (str.equals(new String(b))) {
			return true;
		} else {
			return false;
		}
	}

}
