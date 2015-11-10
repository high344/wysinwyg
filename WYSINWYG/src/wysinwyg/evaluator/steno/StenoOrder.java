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
package wysinwyg.evaluator.steno;

/**
 * Enum containing the steno values in this order:
 * 
 * <pre>
 * #  0
 * S- 1
 * T- 2
 * K- 3
 * P- 4
 * W- 5
 * H- 6
 * R- 7
 * A- 8
 * O- 9
 * *  10
 * -E 11
 * -U 12
 * -F 13
 * -R 14
 * -P 15
 * -B 16
 * -L 17
 * -G 18
 * -T 19
 * -S 20
 * -D 21
 * -Z 22
 * </pre>
 * 
 * @author FelfoldiB.
 *
 */
public enum StenoOrder {

	hm("#", "#"), S_("S-", "S"), T_("T-", "T"), K_("K-", "K"), P_("P-", "P"), W_("W-", "W"), H_(
			"H-", "H"), R_("R-", "R"), A_("A-", "A"), O_("O-", "O"), st("*", "*"), _E("-E", "E"), _U(
			"-U", "U"), _F("-F", "F"), _R("-R", "R"), _P("-P", "P"), _B("-B", "B"), _L("-L", "L"), _G(
			"-G", "G"), _T("-T", "T"), _S("-S", "S"), _D("-D", "D"), _Z("-Z", "Z");

	private String name;
	private String simpleName;

	private StenoOrder(String name, String simpleName) {
		this.name = name;
		this.simpleName = simpleName;
	}

	public int getValue() {
		return this.ordinal();
	}

	public String getName() {
		return name;
	}

	public String getSimpleName() {
		return simpleName;
	}

}
