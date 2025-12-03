package ceu.dam.ad.ejerciciosTema2.csv.exceptions;

public class CsvException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1620558941905514466L;

	public CsvException() {
	}

	public CsvException(String message) {
		super(message);
	}

	public CsvException(Throwable cause) {
		super(cause);
	}

	public CsvException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
