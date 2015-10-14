package wysinwyg.device;

import wysinwyg.utils.ComboboxDisplayName;

public interface Device extends ComboboxDisplayName {

	public void addDeviceListener(DeviceListener devListener);

	public void removeDeviceListener(DeviceListener devListener);

	public void startDevice();

	public void stopDevice();

}
