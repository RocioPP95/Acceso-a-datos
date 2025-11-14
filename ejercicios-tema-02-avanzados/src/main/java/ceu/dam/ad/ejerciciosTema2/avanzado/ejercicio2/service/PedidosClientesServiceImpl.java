package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Articulo;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Cliente;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Pedido;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.PedidoLinea;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.ClienteRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.PedidoRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.RepositoryArticulo;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.RepositoryPedidoLineas;
import jakarta.transaction.Transactional;
@Service
public class PedidosClientesServiceImpl implements PedidosClientesService {

	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private RepositoryArticulo articuloRepo;
	@Autowired
	private RepositoryPedidoLineas pedidoLineaRepo;

	@Override
	public void crearCliente(Cliente cliente) throws PedidosClientesServiceException {
		try {
			clienteRepo.save(cliente);

		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException(e);
		}
	}

	/**
	 * Debe crear en bbdd el pedido indicado. En el pedido, el cliente tiene que
	 * estar previamente registrado, así como los artículos de las líneas. Al
	 * guardar el pedido, guardaremos también todas sus líneas. Devolverá el pedido
	 * registrado completo. IMPORTANTE: todas las l�neas del pedido habrá que
	 * inicializarlas con su n�mero de línea antes de guardarlas. Se inicializarán
	 * comenzando en 1
	 */
	@Transactional
	@Override
	public Pedido crearPedido(Pedido pedido) throws PedidosClientesServiceException {

		try {
			Integer contador = 0;
			List<PedidoLinea> lineas = pedido.getLineas();
			for (PedidoLinea pedidoLinea : lineas) {
				Integer suma=contador++;
				pedidoLinea.setNumLinea(suma);

			}
			pedidoRepo.save(pedido);

		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException(e);

		}
		return pedido;
	}

	/**
	 * Debe crear en bbdd el artículo indicado. Devolverá el articulo registrado
	 * completo.
	 */

	@Override
	@Transactional
	public Articulo crearArticulo(Articulo articulo) throws PedidosClientesServiceException {
		try {
			articuloRepo.save(articulo);

		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException(e);
		}
		return articulo;
	}

	/**
	 * Actualizará los datos del cliente indicado en BBDD. Sólo se actualizarán los
	 * datos de esta entidad, no de sus pedidos.
	 */
	@Override
	@Transactional
	public void actualizarCliente(Cliente cliente) throws PedidosClientesServiceException {
		try {
			clienteRepo.save(cliente);

		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException(e);
		}

	}

	/**
	 * Consulta el cliente con el DNI indicado en BBDD. Si no existe, lanza
	 * NotFoundException Si existe, devolverá dicho cliente con todos sus pedidos
	 * cargados.
	 */
	@Override
	public Cliente consultarCliente(String dni) throws NotFoundException, PedidosClientesServiceException {
		try {
			return clienteRepo.findById(dni).orElseThrow(() -> new NotFoundException("No existe"));
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException(e);
		}

	}

	/**
	 * Consulta el articulo con el ID indicado en BBDD. Si no existe, lanza
	 * NotFoundException Si existe, devolverá dicho artículo.
	 */
	@Override
	public Articulo consultarArticulo(Long idArticulo) throws NotFoundException, PedidosClientesServiceException {
		try {
			return articuloRepo.findById(idArticulo).orElseThrow(() -> new NotFoundException("No existe"));
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException(e);
		}
	}

	/**
	 * Consulta el pedido con el uuid indicado. Si no existe, lanzará
	 * NotFoundExcepion. El pedido devuelto estará completo: con todas sus líneas,
	 * artículos y cliente.
	 */
	@Override
	public Pedido consultarPedido(String uuid) throws NotFoundException, PedidosClientesServiceException {
		try {
			UUID ejemplo = UUID.fromString(uuid);
			return pedidoRepo.findById(ejemplo).orElseThrow(() -> new NotFoundException("No existe"));
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException(e);
		}

	}

}
