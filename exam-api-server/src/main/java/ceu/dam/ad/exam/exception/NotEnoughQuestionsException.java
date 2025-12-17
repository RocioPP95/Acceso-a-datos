package ceu.dam.ad.exam.exception;

public class NotEnoughQuestionsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -919916286393911273L;

	public NotEnoughQuestionsException() {
	}

	public NotEnoughQuestionsException(String message) {
		super(message);
	}

	public NotEnoughQuestionsException(Throwable cause) {
		super(cause);
	}

	public NotEnoughQuestionsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughQuestionsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
