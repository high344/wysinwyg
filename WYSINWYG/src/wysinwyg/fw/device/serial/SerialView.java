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
package wysinwyg.fw.device.serial;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author FelfoldiB.
 *
 */
public class SerialView extends JPanel {

	private static final long serialVersionUID = -4411609601292811983L;

	private JComboBox<String> comboBoxPortName;
	private JButton btnScan;
	private JComboBox<Integer> comboBoxBaud;
	private JComboBox<Integer> comboBoxDataB;
	private JComboBox<String> comboBoxStopB;
	private JComboBox<String> comboBoxParity;
	private JCheckBox chckbxTimeout;
	private JTextField textFieldTimeout;
	private JCheckBox chckbxRTSCTS;
	private JCheckBox chckbxXonXoff;
	private JButton btnSave;
	private JButton btnReload;

	private JPanel currentOpt;
	private Object[][] opt = new Object[9][2];

	/**
	 * Creating the {@linkplain JPanel} specified in the {@linkplain SerialView} .
	 */
	public SerialView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel_9 = new JPanel();
		add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));

		currentOpt = new JPanel();
		currentOpt.setBorder(new CompoundBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Current options:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), new EmptyBorder(
				0, 20, 0, 0)));

		currentOpt.setLayout(new GridLayout(9, 1, 0, 0));

		createCurrentOptionPanels();

		JPanel panel_8 = new JPanel();
		panel_9.add(panel_8);
		panel_9.add(currentOpt);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel_8.add(panel);
		panel.setBorder(new TitledBorder(null, "Connection:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);

		JLabel lblNewLabel = new JLabel((String) opt[0][0]);
		panel_4.add(lblNewLabel);

		comboBoxPortName = new JComboBox<String>();
		comboBoxPortName.setEditable(true);
		comboBoxPortName.setPreferredSize(new Dimension(100, 20));
		comboBoxPortName.setMinimumSize(new Dimension(50, 20));
		panel_4.add(comboBoxPortName);

		btnScan = new JButton("Scan");
		panel_4.add(btnScan);

		JPanel panel_5 = new JPanel();
		panel.add(panel_5);

		JLabel lblNewLabel_1 = new JLabel((String) opt[1][0]);
		panel_5.add(lblNewLabel_1);
		comboBoxBaud = new JComboBox<Integer>();
		panel_5.add(comboBoxBaud);

		JPanel panel_1 = new JPanel();
		panel_8.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Data Format:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(new GridLayout(3, 6, 5, 5));

		JLabel lblNewLabel_2 = new JLabel((String) opt[2][0]);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_2);
		comboBoxDataB = new JComboBox<Integer>();
		panel_1.add(comboBoxDataB);

		JLabel lblNewLabel_3 = new JLabel((String) opt[3][0]);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_3);
		comboBoxStopB = new JComboBox<String>();
		panel_1.add(comboBoxStopB);

		JLabel lblNewLabel_4 = new JLabel((String) opt[4][0]);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_4);
		comboBoxParity = new JComboBox<String>();
		panel_1.add(comboBoxParity);

		JPanel panel_2 = new JPanel();
		panel_8.add(panel_2);
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.setBorder(new TitledBorder(null, (String) opt[5][0] + ":", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));

		chckbxTimeout = new JCheckBox("Use Timeout");
		panel_2.add(chckbxTimeout);

		textFieldTimeout = new JTextField();
		panel_2.add(textFieldTimeout);
		textFieldTimeout.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("seconds");
		panel_2.add(lblNewLabel_5);

		JPanel panel_3 = new JPanel();
		add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Flow Control:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(panel_7);

		chckbxRTSCTS = new JCheckBox((String) opt[7][0]);
		panel_7.add(chckbxRTSCTS);

		chckbxXonXoff = new JCheckBox((String) opt[8][0]);
		panel_7.add(chckbxXonXoff);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(null, "Control:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.add(verticalBox);

		verticalBox.add(Box.createVerticalGlue());
		JPanel panel_6 = new JPanel();
		verticalBox.add(panel_6);
		verticalBox.add(Box.createVerticalGlue());
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		btnSave = new JButton("Save");
		panel_6.add(btnSave);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut);

		btnReload = new JButton("Reload");
		panel_6.add(btnReload);
	}

	private void createCurrentOptionPanels() {
		String[] str = { "Port", "Baudrate", "Data Bits", "Stop Bits", "Parity", "Timeout", "Timeout sec", "RTS / CTS",
				"Xon / Xoff" };

		for (int i = 0; i < opt.length; i++) {
			opt[i][0] = str[i];
			opt[i][1] = new JLabel();

			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

			JLabel lbl = new JLabel(str[i] + ": ");
			panel.add(lbl);

			panel.add((JLabel) opt[i][1]);
			currentOpt.add(panel);
		}
	}

	protected JLabel getPortJLabel() {
		return (JLabel) opt[0][1];
	}

	protected JLabel getBaudrateJLabel() {
		return (JLabel) opt[1][1];
	}

	protected JLabel getDataBitsJLabel() {
		return (JLabel) opt[2][1];
	}

	protected JLabel getStopBitsJLabel() {
		return (JLabel) opt[3][1];
	}

	protected JLabel getParityJLabel() {
		return (JLabel) opt[4][1];
	}

	protected JLabel getTimeoutJLabel() {
		return (JLabel) opt[5][1];
	}

	protected JLabel getTimeoutSecJLabel() {
		return (JLabel) opt[6][1];
	}

	protected JLabel getRTSCTSJLabel() {
		return (JLabel) opt[7][1];
	}

	protected JLabel getXonXoffJLabel() {
		return (JLabel) opt[8][1];
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the port names.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<String> getComboBoxPortName() {
		return comboBoxPortName;
	}

	/**
	 * 
	 * @return a {@linkplain JButton} for scanning.
	 * @see {@linkplain SerialView}.
	 */
	protected JButton getBtnScan() {
		return btnScan;
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the baudrates.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<Integer> getComboBoxBaud() {
		return comboBoxBaud;
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the databits.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<Integer> getComboBoxDataB() {
		return comboBoxDataB;
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the stopbits.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<String> getComboBoxStopB() {
		return comboBoxStopB;
	}

	/**
	 * 
	 * @return a {@linkplain JComboBox} containing the parity values.
	 * @see {@linkplain SerialView}.
	 */
	protected JComboBox<String> getComboBoxParity() {
		return comboBoxParity;
	}

	/**
	 * 
	 * @return a {@linkplain JCheckBox} for the timeout.
	 * @see {@linkplain SerialView}.
	 */
	protected JCheckBox getChckbxTimeout() {
		return chckbxTimeout;
	}

	/**
	 * 
	 * @return a {@linkplain JTextField} for the timeout.
	 * @see {@linkplain SerialView}.
	 */
	protected JTextField getTextFieldTimeout() {
		return textFieldTimeout;
	}

	/**
	 * 
	 * @return a {@linkplain JCheckBox} for the RTS / CTS.
	 * @see {@linkplain SerialView}.
	 */
	protected JCheckBox getChckbxRTSCTS() {
		return chckbxRTSCTS;
	}

	/**
	 * 
	 * @return a {@linkplain JCheckBox} for the Xon / Xoff.
	 * @see {@linkplain SerialView}.
	 */
	protected JCheckBox getChckbxXonXoff() {
		return chckbxXonXoff;
	}

	/**
	 * 
	 * @return a {@linkplain JButton} for saving the values.
	 * @see {@linkplain SerialView}.
	 */
	protected JButton getBtnSave() {
		return btnSave;
	}

	/**
	 * 
	 * @return a {@linkplain JButton} for reloading the values.
	 * @see {@linkplain SerialView}.
	 */
	protected JButton getBtnReload() {
		return btnReload;
	}

}
