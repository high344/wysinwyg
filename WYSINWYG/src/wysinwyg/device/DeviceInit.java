package wysinwyg.device;

import wysinwyg.Init;

public class DeviceInit implements Init {

	private DeviceModel model;
	private DeviceView view;
	private DeviceController controller;

	public DeviceInit() {
		model = new DeviceModel();
		view = new DeviceView(model);
		controller = new DeviceController(model, view);
	}

	@Override
	public DeviceView getView() {
		return view;
	}

	public DeviceController getController() {
		return controller;
	}

}
