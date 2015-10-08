package wysinwyg.device;

import java.util.EventObject;

public class DeviceEvent extends EventObject {

	private static final long serialVersionUID = 2191122736234629451L;

	public static final int DEVICE_KEY_PRESSED = 0;
	public static final int DEVICE_KEY_RELEASED = 1;
	public static final boolean CONSUME_DEVICE_EVENT = true;

	private int vKeyCode;
	private int scanCode;
	private int keyState;
	private boolean consume;

	public DeviceEvent(Object source, int vKeyCode, int scanCode, int keyState) {
		super(source);
		this.vKeyCode = vKeyCode;
		this.scanCode = scanCode;
		this.keyState = keyState;
	}

	public int getvKeyCode() {
		return vKeyCode;
	}

	public int getScanCode() {
		return scanCode;
	}

	public int getKeyState() {
		return keyState;
	}

	public boolean isConsumeEnabled() {
		return consume;
	}

	public void setConsumeEnabled(boolean b) {
		consume = b;
	}

	@Override
	public String toString() {
		return "DeviceEvent [vKeyCode=" + vKeyCode + ", scanCode=" + scanCode + ", keyState="
				+ ((keyState == 0) ? "pressed" : "released") + ", consume=" + consume + "]";
	}

}
