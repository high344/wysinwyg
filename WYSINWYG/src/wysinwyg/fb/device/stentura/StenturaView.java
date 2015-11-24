package wysinwyg.fb.device.stentura;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import wysinwyg.utils.StenturaLayout;
import wysinwyg.utils.serial.SerialView;

public class StenturaView extends JPanel {

	private static final long serialVersionUID = -3816282299811234417L;

	public StenturaView() {
		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Layout", null, panel_2, null);

		StenturaLayout panel = new StenturaLayout();
		panel_2.add(panel);
		GridBagLayout gridBagLayout = (GridBagLayout) panel.getLayout();
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0 };

		SerialView panel_1 = new SerialView();
		tabbedPane.addTab("Serial Communication", null, panel_1, null);
	}

}
