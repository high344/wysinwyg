package wysinwyg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

public class WysinwygController implements Controller, ActionListener {

	private JToggleButton tglbtnStart;
	private WysinwygModel model;

	WysinwygController(WysinwygModel model, WysinwygView view) {
		this.model = model;
		
		tglbtnStart = view.controlPanel.getTglbtnStart();
		tglbtnStart.getInputMap(JComponent.WHEN_FOCUSED).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "none");

		tglbtnStart.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tglbtnStart) {
			if (tglbtnStart.isSelected()) {
				model.start();
			} else {
				model.stop();
			}
		}
	}

}
