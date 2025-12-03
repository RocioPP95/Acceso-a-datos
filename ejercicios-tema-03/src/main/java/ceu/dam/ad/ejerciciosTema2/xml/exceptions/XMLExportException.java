package ceu.dam.ad.ejerciciosTema2.xml.exceptions;

public class XMLExportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3120125567142080230L;

	public XMLExportException() {
	}

	public XMLExportException(String message) {
		super(message);
	}

	public XMLExportException(Throwable cause) {
		super(cause);
	}

	public XMLExportException(String message, Throwable cause) {
		super(message, cause);
	}

	public XMLExportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
