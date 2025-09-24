package services;

public class ClienteException extends Exception {

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
