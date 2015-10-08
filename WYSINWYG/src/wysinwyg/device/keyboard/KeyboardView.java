package wysinwyg.device.keyboard;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

public class KeyboardView extends JPanel {

	private static final long serialVersionUID = 459644234125637827L;

	JCheckBox chckbxEcho;

	public KeyboardView() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		chckbxEcho = new JCheckBox("Echo");
		add(chckbxEcho);
	}

}
