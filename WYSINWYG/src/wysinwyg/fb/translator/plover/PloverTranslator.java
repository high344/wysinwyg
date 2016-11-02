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
package wysinwyg.fb.translator.plover;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

import wysinwyg.fb.evaluator.steno.StenoOrder;
import wysinwyg.fw.Controller;
import wysinwyg.fw.translator.AbstractTranslator;
import wysinwyg.fw.translator.TranslationEvent;
import wysinwyg.fw.translator.dictionary.Dictionary;
import wysinwyg.fw.translator.dictionary.DictionaryBuilder;
import wysinwyg.fw.translator.dictionary.DictionaryController;
import wysinwyg.utils.CapacityArrayDeque;

public class PloverTranslator extends AbstractTranslator {

	
	private CapacityArrayDeque<Element> stenoElements = new CapacityArrayDeque<Element>(11);
	private PloverView view;
	private PloverController controller;
	private PloverDictionaryOptions dOptions;
	private DictionaryController dController;

	public PloverTranslator() {
		dOptions = new PloverDictionaryOptions(new String[] { ".json" });

		PloverTranslationValidator ptv = new PloverTranslationValidator();
		this.addTranslationListener(ptv);

		
		ClassLoader classLoader = getClass().getClassLoader();
		File f = new File(classLoader.getResource("dict.json").getFile());

		dOptions.addDictionary(f);

		view = new PloverView();
		controller = new PloverController(view);
		dController = new DictionaryBuilder(dOptions).setDictionaryView(view.getDictionaryView()).build();
	}

	@Override
	public String getDisplayName() {
		return "Plover";
	}

	@Override
	public void translate(String translate) {
		findTranslation(translate);
	}

	@Override
	public void startTranslation() {
		dOptions.entries.clear();
		dOptions.loadUpAllDictionary();
	}

	@Override
	public void stopTranslation() {
		dOptions.entries.clear();
		stenoElements.clear();
		// controller.updateTextFieldStroke("");
	}

	@Override
	public JPanel getView() {
		return view;
	}

	public Controller getController() {
		return controller;
	}

	private void findTranslation(String result) {
		// Element lastSteno = stenoElements.getLastElement();
		if (result.equals("*")) {
			Element e = stenoElements.removeLastElement();
			if (e != null) {
				// TODO
				// printer.change(lastSteno.getPrintedString(),
				// lastSteno.getRemovedString());
			}
		} else {
			Element e = new Element(result, null);
			stenoElements.addElement(e);

			String fittedStroke = findLongestFittingStroke(result, getAllElements());
			if (fittedStroke != null) {
				e.setTranslation(getLongestTranslation(result, fittedStroke));
			} else {
				e.setTranslation(result);
			}

			super.translationEventOccurred(new TranslationEvent(this, e.getStroke(), e.getTranslation()));

			/* @formatter:off
			int lastStrokeSuffixType = 0;
			Element e2 = stenoElements.getElement(longestStroke);
			if (e2 != null) {
				lastStrokeSuffixType = e2.getSuffixType();
			}

			e.setPrintedString(getPrintedStringForTranslation(e.getTranslation(), lastStrokeSuffixType));
			e.setSuffixType(getSuffixTypeForTranslation(e.getTranslation()));
			e.setRemovedString(concatPrintedString(longestStroke));

			System.out.println(e);
			if (e.getRemovedString() != null) {
				// TODO printer.print(e.getPrintedString(),
				e.getRemovedString().length();
			} else {
				// TODO printer.print(e.getPrintedString(), 0);
			}*/

			controller.updateTranslation(e.getTranslation());
		}
		controller.updateStrokes(stenoElements);

	}

	private String getAllElements() {
		String cache = "";
		for (int i = 0; i < stenoElements.size(); i++) {
			if (i != 0) {
				cache += "/";
			}
			cache += stenoElements.getElement(i).getStroke();
		}
		return cache;
	}

	private int longestStroke = 0;

	public String findLongestFittingStroke(String lastElement, String allElement) {
		System.out.println("last: " + lastElement + " all:" + allElement);
		Map<String, String> z = dOptions.entries.get(lastElement);
		if (z == null) {
			return null;
		}
		controller.updateStrokeResults(z);
		System.out.println("zSize: " + z.size());
		for (Map.Entry<String, String> d : z.entrySet()) {
			System.out.println(d.getKey() + " : " + d.getValue());
		}
		String trans = allElement;
		do {
			if (z.containsKey(trans)) {
				System.out.println("found: " + trans);
				break;
			}
			String[] temp = trans.split("/", 2);
			longestStroke++;
			if (temp.length == 2) {
				trans = temp[1];
			} else {
				longestStroke = 0;
				return lastElement;
			}
		} while (true);
		return trans;
	}

	public String getLongestTranslation(String lastElement, String longestStroke) {
		Map<String, String> z = dOptions.entries.get(lastElement);
		return z.get(longestStroke);
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
