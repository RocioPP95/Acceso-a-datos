package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Articulo;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Cliente;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Pedido;

public class PedidosClientesServiceImpl implements PedidosClientesService {


	@Override
	public void crearCliente(Cliente cliente) throws PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
	}


	@Override
	public Pedido crearPedido(Pedido pedido) throws PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}


	@Override
	public Articulo crearArticulo(Articulo articulo) throws PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}
	
	@Override
	public void actualizarCliente(Cliente cliente) throws PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
	}


	@Override
	public Cliente consultarCliente(String dni) throws NotFoundException, PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}


	@Override
	public Articulo consultarArticulo(Long idArticulo) throws NotFoundException, PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}


	@Override
	public Pedido consultarPedido(String uuid) throws NotFoundException, PedidosClientesServiceException {
		// TODO: IMPLEMENTAR...
		return null;
	}
	
	
	
}
