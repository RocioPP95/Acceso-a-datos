package ceu.dam.ad.tema3.ejercicios.ejercicio04.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.Pedido;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.PedidoLinea;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.repository.PedidoLineaRepository;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.repository.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidosService {
	@Autowired
	private PedidoRepository repoPedido;
	private PedidoLineaRepository repoLinea;

	@Transactional
	public Long registrarPedido(Pedido pedido) throws PedidoException {

		try {
			Pedido pedido1 = repoPedido.save(pedido);
			Integer numLinea = 1;

			for (PedidoLinea linea : pedido1.getLineas()) {
				linea.setIdPedido(pedido1.getIdPedido());
				linea.setNumLinea(numLinea);
				numLinea++;
				repoLinea.save(linea);

			}
			return pedido.getIdPedido();

		} catch (DataAccessException e) {
			throw new PedidoException("Error pedido", e);
		}

	}

	public Pedido consultar(Long idPedido) throws PedidoException, PedidoNotFoundException {

		try {
			Optional<Pedido> pedidoOpt = repoPedido.findById(idPedido);
			if (!pedidoOpt.isPresent()) {
				throw new PedidoNotFoundException("No existe pedido con id " + idPedido);
			}
			pedidoOpt.get().setLineas(repoLinea.findByIdPedido(idPedido));
			return pedidoOpt.get();

		} catch (DataAccessException e) {
			throw new PedidoException("Error al registrar pedido", e);
		}
	}

}
