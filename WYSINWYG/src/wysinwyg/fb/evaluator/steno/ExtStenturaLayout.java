package wysinwyg.fb.evaluator.steno;

import java.awt.event.MouseListener;

import javax.swing.JToggleButton;

import wysinwyg.utils.StenturaLayout;

public class ExtStenturaLayout extends StenturaLayout {

	private static final long serialVersionUID = -3747293377727146545L;

	public JToggleButton getJToggleButton(String actionCommand) {
		return map.get(actionCommand);
	}

	public void setAllJToggleButtonSelected(boolean b) {
		for (JToggleButton gt : map.values()) {
			gt.setSelected(b);
		}
	}

	public void removeAllJToggleButtonMouseListener() {
		for (JToggleButton gt : map.values()) {
			for (MouseListener ml : gt.getMouseListeners()) {
				gt.removeMouseListener(ml);
			}
		}
	}

}
