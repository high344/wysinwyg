package wysinwyg.translator.plover;

import java.util.Map;
import java.util.Objects;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import wysinwyg.Controller;
import wysinwyg.utils.CapacityArrayDeque;

public class PloverController implements Controller {

	private PloverView view;

	public PloverController(PloverView view) {
		Objects.requireNonNull(view);

		this.view = view;
	}

	public void updateStrokes(CapacityArrayDeque<Element> stenoElements) {
		SwingUtilities.invokeLater(createRunnable(stenoElements));
	}

	private Runnable createRunnable(final CapacityArrayDeque<Element> stenoElements) {
		return new Runnable() {

			@Override
			public void run() {
				view.getTextAreaStrokes().setText("");
				try {
					Document doc = view.getTextAreaStrokes().getDocument();
					for (int i = 0; i < stenoElements.size(); i++) {
						doc.insertString(doc.getLength(), stenoElements.getElement(i).getStroke()
								+ "\n", null);
					}
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		};
	}

	public void updateTranslation(String str) {
		SwingUtilities.invokeLater(createRunnable(str));
	}

	private Runnable createRunnable(final String str) {
		return new Runnable() {

			@Override
			public void run() {
				view.getTextAreaTranslation().setText(str);
			}
		};
	}

	public void updateStrokeResults(Map<String, String> z) {
		SwingUtilities.invokeLater(createRunnable(z));
	}

	private Runnable createRunnable(final Map<String, String> z) {
		return new Runnable() {

			@Override
			public void run() {
				try {
					if (z != null) {
						view.getTextAreaStrokeResults().setText("");
						Document doc = view.getTextAreaStrokeResults().getDocument();
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
