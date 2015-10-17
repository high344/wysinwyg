/*******************************************************************************
 * Copyright (c) 2015 Balázs Felföldi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Balázs Felföldi - initial API and implementation
 ******************************************************************************/
package wysinwyg;

public class Main {

	//TODO In need of a Mutex start...
	//TODO Whole buildup without UI...
	//FIXME DOCUMENTATION!
	//TODO MAVEN build Up?
	
	//TODO {@link DeviceModel} read up devices???
	//TODO {link KeyboardDevice} Misery with hooks and places!?
	//TODO LinuxKeyboardHook or jnative???
	//TODO {link KeyboardDevice} More flash and substance??
	//TODO Device Serial, RxTx, etc...
	//TODO Device serial whole TX Bolt protocol, simulation.
	//TODO Device serial whole Stentura protocol, simulation.
	//TODO Device serial whole Treal protocol, simulation.
	//TODO Device serial whole Passport protocol, simulation.
	//TODO Device serial whole Gemini PR protocol, simulation.
	
	//TODO {@link EvaluatorModel} read up evaluators..
	//TODO create a hun evaluator for the feelz.
	//TODO {@link StenoEvaluator} changeable key codes in a database, save, reload, etc...
	//TODO {@link StenoEvaluator} changeable arpeggiate key code, and a button...
	
	//TODO {@link TranslatorModel} read up translators..
	//TODO {@link DictionaryView} DictionaryTableCell vs. DictionaryTableCellRenderer
	//TODO {@link DictionaryTableModel} fireTableStructureChanged is overkill
	//TODO {@link DictionaryTableCell} problems when sizing???
	//TODO {@link PloverJSonDictionary} finish dictionary methods: change/remove/add/...
	//TODO {@link PloverTranslatorValidator} re think and finish the command references and etc...
	//TODO {@link PloverTranslator} build in the number references, read .json, .rtf.
	//TODO Plover strokes into a database, show longest fit with colored background...
	
	//TODO {@link Win32Printer} more complex virtual key handling. 
	//TODO {@link Win32Printer} sending all printing characters in one swoop.
	//TODO LinuxPrinter for virtual key handling.
	
	public static void main(String[] args) {
		new WysinwygInit();
	}

}
