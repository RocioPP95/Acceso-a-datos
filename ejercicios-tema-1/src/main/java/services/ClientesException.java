package services;

public class ClientesException extends Exception {

	private static final long serialVersionUID = -8684941908330927471L;

	public ClientesException() {
	}

	public ClientesException(String message) {
		super(message);
	}

	public ClientesException(Throwable cause) {
		super(cause);
	}

	public ClientesException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
