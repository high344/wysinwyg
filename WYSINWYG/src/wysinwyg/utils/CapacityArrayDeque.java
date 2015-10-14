package wysinwyg.utils;

import java.util.ArrayDeque;
import java.util.Iterator;

public class CapacityArrayDeque<T> extends ArrayDeque<T> {

	private static final long serialVersionUID = -2094832852035798880L;

	private int cap;

	public CapacityArrayDeque(int cap) {
		super(cap);
		this.cap = cap;
	}

	public void addElement(T element) {
		if (element == null) {
			return;
		}
		if (size() == cap) {
			removeFirst();
		}
		addLast(element);
	}

	public T removeLastElement() {
		if (size() > 0) {
			return removeLast();
		}
		return null;
	}

	public T getLastElement() {
		if (size() > 0) {
			return getLast();
		}
		return null;
	}

	public int size() {
		return super.size();
	}

	public T getElement(int index) {
		if (index < 0 || index > size()) {
			return null;
		}
		T e = null;
		int i = 0;
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			e = it.next();
			if (index == i) {
				break;
			} else {
				i++;
			}
		}
		return e;
	}

	public void clear() {
		super.clear();
	}

}
