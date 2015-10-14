package wysinwyg;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class WysinwygControlPanel extends JPanel {

	private static final long serialVersionUID = 7398154840465421546L;

	private JToggleButton tglbtnStart;

	public WysinwygControlPanel() {
		setBorder(new TitledBorder(null, "Control", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		tglbtnStart = new JToggleButton("Start");
		add(tglbtnStart);
	}

	public JToggleButton getTglbtnStart() {
		return tglbtnStart;
	}

}
