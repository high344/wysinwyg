package wysinwyg.printer;


public class TestPrinter implements Printer {
	
	public void print(String printedString, int removeCount) {
		// printedString lehet null;
		System.out.println("REMOVE: "+removeCount+" ADD: "+printedString);
		// TODO Auto-generated method stub
	}

	public void change(String printedString, String removedString) {
		// printedString lehet null;
		// TODO Auto-generated method stub
		System.out.println("REMOVE: "+printedString+" ADD: "+removedString);
	}
	
}
