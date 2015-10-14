package wysinwyg.translator;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

import wysinwyg.Controller;

public class TranslatorController implements Controller, ItemListener {

	private TranslatorModel model;
	private TranslatorView view;

	public TranslatorController(TranslatorModel model, TranslatorView view) {
		Objects.requireNonNull(model);
		Objects.requireNonNull(view);

		this.model = model;
		this.view = view;

		view.getComboBox().addItemListener(this);
	}

	public Translator getSelectedTranslator() {
		return ((Translator) view.getComboBox().getSelectedItem());
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout) (view.getCardsPanel().getLayout());
		cl.show(view.getCardsPanel(),
				((Translator) view.getComboBox().getSelectedItem()).getDisplayName());
		model.getDictionary().getController()
				.setNewTranslator((Translator) view.getComboBox().getSelectedItem());
	}

}
