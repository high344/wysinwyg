package wysinwyg.fb.device.stentura;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import wysinwyg.fw.device.serial.SerialView;
import wysinwyg.utils.StenturaLayout;

public class StenturaView extends JPanel {

	private static final long serialVersionUID = -3816282299811234417L;

	private StenturaLayout stenturaLayout;
	private SerialView serialView;
	
	public StenturaView() {
		setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Layout", null, panel_2, null);

		stenturaLayout = new StenturaLayout();
		panel_2.add(stenturaLayout);

		serialView = new SerialView();
		tabbedPane.addTab("Serial Communication", null, serialView, null);
	}
	
	public StenturaLayout getStenturaLayout() {
		return stenturaLayout;
	}

	public SerialView getSerialView() {
		return serialView;
	}
	
}
