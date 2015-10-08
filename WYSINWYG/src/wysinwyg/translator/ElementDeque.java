package wysinwyg.translator;

import java.util.ArrayDeque;
import java.util.Iterator;

public class ElementDeque {

	private int cap;
	private ArrayDeque<Element> elements;
	private String defaultElementSeparator;
	//TODO cache

	public ElementDeque(int cap) {
		this(cap, "/");
	}

	public ElementDeque(int cap, String defaultElementSeparator) {
		this.cap = cap;
		this.defaultElementSeparator = defaultElementSeparator;
		elements = new ArrayDeque<Element>(cap);
	}

	public void addElement(Element element) {
		if (element == null) {
			return;
		}
		if (elements.size() == cap) {
			elements.removeFirst();
		}
		elements.addLast(element);
	}

	public Element removeLastElement() {
		if (elements.size() > 0) {
			return elements.removeLast();
		}
		return null;
	}

	public Element getLastElement() {
		if (elements.size() > 0) {
			return elements.getLast();
		}
		return null;
	}

	public int getSize(){
		return elements.size();
	}
	
	public String getAllElements() {
		String cache = "";
		Iterator<Element> it = elements.iterator();
		while (it.hasNext()) {
			cache += it.next().getStroke();
			if (it.hasNext()) {
				cache += defaultElementSeparator;
			}
		}
		return cache;
	}
	
	public Element getElement(int i) {
		if(i <= 0 || i> elements.size()) {
			return null;
		}
		Element e = null;
		Iterator<Element> it = elements.iterator();
		while (it.hasNext()) {
			e = it.next();
			if(i-- == 0) {
				break;
			}
		}
		return e;
	}
	
	public String concatPrintedString(int i) {
		if(i <= 0 || i > elements.size()) {
			return "";
		}
		String str = "";
		int x = 0;
		Iterator<Element> it = elements.iterator();
		while (it.hasNext()) {
			Element e = it.next();
			x++;
			if(x == elements.size()) {
				break;
			} else if(x > i) {
				str += e.getPrintedString();
			}
		}
		return str;
	}
	
	@Override
	public String toString() {
		return getAllElements();
	}

}
