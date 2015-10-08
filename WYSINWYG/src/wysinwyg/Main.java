package wysinwyg;


public class Main {
	
	public static void main(String[] args) {
		new WysinwygInit();
	}
	
	/*
	public Main() {
		StenoRefrenceInterface refrence = new StenoRefrenceWin32();
		IPrinter printer = new WinPrinter();
		PloverTranslator translator = new PloverTranslator();
		JSonDictionary dict = new JSonDictionary("/resources/dict.json");
		StenoEvaluator steno = new StenoEvaluator(false, refrence, translator, printer, dict);
		KeyboardHookWin32 hook = new KeyboardHookWin32(steno, 57);
	}*/
	
	/*
	public Main() {
		//ViewPanel view= new ViewPanel();
		//GUIFrame frame = new GUIFrame("Paper", view);
		//KeyboardEvaluator keyboardEvaluator = new KeyboardEvaluator();
		//KeyboardHookJNativeHook keyboardListener = new KeyboardHookJNativeHook();
		StenoRefrenceInterface refrence = new StenoRefrenceWin32();
		KeyboardHookJNativeHook keyboardListener = new KeyboardHookJNativeHook(refrence);
		try {
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.WARNING);
			GlobalScreen.setEventDispatcher(new DispatchService());
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeKeyListener(keyboardListener);
		} catch (NativeHookException e) {
			System.err.println("There was a problem registering the native hook.");
            System.err.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
}