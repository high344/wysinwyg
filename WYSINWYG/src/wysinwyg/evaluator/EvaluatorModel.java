package wysinwyg.evaluator;

import wysinwyg.device.keyboard.hook.StenoRefrenceWin32;
import wysinwyg.evaluator.steno.StenoEvaluator;

public class EvaluatorModel {

	private Evaluator[] evas = new Evaluator[1];

	public EvaluatorModel() {
		load();
	}

	private void load() {
		//TODO
		evas[0] = new StenoEvaluator(new StenoRefrenceWin32());
	}

	public Evaluator[] getEvaluators() {
		return evas;
	}

}
