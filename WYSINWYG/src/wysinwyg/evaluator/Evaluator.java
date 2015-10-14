package wysinwyg.evaluator;

import wysinwyg.device.DeviceListener;
import wysinwyg.utils.ComboboxDisplayName;

public interface Evaluator extends DeviceListener, ComboboxDisplayName {

	public void addEvaluationListener(EvaluationListener evaListener);

	public void removeEvaluationListener(EvaluationListener evaListener);

	public void startEvaluation();

	public void stopEvaluation();

}
