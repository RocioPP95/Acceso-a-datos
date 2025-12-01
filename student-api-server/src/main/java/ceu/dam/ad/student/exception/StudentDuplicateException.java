package ceu.dam.ad.student.exception;

public class StudentDuplicateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1941397057599134747L;

	public StudentDuplicateException() {
	}

	public StudentDuplicateException(String message) {
		super(message);
	}

	public StudentDuplicateException(Throwable cause) {
		super(cause);
	}

	public StudentDuplicateException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
