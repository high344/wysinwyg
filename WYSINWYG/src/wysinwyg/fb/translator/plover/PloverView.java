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
package wysinwyg.fb.translator.plover;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class PloverView extends JPanel {

	private static final long serialVersionUID = 3190747948465279046L;

	private JTextArea textAreaTranslation;
	private JTextArea textAreaStrokeResults;
	private JTextArea textAreaStrokes;

	public PloverView() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Translation:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_3.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setPreferredSize(new Dimension(200, 55));
		panel_2.add(scrollPane_2);

		textAreaTranslation = new JTextArea();
		textAreaTranslation.setEditable(false);
		scrollPane_2.setViewportView(textAreaTranslation);
		textAreaTranslation.setLineWrap(true);
		textAreaTranslation.setRows(3);

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Stroke results:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setPreferredSize(new Dimension(250, 125));
		panel_4.add(scrollPane_1);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		textAreaStrokeResults = new JTextArea();
		textAreaStrokeResults.setEditable(false);
		scrollPane_1.setViewportView(textAreaStrokeResults);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Strokes:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.add(panel_5, BorderLayout.WEST);
		panel_5.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(205, 125));

		textAreaStrokes = new JTextArea();
		textAreaStrokes.setEditable(false);
		scrollPane.setViewportView(textAreaStrokes);
	}

	public JTextArea getTextAreaTranslation() {
		return textAreaTranslation;
	}

	public JTextArea getTextAreaStrokeResults() {
		return textAreaStrokeResults;
	}

	public JTextArea getTextAreaStrokes() {
		return textAreaStrokes;
	}

}
