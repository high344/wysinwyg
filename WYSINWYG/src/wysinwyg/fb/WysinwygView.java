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
package wysinwyg.fb;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import wysinwyg.fw.device.DeviceView;
import wysinwyg.fw.evaluator.EvaluatorView;

/**
 * A {@linkplain JPanel} with a {@linkplain WysinwygControlPanel} and
 * {@linkplain DeviceView}.
 * 
 * <pre>
 * |-----------------------------------|
 * | WysinwygControlPanel | DeviceView |
 * |-----------------------------------|
 * </pre>
 * 
 * @author FelfoldiB.
 *
 */
public class WysinwygView extends JPanel {

	private static final long serialVersionUID = 2364150470979116210L;

	private WysinwygControlPanel controlPanel;
	private DeviceView deviceView;
	private EvaluatorView evaluatorView;

	/**
	 * Creating the {@linkplain JPanel} specified in the
	 * {@linkplain WysinwygView}.
	 */
	public WysinwygView() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout(5, 5));

		controlPanel = new WysinwygControlPanel();
		panel_1.add(controlPanel, BorderLayout.WEST);

		deviceView = new DeviceView();
		panel_1.add(deviceView, BorderLayout.CENTER);
		
		evaluatorView = new EvaluatorView();
		panel_1.add(evaluatorView, BorderLayout.EAST);

		scrollPane.setViewportView(panel_1);
	}

	/**
	 * 
	 * @return a {@linkplain WysinwygControlPanel}.
	 * @see {@linkplain WysinwygView}.
	 */
	public WysinwygControlPanel getControlPanel() {
		return controlPanel;
	}

	/**
	 * 
	 * @return a {@linkplain DeviceView}.
	 * @see {@linkplain WysinwygView}.
	 */
	public DeviceView getDeviceView() {
		return deviceView;
	}
	
	/**
	 * 
	 * @return a {@linkplain DeviceView}.
	 * @see {@linkplain WysinwygView}.
	 */
	public EvaluatorView getEvaluatorView() {
		return evaluatorView;
	}

}
