package wysinwyg;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class WysinwygView extends JPanel {

	private static final long serialVersionUID = 2364150470979116210L;

	private WysinwygModel model;
	private WysinwygControlPanel controlPanel;

	public WysinwygView(WysinwygModel model) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		this.model = model;
		buildGUI();
	}

	public WysinwygControlPanel getControlPanel() {
		return controlPanel;
	}

	private void buildGUI() {
		setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5, 5));
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout(5, 5));

		controlPanel = new WysinwygControlPanel();
		panel_1.add(controlPanel, BorderLayout.WEST);

		if (model != null) {
			if (model.getDevice() != null) {
				panel_1.add(model.getDevice().getView(), BorderLayout.CENTER);
			}
			if (model.getEvaluator() != null) {
				panel_1.add(model.getEvaluator().getView(), BorderLayout.EAST);
			}
			panel.add(panel_1, BorderLayout.NORTH);
			if (model.getTranslator() != null) {
				panel.add(model.getTranslator().getView(), BorderLayout.CENTER);
			}
		}
		scrollPane.setViewportView(panel);
	}

}
