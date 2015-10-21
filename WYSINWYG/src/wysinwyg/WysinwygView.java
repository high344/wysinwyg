/*******************************************************************************
 * Copyright (c) 2015 Balazs Felfoldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balazs Felfoldi - initial API and implementation
 ******************************************************************************/
package wysinwyg;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import wysinwyg.device.DeviceView;
import wysinwyg.evaluator.EvaluatorView;
import wysinwyg.translator.TranslatorView;

/**
 * JPanel with a {@linkplain WysinwygControlPanel} on upper left side. A
 * possible {@linkplain DeviceView} on the upper center. A possible
 * {@linkplain EvaluatorView} on the upper right side and a possible
 * {@linkplain TranslatorView} in the middle. All acquired from a
 * {@linkplain WysinwygModel}
 * 
 * <pre>
 * |---------------------------------------------------|
 * | WysinwygControlPanel | DeviceView | EvaluatorView |
 * |---------------------------------------------------|
 * |                      TranslatorView               |
 * |---------------------------------------------------|
 * </pre>
 * 
 * @author FelfoldiB.
 *
 */
public class WysinwygView extends JPanel {

	private static final long serialVersionUID = 2364150470979116210L;

	private WysinwygModel model;
	private WysinwygControlPanel controlPanel;

	/**
	 * Creating a JPanel from the {@code model} elements. If the {@code model}
	 * is {@code null} only a WysinwygControlPanel will be set.
	 * 
	 * @see WysinwygView
	 * @param model
	 *            can be {@code null}.
	 */
	public WysinwygView(WysinwygModel model) {
		this.model = model;
		buildGUI();
	}

	/**
	 * 
	 * @return a WysinwygControlPanel class.
	 * @see WysinwygControlPanel
	 */
	public WysinwygControlPanel getControlPanel() {
		return controlPanel;
	}

	private void buildGUI() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
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
