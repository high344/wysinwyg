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

/**
 * Responsible for initializing, starting and stopping selected components.
 * 
 * @see {@linkplain DeviceInit}, {@linkplain EvaluatorInit},
 *      {@linkplain TranslatorInit}, {@linkplain Printer}
 * @author FelfoldiB.
 *
 */
public class WysinwygModel {

	/**
	 * The selected device, set by the {@link WysinwygModel#start()} method.
	 * Otherwise its {@code null}.
	 */
	private Device runningDevice;

	/**
	 * The selected evaluator, set by the {@link WysinwygModel#start()} method.
	 * Otherwise its {@code null}.
	 */
	private Evaluator runningEvaluator;

	/**
	 * The selected evaluator, set by the {@link WysinwygModel#start()} method.
	 * Otherwise its {@code null}.
	 */
	private Translator runningTranslator;

	private DeviceInit device;
	private EvaluatorInit evaluator;
	private TranslatorInit translator;
	private Printer printer;

	/**
	 * Initialize a {@linkplain DeviceInit}, a {@linkplain EvaluatorInit}, a
	 * {@linkplain TranslatorInit} and a {@linkplain Printer}.
	 */
	public WysinwygModel() {
		device = new DeviceInit();
		evaluator = new EvaluatorInit();
		translator = new TranslatorInit();
		printer = Win32Printer.getInstance();
	}

	/**
	 * 
	 * @return a {@linkplain DeviceInit} class.
	 */
	public DeviceInit getDevice() {
		return device;
	}

	/**
	 * 
	 * @return a {@linkplain EvaluatorInit} class.
	 */
	public EvaluatorInit getEvaluator() {
		return evaluator;
	}

	/**
	 * 
	 * @return a {@linkplain TranslatorInit} class.
	 */
	public TranslatorInit getTranslator() {
		return translator;
	}

	/**
	 * Gather the selected {@linkplain Device}, {@linkplain Evaluator},
	 * {@linkplain Translator} components and start them.
	 */
	public void start() {
		runningDevice = device.getController().getSelectedDevice();
		runningEvaluator = evaluator.getController().getSelectedEvaluator();
		runningTranslator = translator.getController().getSelectedTranslator();
		runningTranslator.startTranslation();
		runningEvaluator.addEvaluationListener(runningTranslator);
		runningEvaluator.startEvaluation();
		runningDevice.addDeviceListener(runningEvaluator);

		// if the selected device is a keyboard with a hook, we need the printer
		// to differentiate the physical and virtual key events.
		if (runningDevice instanceof Keyboard) {
			Keyboard abs = (Keyboard) runningDevice;
			abs.getHook().setPrinter(printer);
		}
		runningDevice.startDevice();
	}

	/**
	 * Stop the selected components started by the {@link WysinwygModel#start()}
	 * method.
	 */
	public void stop() {
		runningDevice.stopDevice();
		runningDevice.removeDeviceListener(runningEvaluator);
		runningEvaluator.stopEvaluation();
		runningEvaluator.removeEvaluationListener(runningTranslator);
		runningTranslator.stopTranslation();
	}

}
