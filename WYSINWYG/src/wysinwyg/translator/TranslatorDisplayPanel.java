package wysinwyg.translator;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.SystemColor;

import javax.swing.JScrollPane;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.FlowLayout;

import javax.swing.border.TitledBorder;

public class TranslatorDisplayPanel extends JPanel {

	private static final long serialVersionUID = 3190747948465279046L;
	
	JTextField textFieldStroke;
	JTextField textFieldTranslation;
	JTextArea textAreaStrokes;
	
	public TranslatorDisplayPanel() {
		setBorder(new TitledBorder(null, "Display:", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("Strokes:");
		panel_2.add(lblNewLabel);

		textFieldStroke = new JTextField();
		textFieldStroke.setBackground(SystemColor.text);
		textFieldStroke.setEditable(false);
		panel_2.add(textFieldStroke);
		textFieldStroke.setColumns(30);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		JLabel lblNewLabel_1 = new JLabel("Translation:");
		panel_3.add(lblNewLabel_1);

		textFieldTranslation = new JTextField();
		textFieldTranslation.setBackground(SystemColor.text);
		textFieldTranslation.setEditable(false);
		panel_3.add(textFieldTranslation);
		textFieldTranslation.setColumns(30);

		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane_1);

		textAreaStrokes = new JTextArea();
		textAreaStrokes.setEditable(false);
		scrollPane_1.setViewportView(textAreaStrokes);
	}



}
