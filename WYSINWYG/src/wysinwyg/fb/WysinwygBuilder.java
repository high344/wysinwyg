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

import wysinwyg.fb.device.Devices;
import wysinwyg.fb.evaluator.Evaluators;
import wysinwyg.fb.mutex.Mutex;
import wysinwyg.fb.mutex.MutexException;
import wysinwyg.fw.Builder;
import wysinwyg.fw.device.DeviceBuilder;
import wysinwyg.fw.device.DeviceController;
import wysinwyg.fw.evaluator.EvaluatorBuilder;
import wysinwyg.fw.evaluator.EvaluatorController;
import wysinwyg.utils.ErrorMessage;

public class WysinwygBuilder implements Builder {

	static {
		WysinwygController.debug = Boolean.parseBoolean(System.getProperty("wysinwyg.debug"));
	}

	@Override
	public WysinwygController build() {
		String s = System.getProperty("wysinwyg.mutex");
		if(s == null || !s.equalsIgnoreCase("false")) {
			testOnlyInstance();
		}

		WysinwygView view = new WysinwygView();

		DeviceController deviceController = createDeviceController(view);
		EvaluatorController evaluatorController = createEvaulatorController(view);

		WysinwygController controller = new WysinwygController(view);
		controller.setDeviceController(deviceController);
		controller.setEvaluatorController(evaluatorController);

		return controller;
	}

	private void testOnlyInstance() {
		try {
			Mutex.block();
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Mutex.release();
					} catch (MutexException e) {
						ErrorMessage.show(e, WysinwygController.debug);
					}
				}
			}, "MutexShutdownHook"));
		} catch (MutexException e) {
			ErrorMessage.show(e, WysinwygController.debug);
			System.exit(1);
		}
	}

	private DeviceController createDeviceController(WysinwygView view) {
		return new DeviceBuilder().setDeviceView(view.getDeviceView()).addDeviceList(new Devices().readUpDevices())
				.build();
	}
	
	private EvaluatorController createEvaulatorController(WysinwygView view) {
		return new EvaluatorBuilder().setEvaluatorView(view.getEvaluatorView()).addEvaluatorList(new Evaluators().getEvaluators())
				.build();
	}

}
