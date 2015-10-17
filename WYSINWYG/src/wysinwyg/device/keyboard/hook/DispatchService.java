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
package wysinwyg.device.keyboard.hook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

public class DispatchService extends AbstractExecutorService {

	private boolean running = false;

	public DispatchService() {
		running = true;
	}

	@Override
	public void shutdown() {
		running = false;
	}

	@Override
	public List<Runnable> shutdownNow() {
		running = false;
		return new ArrayList<Runnable>(0);
	}

	@Override
	public boolean isShutdown() {
		return !running;
	}

	@Override
	public boolean isTerminated() {
		return !running;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		return true;
	}

	@Override
	public void execute(Runnable command) {
		command.run();
	}

}
