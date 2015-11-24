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
package wysinwyg.fw.device;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

import wysinwyg.fw.Init;
import wysinwyg.utils.ComboboxListCellRenderer;

/**
 * JPanel with a JComboBox containing {@linkplain Device devices}. If the device
 * is an instance of the {@linkplain Init init MVC} than the View component is
 * also added in a CardLayout form. If the View is {@code null}, a new JPanel is
 * added. A JTextArea of the occurring {@linkplain DeviceEvent device events} is
 * also present at the bottom.
 * 
 * <pre>
 * |------------------------|
 * |        JComboBox       |
 * |------------------------|
 * | CardLayout: DeviceView |
 * |------------------------|
 * |        JTextArea       |
 * |------------------------|
 * </pre>
 * 
 * @author FelfoldiB.
 *
 */
public class DeviceView extends JPanel {

	private static final long serialVersionUID = 1550900345407909849L;

	private DeviceModel model;
	private JComboBox<Device> comboBox;
	private JPanel cardsPanel;
	private JTextArea textArea;

	/**
	 * Creating a JPanel from the {@code model} elements, if it's {@code null}
	 * an empty JComboBox will still be present.
	 * 
	 * @see DeviceView
	 * @param model
	 *            can be {@code null}.
	 */
	public DeviceView(DeviceModel model) {
		this.model = model;
		buildGUI();
	}

	private void buildGUI() {
		setBorder(new TitledBorder(null, "Device:", TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(panel, BorderLayout.NORTH);

		if (model != null && model.getDevices() != null) {
			comboBox = new JComboBox<Device>(model.getDevices());
		} else {
			comboBox = new JComboBox<Device>();
		}

		comboBox.setPreferredSize(new Dimension(150, 20));
		comboBox.setRenderer(new ComboboxListCellRenderer());
		panel.add(comboBox);

		cardsPanel = new JPanel();
		add(cardsPanel, BorderLayout.WEST);
		cardsPanel.setLayout(new CardLayout(0, 0));

		if (model != null && model.getDevices() != null) {
			for (Device d : model.getDevices()) {
				if (d instanceof Init) {
					Init init = (Init) d;
					if (init.getView() != null) {
						cardsPanel.add(init.getView(), d.getDisplayName());
					} else {
						cardsPanel.add(new JPanel(), d.getDisplayName());
					}
				}
			}
		}

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Device Input:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(300, 100));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		((DefaultCaret) textArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scrollPane.setViewportView(textArea);
	}

	/**
	 * 
	 * @return a JComboBox containing {@linkplain Device devices}.
	 */
	public JComboBox<Device> getComboBox() {
		return comboBox;
	}

	/**
	 * 
	 * @return a JPanel class, with CardLayout.
	 */
	public JPanel getCardsPanel() {
		return cardsPanel;
	}

	/**
	 * 
	 * @return a JTextArea class.
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

}
