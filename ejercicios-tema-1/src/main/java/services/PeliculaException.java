package services;

public class PeliculaException extends Exception {

	public PeliculaException() {
	}

	public PeliculaException(String message) {
		super(message);
	}

	public PeliculaException(Throwable cause) {
		super(cause);
	}

	public PeliculaException(String message, Throwable cause) {
		super(message, cause);
	}

	public PeliculaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
