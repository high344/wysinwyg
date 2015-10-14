package wysinwyg.evaluator.steno;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import wysinwyg.utils.StenturaLayout;

public class StenoView extends JPanel {

	private static final long serialVersionUID = -3028516136522939678L;

	private JCheckBox chckbxArpeggiate;
	private StenturaLayout stentura;

	public StenoView() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel_1.add(panel);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		chckbxArpeggiate = new JCheckBox("Arpeggiate");
		panel.add(chckbxArpeggiate);
		stentura = new StenturaLayout();
		panel_1.add(stentura);
	}

	public JCheckBox getChckbxArpeggiate() {
		return chckbxArpeggiate;
	}

	public StenturaLayout getStentura() {
		return stentura;
	}

}