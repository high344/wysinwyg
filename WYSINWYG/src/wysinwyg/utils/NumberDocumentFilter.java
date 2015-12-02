package wysinwyg.utils;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberDocumentFilter extends DocumentFilter {

	private NumberFormat nf = NumberFormat.getInstance();

	private boolean testNumber(String str) {
		try {
			nf.parse(str);
		} catch (ParseException e) {
			if (str.equals(".")) {
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		if (!testNumber(string)) {
			return;
		}
		super.insertString(fb, offset, string, attr);
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {
		if (!testNumber(text)) {
			return;
		}
		super.replace(fb, offset, length, text, attrs);
	}

}
