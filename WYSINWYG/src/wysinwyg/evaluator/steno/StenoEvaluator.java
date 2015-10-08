package wysinwyg.evaluator.steno;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wysinwyg.Controller;
import wysinwyg.device.DeviceEvent;
import wysinwyg.evaluator.EvaluationEvent;
import wysinwyg.evaluator.EvaluationListener;
import wysinwyg.evaluator.Evaluator;

public class StenoEvaluator implements Evaluator, EvaluationListener {

	private boolean arpeggiate;
	private StenoRefrence refrence;
	private StenoView view;
	private char[] charStroke = new char[23];
	private StenoOrder[] stenos = StenoOrder.values();
	private List<EvaluationListener> list;
	private int rawPower;
	
	public StenoEvaluator() {
		list = new ArrayList<EvaluationListener>(3);
		zeroCharStroke();
		view = new StenoView();
		view.stentura.removeAllJToggleButtonMouseListener();
	}
	
	@Override
	public Component getView() {
		return view;
	}

	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getDisplayName() {
		return "Steno";
	}
	
	@Override
	public void deviceEventOccurred(DeviceEvent e) {
		if(e.getKeyState() == DeviceEvent.DEVICE_KEY_PRESSED) {
			StenoOrder steno = null;
			if ((steno = refrence.getStenoRefrence(e.getvKeyCode())) != null) {
				rawPower += e.getvKeyCode();
				charStroke[steno.ordinal()] = '1';
				view.stentura.getJToggleButton(steno.name()).setSelected(true);
			}
		} else if(e.getKeyState() == DeviceEvent.DEVICE_KEY_RELEASED) {
			if (refrence.getStenoRefrence(e.getvKeyCode()) != null) {
				rawPower -= e.getvKeyCode();
				if (rawPower == 0 && !arpeggiate) {
					view.stentura.setAllJToggleButtonSelected(false);
					evaluteKeyReleased();
				}
				//TODO 57
			} else if(arpeggiate && e.getScanCode() == 57) {
				view.stentura.setAllJToggleButtonSelected(false);
				evaluteKeyReleased();
			}
		}
	}
	
	@Override
	public void addEvaluationListener(EvaluationListener evaListener) {
		list.add(evaListener);
	}

	@Override
	public void removeEvaluationListener(EvaluationListener evaListener) {
		list.remove(evaListener);
	}

	@Override
	public void evaluationEventOccurred(EvaluationEvent e) {
		for(EvaluationListener eva : list) {
			eva.evaluationEventOccurred(e);
		}
	}

	@Override
	public void startEvaluation() {
		arpeggiate = view.chckbxArpeggiate.isSelected();
		//TODO
		refrence = new StenoRefrenceWin32();
	}
	
	@Override
	public void stopEvaluation() {
		// TODO Auto-generated method stub
	}	
	
	private void zeroCharStroke() {
		Arrays.fill(charStroke, 0, 23, '0');
	}
	
	private void evaluteKeyReleased() {
		EvaluationEvent eve = new EvaluationEvent(this, buildStenoString(charStroke));
		zeroCharStroke();
		evaluationEventOccurred(eve);
	}
	
	private String buildStenoString(char[] charStroke) {
		String strokeString = "";
		Boolean possibleNumber = false;
		

		for(int i=0; i < charStroke.length; i++) {
			if(charStroke[i] == '1') {
				if(stenos[i] == StenoOrder.hm) {
					strokeString += "#";
					possibleNumber = new Boolean(true);
				} else if(stenos[i] == StenoOrder.st) {
					strokeString += "*";
				} else {
					if(possibleNumber) {
						String num = getStenoNumberRefrence(stenos[i]);
						if(num == null) {
							strokeString += stenos[i].name();
						} else {
							strokeString += num;
							
						}
					} else {
						strokeString += stenos[i].name();
					}
				}
			} else if(i == 8 && isHyphenNeeded(charStroke)) {
				strokeString += "-";
			}
		}
		strokeString = strokeString.replace("_", "");
		if(strokeString.endsWith("-")) {
			strokeString = strokeString.replace("-", "");
		}
		System.out.println(charStroke);
		System.out.println(strokeString);
		return strokeString;
	}
	
	private boolean isHyphenNeeded(char[] charStroke) {
		for(int i = 8; i <= 12; i++){
			if(charStroke[i] == '1') {
				return false;
			}
		}
		return true;
	}
	
	private String getStenoNumberRefrence(StenoOrder steno) {
		switch (steno) {
		case S_:
			return "1";
		case T_:
			return "2";
		case P_:
			return "3";
		case H_:
			return "4";
		case A_:
			return "5";
		case O_:
			return "0";
		case _F:
			return "6";
		case _P:
			return "7";
		case _L:
			return "8";
		case _T:
			return "9";
		default:
			break;
		}
		return null;
	}
	
}
