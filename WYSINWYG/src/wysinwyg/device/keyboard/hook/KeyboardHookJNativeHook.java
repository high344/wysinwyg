package wysinwyg.device.keyboard.hook;

import java.lang.reflect.Field;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import wysinwyg.device.DeviceEvent;
import wysinwyg.device.DeviceListener;

public class KeyboardHookJNativeHook extends AbstractKeyboardHook implements NativeKeyListener {

	private Field f;

	public KeyboardHookJNativeHook(DeviceListener devListener) {
		super(devListener, false);
		GlobalScreen.setEventDispatcher(new DispatchService());
		GlobalScreen.addNativeKeyListener(this);
		try {
			f = NativeInputEvent.class.getDeclaredField("reserved");
			f.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enableHook() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void disableHook() {
		try {
			GlobalScreen.unregisterNativeHook();
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nke) {
		try {
			DeviceEvent event = new DeviceEvent(this, nke.getKeyCode(), nke.getRawCode(),
					DeviceEvent.DEVICE_KEY_PRESSED);
			deviceEventOccurred(event);
			if (event.isConsumeEnabled()) {
				f.setShort(nke, (short) 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nke) {
		try {
			DeviceEvent event = new DeviceEvent(this, nke.getKeyCode(), nke.getRawCode(),
					DeviceEvent.DEVICE_KEY_RELEASED);
			if (event.isConsumeEnabled()) {
				f.setShort(nke, (short) 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nke) {

	}

}
