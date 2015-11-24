package wysinwyg.fb.device.stentura;

import wysinwyg.fb.evaluator.steno.StenoOrder;
import wysinwyg.fb.evaluator.steno.StenoReference;

public class StenoRefrenceStentura implements StenoReference {

	// Byte 1
	/*
	 * private static final int SM = 0b00100000; // StenoMark 0
	 */
	private static final int HM = 0b00010000;
	private static final int S_ = 0b00001000;
	private static final int T_ = 0b00000100;
	private static final int K_ = 0b00000010;
	private static final int P_ = 0b00000001; // 5

	// Byte 2
	private static final int W_ = 0b01100000; // 6
	private static final int H_ = 0b01010000;
	private static final int R_ = 0b01001000;
	private static final int A_ = 0b01000100;
	private static final int O_ = 0b01000010;
	private static final int ST = 0b01000001; // 11

	// Byte 3
	private static final int _E = 0b10100000; // 12
	private static final int _U = 0b10010000;
	private static final int _F = 0b10001000;
	private static final int _R = 0b10000100;
	private static final int _P = 0b10000010;
	private static final int _B = 0b10000001; // 17

	// Byte 4
	private static final int _L = 0b11100000; // 18
	private static final int _G = 0b11010000;
	private static final int _T = 0b11001000;
	private static final int _S = 0b11000100;
	private static final int _D = 0b11000010;
	private static final int _Z = 0b11000001; // 23

	@Override
	public StenoOrder getStenoReference(int code) {
		switch (code) {
		case HM:
			return StenoOrder.hm;
		case S_:
			return StenoOrder.S_;
		case T_:
			return StenoOrder.T_;
		case K_:
			return StenoOrder.K_;
		case P_:
			return StenoOrder.P_;
		case W_:
			return StenoOrder.W_;
		case H_:
			return StenoOrder.H_;
		case R_:
			return StenoOrder.R_;
		case A_:
			return StenoOrder.A_;
		case O_:
			return StenoOrder.O_;
		case ST:
			return StenoOrder.st;
		case _E:
			return StenoOrder._E;
		case _U:
			return StenoOrder._U;
		case _F:
			return StenoOrder._F;
		case _R:
			return StenoOrder._R;
		case _P:
			return StenoOrder._P;
		case _B:
			return StenoOrder._B;
		case _L:
			return StenoOrder._L;
		case _G:
			return StenoOrder._G;
		case _T:
			return StenoOrder._T;
		case _S:
			return StenoOrder._S;
		case _D:
			return StenoOrder._D;
		case _Z:
			return StenoOrder._Z;
		}
		return null;
	}
}
