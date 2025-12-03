package ceu.dam.ad.ejerciciosTema2.xml.exceptions;

public class XMLImportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7168125424922322042L;

	public XMLImportException() {
	}

	public XMLImportException(String message) {
		super(message);
	}

	public XMLImportException(Throwable cause) {
		super(cause);
	}

	public XMLImportException(String message, Throwable cause) {
		super(message, cause);
	}

	public XMLImportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
