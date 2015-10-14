package wysinwyg.device.serial.stentura;

import java.awt.Component;

import wysinwyg.Controller;
import wysinwyg.Init;
import wysinwyg.device.Device;
import wysinwyg.device.DeviceListener;
import wysinwyg.utils.StenturaLayout;

public class StenturaDevice implements Init, Device {

	private StenturaLayout panel = new StenturaLayout();

	@Override
	public Component getView() {
		// TODO Auto-generated method stub
		return panel;
	}

	@Override
	public Controller getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return "Stentura";
	}

	@Override
	public void addDeviceListener(DeviceListener devListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDevice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopDevice() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeDeviceListener(DeviceListener devListener) {
		// TODO Auto-generated method stub
		
	}

	
}
