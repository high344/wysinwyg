package wysinwyg.translator.plover;

import java.awt.Component;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import wysinwyg.Controller;
import wysinwyg.Init;
import wysinwyg.evaluator.EvaluationEvent;
import wysinwyg.evaluator.steno.StenoOrder;
import wysinwyg.translator.AbstractTranslator;
import wysinwyg.translator.TranslationEvent;
import wysinwyg.translator.dictionary.Dictionary;
import wysinwyg.utils.CapacityArrayDeque;


public class PloverTranslator extends AbstractTranslator implements Init {

	private TreeMap<String, Map<String, String>> entries;
	private CapacityArrayDeque<Element> stenoElements = new CapacityArrayDeque<Element>(11);
	private PloverView view;
	private PloverController controller;
	
	public PloverTranslator(String[] supportedFileExtensions) {
		super(supportedFileExtensions);
		
		PloverTranslationValidator ptv = new PloverTranslationValidator();
		this.addTranslationListener(ptv);
		
		entries = new TreeMap<String, Map<String, String>>();
		ClassLoader classLoader = getClass().getClassLoader();
		File f = new File(classLoader.getResource("dict.json").getFile());
		addDictionary(f);
		
		view = new PloverView();
		controller = new PloverController(view);
	}

	@Override
	public String getDisplayName() {
		return "Plover";
	}

	@Override
	public void evaluationEventOccurred(EvaluationEvent e) {
		if (e.getResult() != null) {
			findTranslation(e.getResult());
		}
	}

	@Override
	protected Dictionary initDictionary(File file) {
		return new PloverJSonDictionary(file.getPath(), entries);
	}

	@Override
	public void startTranslation() {
		entries.clear();
		loadUpAllDictionary();
	}

	@Override
	public void stopTranslation() {
		entries.clear();
		stenoElements.clear();
		//controller.updateTextFieldStroke("");
	}
	
	@Override
	public Component getView() {
		return view;
	}

	@Override
	public Controller getController() {
		return controller;
	}
	
	private void findTranslation(String result) {
		//Element lastSteno = stenoElements.getLastElement();
		if(result.equals("*")) {
			Element e = stenoElements.removeLastElement();
			if(e != null) {
				//TODO
				//printer.change(lastSteno.getPrintedString(), lastSteno.getRemovedString());
			}
		} else {
			Element e = new Element(result, null);
			stenoElements.addElement(e);
			
			String fittedStroke = findLongestFittingStroke(result, getAllElements());
			if(fittedStroke != null) {
				e.setTranslation(getLongestTranslation(result, fittedStroke));
			} else {
				e.setTranslation(result);
			}
			
			translationEventOccurred(new TranslationEvent(this, e.getStroke(), e.getTranslation()));
			
			/*
			int lastStrokeSuffixType = 0;
			Element e2 = stenoElements.getElement(longestStroke);
			if(e2 != null) {
				lastStrokeSuffixType = e2.getSuffixType();
			}
			
			e.setPrintedString(getPrintedStringForTranslation(e.getTranslation(), lastStrokeSuffixType));
			e.setSuffixType(getSuffixTypeForTranslation(e.getTranslation()));
			e.setRemovedString(concatPrintedString(longestStroke));
			
			System.out.println(e);
			if(e.getRemovedString() != null) {
				//TODO
				//printer.print(e.getPrintedString(), e.getRemovedString().length());
			} else {
				//TODO
				//printer.print(e.getPrintedString(), 0);
			}*/
			controller.updateTextFieldTranslation(e.getTranslation());
		}
		controller.updateTextFieldStroke(getAllElements());
		
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
		System.out.println("last: "+lastElement+" all:"+allElement);
		Map<String, String> z = entries.get(lastElement);
		if (z == null) {
			return null;
		}
		controller.updateTextAreaStrokes(z);
		System.out.println("zSize: "+z.size());
		for(Map.Entry<String, String> d : z.entrySet()) {
			System.out.println(d.getKey()+" : "+d.getValue());
		}
		String trans = allElement;
		do {
			if (z.containsKey(trans)) {
				System.out.println("found: "+trans);
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
		Map<String, String> z = entries.get(lastElement);
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
