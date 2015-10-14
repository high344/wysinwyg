package wysinwyg.printer;

import wysinwyg.device.DeviceEvent;

public interface Printer {

	public boolean isDeviceEventVirtual(DeviceEvent e);

	public void addPrinterEvent(PrinterEvent e);

}
