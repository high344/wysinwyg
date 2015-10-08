package wysinwyg.evaluator.steno;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import wysinwyg.utils.StenturaLayout;
import java.awt.FlowLayout;

public class StenoView extends JPanel {

	private static final long serialVersionUID = -3028516136522939678L;

	JCheckBox chckbxArpeggiate;
	StenturaLayout stentura;
	private JPanel panel_1;

	public StenoView() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_1 = new JPanel();
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

}
