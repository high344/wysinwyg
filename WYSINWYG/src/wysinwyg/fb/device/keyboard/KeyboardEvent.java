package wysinwyg.fb.device.keyboard;

import wysinwyg.fw.device.DeviceEvent;

public class KeyboardEvent extends DeviceEvent {

	private static final long serialVersionUID = 9200860879608474459L;

	private int vKeyCode;
	private int scanCode;
	private KeyboardKeyState keyState;
	private boolean consume;

	public KeyboardEvent(Object source, int vKeyCode, int scanCode, KeyboardKeyState keyState) {
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

	public KeyboardKeyState getKeyState() {
		return keyState;
	}

	public boolean isConsumable() {
		return consume;
	}

	public void setConsumeEnabled(boolean b) {
		consume = b;
	}

	@Override
	public String toString() {
		return "KeyboardEvent [vKeyCode=" + vKeyCode + ", scanCode=" + scanCode + ", keyState=" + keyState
				+ ", consume=" + consume + "]";
	}

}
