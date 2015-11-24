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
package wysinwyg.utils.serial;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class SerialView extends JPanel {

	private static final long serialVersionUID = -4411609601292811983L;
	
	private JComboBox<String> comboBoxPortName;
	private JButton btnScan;
	private JComboBox<Integer> comboBoxBaud;
	private JComboBox<Integer> comboBoxDataB;
	private JComboBox<Double> comboBoxStopB;
	private JComboBox<String> comboBoxParity;
	private JCheckBox chckbxTimeout;
	private JTextField textFieldTimeout;
	private JCheckBox chckbxRTSCTS;
	private JCheckBox chckbxXonXoff;
	private JButton btnSet;
	private JButton btnCancel;
	
	public SerialView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Connection:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_4);

		JLabel lblNewLabel = new JLabel("Port:");
		panel_4.add(lblNewLabel);

		comboBoxPortName = new JComboBox<String>();
		comboBoxPortName.setEditable(true);
		comboBoxPortName.setPreferredSize(new Dimension(100, 20));
		comboBoxPortName.setMinimumSize(new Dimension(50, 20));
		panel_4.add(comboBoxPortName);

		btnScan = new JButton("Scan");
		panel_4.add(btnScan);

		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_5);

		JLabel lblNewLabel_1 = new JLabel("Baudrate:");
		panel_5.add(lblNewLabel_1);

		comboBoxBaud = new JComboBox<Integer>(SerialModel.baudrate);
		panel_5.add(comboBoxBaud);

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Data Format:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setLayout(new GridLayout(3, 6, 5, 5));

		JLabel lblNewLabel_2 = new JLabel("Data Bits:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_2);

		comboBoxDataB = new JComboBox<Integer>(SerialModel.databits);
		panel_1.add(comboBoxDataB);

		JLabel lblNewLabel_3 = new JLabel("Stop Bits:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_3);

		comboBoxStopB = new JComboBox<Double>(SerialModel.stopbits);
		panel_1.add(comboBoxStopB);

		JLabel lblNewLabel_4 = new JLabel("Parity:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblNewLabel_4);

		comboBoxParity = new JComboBox<String>(SerialModel.parity);
		panel_1.add(comboBoxParity);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_2.setBorder(new TitledBorder(null, "Timeout:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel_2);

		chckbxTimeout = new JCheckBox("Use Timeout");
		panel_2.add(chckbxTimeout);

		textFieldTimeout = new JTextField();
		panel_2.add(textFieldTimeout);
		textFieldTimeout.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("seconds");
		panel_2.add(lblNewLabel_5);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_3.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_3.setBorder(new TitledBorder(null, "Flow Control:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		add(panel_3);

		chckbxRTSCTS = new JCheckBox("RTS / CTS");
		panel_3.add(chckbxRTSCTS);

		chckbxXonXoff = new JCheckBox("Xon / Xoff");
		panel_3.add(chckbxXonXoff);

		JPanel panel_6 = new JPanel();
		add(panel_6);

		btnSet = new JButton("Set");
		panel_6.add(btnSet);

		btnCancel = new JButton("Cancel");
		panel_6.add(btnCancel);
	}

}
