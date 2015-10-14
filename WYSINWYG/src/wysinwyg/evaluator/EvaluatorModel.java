package wysinwyg.evaluator;

import wysinwyg.device.keyboard.hook.StenoReferenceWin32;
import wysinwyg.evaluator.steno.StenoEvaluator;

public class EvaluatorModel {

	private Evaluator[] evas = new Evaluator[1];

	public EvaluatorModel() {
		load();
	}

	private void load() {
		evas[0] = new StenoEvaluator(new StenoReferenceWin32());
	}

	public Evaluator[] getEvaluators() {
		return evas;
	}

}
