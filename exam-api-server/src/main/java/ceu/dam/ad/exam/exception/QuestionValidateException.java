package ceu.dam.ad.exam.exception;

public class QuestionValidateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2762759830267931225L;

	public QuestionValidateException() {
	}

	public QuestionValidateException(String message) {
		super(message);
	}

	public QuestionValidateException(Throwable cause) {
		super(cause);
	}

	public QuestionValidateException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuestionValidateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
