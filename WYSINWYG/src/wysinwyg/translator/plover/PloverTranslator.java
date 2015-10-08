package wysinwyg.translator.plover;

import java.util.Map;
import java.util.TreeMap;

import wysinwyg.evaluator.EvaluationEvent;
import wysinwyg.translator.AbstractTranslator;
import wysinwyg.translator.dictionary.Dictionary;


public class PloverTranslator extends AbstractTranslator {

	private TreeMap<String, Map<String, String>> entries;
	
	public PloverTranslator(String[] supportedFileExtensions) {
		super(supportedFileExtensions);
		
		entries = new TreeMap<String, Map<String, String>>();
		
		addDictionary("/resources/dict1.json");
		addDictionary("/resources/dict2.json");
		addDictionary("/resources/dict3.json");
		addDictionary("/resources/dict4.json");
	}

	@Override
	public String getDisplayName() {
		return "Plover";
	}

	@Override
	public void evaluationEventOccurred(EvaluationEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	protected Dictionary initDictionary(String path) {
		return new PloverJSonDictionary(path, entries);
	}
	
	/*
	
	private void load() {
		entries.clear();
		for(Dictionary d : dictionaries) {
			d.loadDictionary();
		}
	}*/
	
	/*

public static void main(String[] args) {
		PloverTranslator trans = new PloverTranslator();
		String[] test = {
				"{,}but if you want to know what I believe{,}   {-|}   i'll tell you..{.}",
				"they begin as random events that most people don't even notice.",
				"{-|}but they grow{.}   ", "     they multiply...",
				"Some people call that  ", "{^ \"}fate\".", "I call it ",
				"{&Cha}", "{&os}{    }", "{&The}", "{&ory.}", "asdasd{^asd^}" };
		
		for(String str: test) {
			System.out.println(str+" "+trans.getSuffixTypeForTranslation(str));
		}
		
	}
	
	public PloverTranslator() {
		this.commandBegin = '{';
		this.commandEnd = '}';
		this.commandBeginCount = Character.charCount('{');
		this.commandEndCount = Character.charCount('}');
		
		entries = new TreeMap<String, Map<String, String>>();
		init();
	}

	public int[] getWhites() {
		return whites;
	}
	
	public int getCommandReference(String type) {
		switch (type) {
		case "?":
			return COMMAND_GLUE_SPACE_AND_CAPITALIZE;
		case "!":
			return COMMAND_GLUE_SPACE_AND_CAPITALIZE;
		case ".":
			return COMMAND_GLUE_SPACE_AND_CAPITALIZE;
		case ";":
			return COMMAND_GLUE_SPACE;
		case ",":
			return COMMAND_GLUE_SPACE;
		case "-|":
			return COMMAND_CAPITALIZE_NEXT_WORD;
		case ">":
			return COMMAND_LOWERCASE_NEXT_WORD;
		case "^":
			return COMMAND_GLUE;
		case "&":
			return COMMAND_GLUE_NEXT_GLUE;
		case " ":
			return COMMAND_SPACE;
		}
		return COMMAND_NOT_PRESENT;
	}

	public String[] getSimples() {
		return cmdSimple;
	}
	
	public int getSuffixTypeForTranslation(String translation) {
		int l = translation.length();
		int cbegin = -1;
		int cend = -1;
		boolean last = true;
		for(int i = 0, ch; i < l; i += Character.charCount(ch)) {
			ch = translation.codePointAt(i);
			if(ch == commandBegin) {
				cbegin = i+Character.charCount(ch);
			} else if (ch == commandEnd) {
				cend = i;
				last = true;
			} else if(!contains(getWhites(), ch)) {
				last = false;
			}
		}
		if(cbegin > -1 && cend > -1 && cbegin < cend && last) {
			return determineCommandType(translation, cbegin);
		}
		return COMMAND_NOT_PRESENT;
	}
	
	public String getPrintedStringForTranslation(String translation, int lastStrokeSuffixType) {
		int l = translation.length();
		int index = skipWSfront(translation, l);
		if(index == -1) {
			return null;
		}
		int cmd = COMMAND_NOT_PRESENT;
		String pstr = "";
		for (int i = 0, ch; i < l; i += Character.charCount(ch)) {
			ch = translation.codePointAt(i);
			if(contains(getWhites(), ch)) {
				if(cmd == COMMAND_SPACE) {
					pstr += Character.toChars(ch);
				}
			} else if (ch == commandBegin) {
				cmd = determineCommandTypeExtended(translation, i);
			} else if (ch == commandEnd) {
				cmd = COMMAND_NOT_PRESENT;
			} else {
				if(cmd == COMMAND_ERROR) {
					
				} else {
					pstr += Character.toChars(ch);
				}
			}
		}
		return pstr;
	}
	
	private int skipWSfront(String translation, int length) {
		for (int i = 0, ch = 0; i < length; i += Character.charCount(ch)) {
			ch = translation.codePointAt(i);
			if(!contains(getWhites(), ch)) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean contains(int[] ws, int ch) {
		for(int i = 0; i < ws.length; i++) {
			if(ws[i] == ch) {
				return true;
			}
		}
		return false;
	}
	
	private int determineCommandType(String translation, int from) {
		for(String str : getSimples()) {
			if(translation.startsWith(str, from)) {
				return getCommandReference(str);
			}
		}
		return COMMAND_NOT_PRESENT;
	}
	
	private int determineCommandTypeExtended(String translation, int from) {
		int c = determineCommandType(translation, from);
		switch(c) {
			case COMMAND_GLUE:
				//TODO return ext glue;
				return COMMAND_GLUE;
		}
		return COMMAND_NOT_PRESENT;
	}

	

	
	Element lastSteno = stenoElements.getLastElement();
	String stroke = buildStenoString(charStroke);
	
	if(stroke.length() == 1 && stroke.charAt(0) == '*') {
		if(lastSteno == null){
			return;
		}
		stenoElements.removeLastElement();
		if(lastSteno.getPrintedString() != null) {
			printer.print(lastSteno.getRemovedString(), lastSteno.getPrintedString().length());
		}
		//printer.change(lastSteno.getPrintedString(), lastSteno.getRemovedString());
	} else {
		Element e = new Element(stroke, null, 0, null, null);
		stenoElements.addElement(e);
		
		String fittedStroke = dict.findLongestFittingStroke(stroke, stenoElements.getAllElements());
		if(fittedStroke != null) {
			e.setTranslation(dict.getLongestTranslation(stroke, fittedStroke));
		} else {
			e.setTranslation(stroke);
		}
		int lastStrokeSuffixType = 0;
		int c = dict.getLongestFitStroke();
		Element e2 = stenoElements.getElement(c);
		if(e2 != null) {
			lastStrokeSuffixType = e2.getSuffixType();
		}
		
		e.setPrintedString(translator.getPrintedStringForTranslation(e.getTranslation(), lastStrokeSuffixType));
		e.setSuffixType(translator.getSuffixTypeForTranslation(e.getTranslation()));
		e.setRemovedString(stenoElements.concatPrintedString(c));
		
		if(e.getRemovedString() != null) {
			printer.print(e.getPrintedString(), e.getRemovedString().length());
		} else {
			printer.print(e.getPrintedString(), 0);
		}
		//System.out.println(e);
	}

/*
public boolean consumeKey(NativeKeyEvent nke) {
	System.out.println("consume: "+nke.paramString());
	if (!nke.isActionKey()
			//&& isConsumeEnabled
			&& nke.getKeyCode() != NativeKeyEvent.VC_BACKSPACE
			&& nke.getKeyCode() != NativeKeyEvent.VC_DELETE
			&& nke.getKeyCode() != NativeKeyEvent.VC_ESCAPE
			&& nke.getKeyCode() != NativeKeyEvent.VC_ENTER
			&& nke.getKeyCode() != NativeKeyEvent.VC_TAB) {
		return true;
	}
	return false;
}

	public static final int COMMAND_NOT_PRESENT = 0;
	public static final int COMMAND_ERROR = 9;
	
	public static final int COMMAND_GLUE_SPACE_AND_CAPITALIZE = 1; // {?} {!} {.}
	public static final int COMMAND_GLUE_SPACE = 2; // {;} {,}
	public static final int COMMAND_CAPITALIZE_NEXT_WORD = 3; // {-|}
	public static final int COMMAND_LOWERCASE_NEXT_WORD = 4; // {>}
	public static final int COMMAND_GLUE = 5; // {^}
	public static final int COMMAND_GLUE_EXT= 6; // {^ ^}
	public static final int COMMAND_GLUE_NEXT_GLUE = 7; // {&}
	public static final int COMMAND_SPACE = 8; // { }
	
	public static final int[] whites = {' ', '\t'};
	public static final String[] cmdSimple = {"?", "!", ".", ";", ",", "-|", ">", "^", "&", " "}; // TODO "^", "&", " "
	
	private int commandBegin;
	private int commandBeginCount;
	private int commandEnd;
	private int commandEndCount;
	*/
}
