package wysinwyg;

import wysinwyg.device.Device;
import wysinwyg.device.DeviceInit;
import wysinwyg.evaluator.Evaluator;
import wysinwyg.evaluator.EvaluatorInit;
import wysinwyg.translator.TranslatorInit;

public class WysinwygModel {

	private Device runningDevice;
	private Evaluator runningEvaluator;
	
	private DeviceInit device;
	private EvaluatorInit evaluator;
	private TranslatorInit translator;
	
	WysinwygModel () {
		device = new DeviceInit();
		evaluator = new EvaluatorInit();
		translator = new TranslatorInit();
	}
	
	DeviceInit getDevice() {
		return device;
	}
	
	EvaluatorInit getEvaluator() {
		return evaluator;
	}
	
	TranslatorInit getTranslator() {
		return translator;
	}
	
	/*
	public WysinwygModel(WysinwygPanel frame) {
		deviceControl = new DeviceInit().getController();
		evaluateControl = new EvaluatorInit(frame.evaluate).getController();
		
		translator = new PloverTranslator();
		new DictionaryInit(translator, frame.dictionary);
		
		DisplayInit displayInit = new DisplayInit();
	}*/

	void start() {
		runningDevice = device.getController().getSelectedDevice();
		runningEvaluator = evaluator.getController().getSelectedEvaluator();
		runningEvaluator.startEvaluation();
		
		
		runningDevice.addDeviceListener(runningEvaluator);
		device.getController().getSelectedDevice().startDevice();
		/*
		if(deviceControl != null && (runningDevice = deviceControl.getSelectedDevice()) != null) {
			if(evaluateControl != null && (runningEvaluator = evaluateControl.getSelectedEvaluator()) != null) {
				runningEvaluator.prepareEvaluation();
				runningEvaluator.setTranslator(translator);
				//runningEvaluator.setPrinter(new Display());
				runningDevice.addDeviceListener(runningEvaluator);
			}
			runningDevice.startDevice();
		}*/
	}
	
	void stop() {/*
		if(deviceControl != null && runningDevice != null) {
			runningDevice.stopDevice();
			runningDevice = null;
		}*/
		runningDevice.stopDevice();
		runningDevice.removeDeviceListener(runningEvaluator);
		runningEvaluator.stopEvaluation();
	}
	
}
