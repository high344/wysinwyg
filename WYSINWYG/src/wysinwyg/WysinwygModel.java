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
package wysinwyg;

import wysinwyg.device.Device;
import wysinwyg.device.DeviceInit;
import wysinwyg.device.keyboard.Keyboard;
import wysinwyg.evaluator.Evaluator;
import wysinwyg.evaluator.EvaluatorInit;
import wysinwyg.printer.Printer;
import wysinwyg.printer.win32.Win32Printer;
import wysinwyg.translator.Translator;
import wysinwyg.translator.TranslatorInit;

public class WysinwygModel {

	private Device runningDevice;
	private Evaluator runningEvaluator;
	private Translator runningTranslator;

	private DeviceInit device;
	private EvaluatorInit evaluator;
	private TranslatorInit translator;
	private Printer printer;

	public WysinwygModel() {
		device = new DeviceInit();
		evaluator = new EvaluatorInit();
		translator = new TranslatorInit();
		printer = Win32Printer.getInstance();
	}

	public DeviceInit getDevice() {
		return device;
	}

	public EvaluatorInit getEvaluator() {
		return evaluator;
	}

	public TranslatorInit getTranslator() {
		return translator;
	}

	public void start() {
		runningDevice = device.getController().getSelectedDevice();
		runningEvaluator = evaluator.getController().getSelectedEvaluator();
		runningTranslator = translator.getController().getSelectedTranslator();
		runningTranslator.startTranslation();
		runningEvaluator.addEvaluationListener(runningTranslator);
		runningEvaluator.startEvaluation();
		runningDevice.addDeviceListener(runningEvaluator);

		if (runningDevice instanceof Keyboard) {
			Keyboard abs = (Keyboard) runningDevice;
			abs.getHook().setPrinter(printer);
		}
		runningDevice.startDevice();
	}

	public void stop() {
		runningDevice.stopDevice();
		runningDevice.removeDeviceListener(runningEvaluator);
		runningEvaluator.stopEvaluation();
		runningEvaluator.removeEvaluationListener(runningTranslator);
		runningTranslator.stopTranslation();
	}

}
