package wysinwyg.translator.plover;

import wysinwyg.printer.PrinterEvent;
import wysinwyg.printer.win32.Win32Printer;
import wysinwyg.translator.TranslationEvent;
import wysinwyg.translator.TranslationListener;
import wysinwyg.utils.CapacityArrayDeque;

public class PloverTranslationValidator implements TranslationListener{

	@Override
	public void translationEventOccurred(TranslationEvent e) {
		Win32Printer.getInstance().addPrinterEvent(new PrinterEvent(e.getTranslation()));
	}
	
	
	private static final int COMMAND_NOT_PRESENT = 0;
	private static final int COMMAND_ERROR = 9;
	
	private static final int COMMAND_GLUE_SPACE_AND_CAPITALIZE = 1; // {?} {!} {.}
	private static final int COMMAND_GLUE_SPACE = 2; // {;} {,}
	private static final int COMMAND_CAPITALIZE_NEXT_WORD = 3; // {-|}
	private static final int COMMAND_LOWERCASE_NEXT_WORD = 4; // {>}
	private static final int COMMAND_GLUE = 5; // {^}
	private static final int COMMAND_GLUE_EXT= 6; // {^ ^}
	private static final int COMMAND_GLUE_NEXT_GLUE = 7; // {&}
	private static final int COMMAND_SPACE = 8; // { }
	
	private static final int[] whites = {' ', '\t'};
	private static final String[] cmdSimple = {"?", "!", ".", ";", ",", "-|", ">", "^", "&", " "}; // TODO "^", "&", " "
	
	private int commandBegin;
	private int commandBeginCount;
	private int commandEnd;
	private int commandEndCount;
	
	private CapacityArrayDeque<Element> stenoElements = new CapacityArrayDeque<Element>(11);
	
	private int getSuffixTypeForTranslation(String translation) {
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
	
	private String getPrintedStringForTranslation(String translation, int lastStrokeSuffixType) {
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
	
	private String[] getSimples() {
		return cmdSimple;
	}
	
	private String concatPrintedString(int index) {
		if(index <= 0 || index > stenoElements.size()) {
			return "";
		}
		String str = "";
		int x = 0;
		for(int i = 0; i < stenoElements.size(); i++) {
			Element e = stenoElements.getElement(i);
			x++;
			if(x == stenoElements.size()) {
				break;
			} else if(x > i) {
				str += e;
			}
		}
		return str;
	}
	
	public int[] getWhites() {
		return whites;
	}
	
	private int getCommandReference(String type) {
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
*/

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
}*/
	
}
