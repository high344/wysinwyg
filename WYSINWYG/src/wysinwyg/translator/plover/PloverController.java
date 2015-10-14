package wysinwyg.translator.plover;

import java.util.Map;
import java.util.Objects;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import wysinwyg.Controller;

public class PloverController implements Controller {

	private PloverView view;

	public PloverController(PloverView view) {
		Objects.requireNonNull(view);

		this.view = view;
	}

	public void updateTextFieldStroke(String str) {
		SwingUtilities.invokeLater(createTextFieldRunnable(str, view.getTextFieldStroke()));
	}

	public void updateTextFieldTranslation(String str) {
		SwingUtilities.invokeLater(createTextFieldRunnable(str, view.getTextFieldTranslation()));
	}

	private Runnable createTextFieldRunnable(final String str, final JTextField textField) {
		return new Runnable() {

			@Override
			public void run() {
				textField.setText(str);
			}
		};
	}

	public void updateTextAreaStrokes(Map<String, String> z) {
		SwingUtilities.invokeLater(updateTextArea(z));
	}

	private Runnable updateTextArea(final Map<String, String> z) {
		return new Runnable() {

			@Override
			public void run() {
				try {
					if (z != null) {
						view.getTextAreaStrokes().setText("");
						Document doc = view.getTextAreaStrokes().getDocument();
						for (Map.Entry<String, String> str : z.entrySet()) {
							doc.insertString(doc.getLength(), str.getKey() + " : " + str.getValue()
									+ "\n", null);
						}
					}
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		};
	}
}
