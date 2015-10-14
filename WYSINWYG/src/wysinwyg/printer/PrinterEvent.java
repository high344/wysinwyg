package wysinwyg.printer;

public class PrinterEvent {

	private String print;
	private int command;

	public PrinterEvent(String printedString) {
		this.print = printedString;
	}

	public PrinterEvent(int commandValue) {
		this.command = commandValue;
	}

	public String getPrint() {
		return print;
	}

	public Integer getCommand() {
		return command;
	}

	public boolean isCommand() {
		return (print == null);
	}

}