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
package wysinwyg.fw.evaluator;

import java.util.ArrayList;
import java.util.List;

import wysinwyg.fw.Builder;

public class EvaluatorBuilder implements Builder {

	private EvaluatorView view;
	private List<Evaluator> evaluators = new ArrayList<Evaluator>(5);

	public int getEvaluatorListSize() {
		return evaluators.size();
	}

	public EvaluatorBuilder addEvaluator(Evaluator evaluator) {
		if (evaluator != null) {
			if (!evaluators.contains(evaluator)) {
				evaluators.add(evaluator);
			}
		}
		return this;
	}

	public EvaluatorBuilder removeEvaluator(Evaluator evaluator) {
		evaluators.remove(evaluator);
		return this;
	}

	public EvaluatorBuilder addEvaluatorList(List<Evaluator> list) {
		if (list != null) {
			for (Evaluator e : list) {
				addEvaluator(e);
			}
		}
		return this;
	}

	public EvaluatorBuilder setEvaluatorView(EvaluatorView view) {
		this.view = view;
		return this;
	}

	@Override
	public EvaluatorController build() {
		if (view == null) {
			view = new EvaluatorView();
		}
		return new EvaluatorController(evaluators, view);
	}

}
