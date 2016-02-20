package wysinwyg.fb.mutex;

public class MutexException extends Exception {

	private static final long serialVersionUID = 8187788255886177840L;

	public MutexException() {
		super();
	}

	public MutexException(String message) {
		super(message);
	}

	public MutexException(String message, Throwable cause) {
		super(message, cause);
	}

	public MutexException(Throwable cause) {
		super(cause);
	}

}