package wysinwyg.evaluator;

import wysinwyg.Init;
import wysinwyg.device.DeviceListener;
import wysinwyg.utils.ComboboxDisplayName;

public interface Evaluator extends DeviceListener, Init, ComboboxDisplayName {

	public void addEvaluationListener(EvaluationListener evaListener);

	public void removeEvaluationListener(EvaluationListener evaListener);

	public void startEvaluation();

	public void stopEvaluation();

}
