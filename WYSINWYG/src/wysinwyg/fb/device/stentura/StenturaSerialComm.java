package wysinwyg.fb.device.stentura;

import wysinwyg.fw.device.serial.SerialComm;
import wysinwyg.fw.device.serial.SerialController;

public class StenturaSerialComm implements SerialComm {

	private SerialController controller;
	
	public StenturaSerialComm() {
		// TODO Auto-generated constructor stub
	}
	
	protected void setSerialController(SerialController controller) {
		this.controller = controller;
	}
	
	@Override
	public void btnScanActionOccurred() {
		// TODO Auto-generated method stub
	}

	@Override
	public void btnSetActionOccurred() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void btnCancelOccurred() {
		// TODO Auto-generated method stub
		
	}

}
