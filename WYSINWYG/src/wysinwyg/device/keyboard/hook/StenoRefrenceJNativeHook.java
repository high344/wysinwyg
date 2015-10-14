package wysinwyg.device.keyboard.hook;

import org.jnativehook.keyboard.NativeKeyEvent;

import wysinwyg.evaluator.steno.StenoOrder;
import wysinwyg.evaluator.steno.StenoRefrence;

public class StenoRefrenceJNativeHook implements StenoRefrence {

	@Override
	public StenoOrder getStenoRefrence(int keyCode) {
		switch (keyCode) {
		case NativeKeyEvent.VC_Q:
			return StenoOrder.S_;
		case NativeKeyEvent.VC_A:
			return StenoOrder.S_;
		case NativeKeyEvent.VC_W:
			return StenoOrder.T_;
		case NativeKeyEvent.VC_S:
			return StenoOrder.K_;
		case NativeKeyEvent.VC_E:
			return StenoOrder.P_;
		case NativeKeyEvent.VC_D:
			return StenoOrder.W_;
		case NativeKeyEvent.VC_R:
			return StenoOrder.H_;
		case NativeKeyEvent.VC_F:
			return StenoOrder.R_;
		case NativeKeyEvent.VC_C:
			return StenoOrder.A_;
		case NativeKeyEvent.VC_V:
			return StenoOrder.O_;
		case NativeKeyEvent.VC_N:
			return StenoOrder._E;
		case NativeKeyEvent.VC_M:
			return StenoOrder._U;
		case NativeKeyEvent.VC_U:
			return StenoOrder._F;
		case NativeKeyEvent.VC_J:
			return StenoOrder._R;
		case NativeKeyEvent.VC_I:
			return StenoOrder._P;
		case NativeKeyEvent.VC_K:
			return StenoOrder._B;
		case NativeKeyEvent.VC_O:
			return StenoOrder._L;
		case NativeKeyEvent.VC_L:
			return StenoOrder._G;
		case NativeKeyEvent.VC_P:
			return StenoOrder._T;
		case NativeKeyEvent.VC_SEMICOLON:
			return StenoOrder._S;
		case NativeKeyEvent.VC_OPEN_BRACKET:
			return StenoOrder._D;
		case NativeKeyEvent.VC_QUOTE:
			return StenoOrder._Z;
		case NativeKeyEvent.VC_0:
		case NativeKeyEvent.VC_1:
		case NativeKeyEvent.VC_2:
		case NativeKeyEvent.VC_3:
		case NativeKeyEvent.VC_4:
		case NativeKeyEvent.VC_5:
		case NativeKeyEvent.VC_6:
		case NativeKeyEvent.VC_7:
		case NativeKeyEvent.VC_8:
		case NativeKeyEvent.VC_9:
			return StenoOrder.hm;
		case NativeKeyEvent.VC_T:
		case NativeKeyEvent.VC_G:
		case NativeKeyEvent.VC_Z:
		case NativeKeyEvent.VC_H:
			return StenoOrder.st;
		}
		return null;
	}
	
}
