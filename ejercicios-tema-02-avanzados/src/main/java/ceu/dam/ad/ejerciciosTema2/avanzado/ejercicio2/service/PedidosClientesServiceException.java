package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.service;

public class PedidosClientesServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2963030282235260647L;

	public PedidosClientesServiceException() {
	}

	public PedidosClientesServiceException(String message) {
		super(message);
	}

	public PedidosClientesServiceException(Throwable cause) {
		super(cause);
	}

	public PedidosClientesServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PedidosClientesServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
