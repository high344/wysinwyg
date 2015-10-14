package wysinwyg.device.keyboard.hook;

import wysinwyg.evaluator.steno.StenoOrder;
import wysinwyg.evaluator.steno.StenoRefrence;

public class StenoRefrenceWin32 implements StenoRefrence {

	private static final int VK_Q = 0x51;
	private static final int VK_A = 0x41;
	private static final int VK_W = 0x57;
	private static final int VK_S = 0x53;
	private static final int VK_E = 0x45;
	private static final int VK_D = 0x44;
	private static final int VK_R = 0x52;
	private static final int VK_F = 0x46;
	private static final int VK_C = 0x43;
	private static final int VK_V = 0x56;
	private static final int VK_N = 0x4E;
	private static final int VK_M = 0x4D;
	private static final int VK_U = 0x55;
	private static final int VK_J = 0x4A;
	private static final int VK_I = 0x49;
	private static final int VK_K = 0x4B;
	private static final int VK_O = 0x4F;
	private static final int VK_L = 0x4C;
	private static final int VK_P = 0x50;
	private static final int VK_SEMICOLON = 0xBA;
	private static final int VK_OPEN_BRACKET = 0xDB;
	private static final int VK_QUOTE = 0xDE;
	private static final int VK_0 = 0x30;
	private static final int VK_1 = 0x31;
	private static final int VK_2 = 0x32;
	private static final int VK_3 = 0x33;
	private static final int VK_4 = 0x34;
	private static final int VK_5 = 0x35;
	private static final int VK_6 = 0x36;
	private static final int VK_7 = 0x37;
	private static final int VK_8 = 0x38;
	private static final int VK_9 = 0x39;
	private static final int VK_T = 0x54;
	private static final int VK_G = 0x47;
	private static final int VK_Z = 0x5A;
	private static final int VK_H = 0x48;
	
	@Override
	public StenoOrder getStenoRefrence(int code) {
		switch (code) {
		case VK_Q:
			return StenoOrder.S_;
		case VK_A:
			return StenoOrder.S_;
		case VK_W:
			return StenoOrder.T_;
		case VK_S:
			return StenoOrder.K_;
		case VK_E:
			return StenoOrder.P_;
		case VK_D:
			return StenoOrder.W_;
		case VK_R:
			return StenoOrder.H_;
		case VK_F:
			return StenoOrder.R_;
		case VK_C:
			return StenoOrder.A_;
		case VK_V:
			return StenoOrder.O_;
		case VK_N:
			return StenoOrder._E;
		case VK_M:
			return StenoOrder._U;
		case VK_U:
			return StenoOrder._F;
		case VK_J:
			return StenoOrder._R;
		case VK_I:
			return StenoOrder._P;
		case VK_K:
			return StenoOrder._B;
		case VK_O:
			return StenoOrder._L;
		case VK_L:
			return StenoOrder._G;
		case VK_P:
			return StenoOrder._T;
		case VK_SEMICOLON:
			return StenoOrder._S;
		case VK_OPEN_BRACKET:
			return StenoOrder._D;
		case VK_QUOTE:
			return StenoOrder._Z;
		case VK_0:
		case VK_1:
		case VK_2:
		case VK_3:
		case VK_4:
		case VK_5:
		case VK_6:
		case VK_7:
		case VK_8:
		case VK_9:
			return StenoOrder.hm;
		case VK_T:
		case VK_G:
		case VK_Z:
		case VK_H:
			return StenoOrder.st;
		}
		return null;
	}
	
}
