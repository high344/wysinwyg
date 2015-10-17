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
package wysinwyg.evaluator.steno;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import wysinwyg.Controller;
import wysinwyg.Init;
import wysinwyg.device.DeviceEvent;
import wysinwyg.evaluator.EvaluationEvent;
import wysinwyg.evaluator.EvaluationListener;
import wysinwyg.evaluator.Evaluator;

public class StenoEvaluator implements Init, Evaluator, EvaluationListener {

	private boolean arpeggiate;
	private StenoReference reference;
	private StenoView view;
	private char[] charStroke = new char[23];
	private StenoOrder[] stenos = StenoOrder.values();
	private List<EvaluationListener> list;
	private int rawPower;

	public StenoEvaluator(StenoReference refrence) {
		Objects.requireNonNull(refrence);
		this.reference = refrence;

		list = new ArrayList<EvaluationListener>(3);
		zeroCharStroke();
		view = new StenoView();
		view.getStentura().removeAllJToggleButtonMouseListener();
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
		if (e.getKeyState() == DeviceEvent.DEVICE_KEY_PRESSED) {
			StenoOrder steno = null;
			if ((steno = reference.getStenoReference(e.getvKeyCode())) != null) {
				rawPower += e.getvKeyCode();
				charStroke[steno.ordinal()] = '1';
				view.getStentura().getJToggleButton(steno.name()).setSelected(true);
			}
		} else if (e.getKeyState() == DeviceEvent.DEVICE_KEY_RELEASED) {
			if (reference.getStenoReference(e.getvKeyCode()) != null) {
				rawPower -= e.getvKeyCode();
				if (rawPower < 0) {
					rawPower = 0;
				}
				if (rawPower == 0 && !arpeggiate) {
					view.getStentura().setAllJToggleButtonSelected(false);
					evaluteKeyReleased();
				}
				// TODO 32
			} else if (arpeggiate && e.getvKeyCode() == 32) {
				view.getStentura().setAllJToggleButtonSelected(false);
				evaluteKeyReleased();
			}
		}
		e.setConsumeEnabled(true);
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
		for (EvaluationListener eva : list) {
			eva.evaluationEventOccurred(e);
		}
	}

	@Override
	public void startEvaluation() {
		arpeggiate = view.getChckbxArpeggiate().isSelected();
	}

	@Override
	public void stopEvaluation() {
		rawPower = 0;
		zeroCharStroke();
		view.getStentura().setAllJToggleButtonSelected(false);
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

		for (int i = 0; i < charStroke.length; i++) {
			if (charStroke[i] == '1') {
				if (stenos[i] == StenoOrder.hm) {
					strokeString += "#";
				} else if (stenos[i] == StenoOrder.st) {
					strokeString += "*";
				} else {
					strokeString += stenos[i].name();
				}
			} else if (i == 8 && isHyphenNeeded(charStroke)) {
				strokeString += "-";
			}
		}
		strokeString = strokeString.replace("_", "");
		if (strokeString.endsWith("-")) {
			strokeString = strokeString.replace("-", "");
		}
		System.out.println(charStroke);
		System.out.println(strokeString);
		return strokeString;
	}

	private boolean isHyphenNeeded(char[] charStroke) {
		for (int i = 8; i <= 12; i++) {
			if (charStroke[i] == '1') {
				return false;
			}
		}
		return true;
	}

}
