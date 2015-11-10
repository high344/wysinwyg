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
package wysinwyg.utils;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class StenturaLayout extends JPanel {

	private static final long serialVersionUID = -3732867237085126757L;

	protected String[] sten1 = { "#", "hm", "S", "S_", "T", "T_", "P", "P_", "H", "H_", "*", "st",
			"F", "_F", "P", "_P", "L", "_L", "T", "_T", "D", "_D" };
	protected String[] sten2 = { "K", "K_", "W", "W_", "R", "R_", "R", "_R", "B", "_B", "G", "_G",
			"S", "_S", "Z", "_Z" };
	protected String[] sten3 = { "A", "A_", "O", "O_", "E", "_E", "U", "_U" };

	protected Map<String, JToggleButton> map = new HashMap<String, JToggleButton>(23);

	public StenturaLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		addJToggleButton(sten1[0], sten1[1], 0, 0);

		for (int i = 2, c = 0; i < sten1.length; i++, c++) {
			addJToggleButton(sten1[i], sten1[++i], c, 1);
		}

		for (int i = 0, c = 1; i < sten2.length; i++, c++) {
			if (c == 4) {
				i--;
				continue;
			}
			addJToggleButton(sten2[i], sten2[++i], c, 2);
		}

		for (int i = 0, c = 2; i < sten3.length; i++, c++) {
			if (c == 4) {
				i--;
				continue;
			}
			addJToggleButton(sten3[i], sten3[++i], c, 3);
		}

	}

	private void addJToggleButton(String text, String actionCmd, int gridx, int gridy) {
		JToggleButton btn = new JToggleButton(text);

		GridBagConstraints gbc_btn = new GridBagConstraints();
		gbc_btn.fill = GridBagConstraints.BOTH;
		Dimension dim = null;
		if (actionCmd.equals("hm")) {
			dim = new Dimension(41, 50);
			gbc_btn.gridwidth = 10;
		} else if (actionCmd.equals("S_") || actionCmd.equals("st")) {
			dim = new Dimension(50, 100);
			gbc_btn.gridheight = 2;
		} else {
			dim = new Dimension(50, 50);
		}
		btn.setMinimumSize(dim);
		btn.setPreferredSize(dim);
		btn.setMaximumSize(dim);
		btn.setActionCommand(actionCmd);
		gbc_btn.insets = new Insets(0, 0, 5, 5);
		gbc_btn.gridx = gridx;
		gbc_btn.gridy = gridy;
		add(btn, gbc_btn);
		map.put(actionCmd, btn);
	}

}
