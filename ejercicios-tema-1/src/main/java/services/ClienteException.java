package services;

public class ClienteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8664568820401279770L;

	public ClienteException() {
	}

	public ClienteException(String message) {
		super(message);
	}

	public ClienteException(Throwable cause) {
		super(cause);
	}

	public ClienteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClienteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
