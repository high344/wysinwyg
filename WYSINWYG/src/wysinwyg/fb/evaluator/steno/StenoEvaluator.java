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
package wysinwyg.fb.evaluator.steno;

import java.util.Arrays;
import java.util.Objects;

import javax.swing.JPanel;

import wysinwyg.fb.device.keyboard.KeyboardEvent;
import wysinwyg.fb.device.keyboard.KeyboardKeyState;
import wysinwyg.fw.device.DeviceEvent;
import wysinwyg.fw.evaluator.AbstractEvaluator;
import wysinwyg.fw.evaluator.EvaluationEvent;

public class StenoEvaluator extends AbstractEvaluator {

	private StenoReference reference;
	private StenoView view;
	private char[] charStroke = new char[23];
	private StenoOrder[] stenos = StenoOrder.values();
	private int rawPower;

	public StenoEvaluator(StenoReference refrence) {
		Objects.requireNonNull(refrence);
		this.reference = refrence;
		zeroCharStroke();
		view = new StenoView();
		view.getStentura().removeAllJToggleButtonMouseListener();
	}

	@Override
	public JPanel getView() {
		return view;
	}

	@Override
	public String getDisplayName() {
		return "Steno";
	}

	@Override
	protected EvaluationEvent evaluateDeviceEvent(DeviceEvent de) {
		KeyboardEvent ke = (KeyboardEvent) de;
		if (ke.getKeyState() == KeyboardKeyState.DEVICE_KEY_PRESSED) {
			StenoOrder steno = null;
			if ((steno = reference.getStenoReference(ke.getvKeyCode())) != null) {
				rawPower += ke.getvKeyCode();
				charStroke[steno.ordinal()] = '1';
				view.getStentura().getJToggleButton(steno.name()).setSelected(true);
			}
		} else if (ke.getKeyState() == KeyboardKeyState.DEVICE_KEY_RELEASED) {
			if (reference.getStenoReference(ke.getvKeyCode()) != null) {
				rawPower -= ke.getvKeyCode();
				if (rawPower < 0) {
					rawPower = 0;
				}
				if (rawPower == 0 && !view.getChckbxArpeggiate().isSelected()) {
					view.getStentura().setAllJToggleButtonSelected(false);
					evaluteKeyReleased();
				}
				// TODO 32
			} else if (view.getChckbxArpeggiate().isSelected() && ke.getvKeyCode() == 32) {
				view.getStentura().setAllJToggleButtonSelected(false);
				evaluteKeyReleased();
			}
		}
		return null;
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
				strokeString += stenos[i].getSimpleName();
			} else if (i == 8 && isHyphenNeeded(charStroke)) {
				strokeString += "-";
			}
		}
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
