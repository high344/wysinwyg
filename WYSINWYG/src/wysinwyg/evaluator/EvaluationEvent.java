package wysinwyg.evaluator;

import java.util.EventObject;

public class EvaluationEvent extends EventObject {

	private static final long serialVersionUID = -7561597978391111059L;

	private String result;

	public EvaluationEvent(Object source, String result) {
		super(source);
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	@Override
	public String toString() {
		return "EvaluationEvent [result=" + result + "]";
	}

}
