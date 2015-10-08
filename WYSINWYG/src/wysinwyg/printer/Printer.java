package wysinwyg.printer;

public interface Printer {

	public void print(String printedString, int removeCount);
	@Deprecated
	public void change(String printedString, String removedString);
	
}
