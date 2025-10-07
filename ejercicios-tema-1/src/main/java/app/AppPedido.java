package app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import model.LineaPedido;
import model.Pedido;
import services.PedidoException;
import services.PedidoService;

public class AppPedido {

	public static void main(String[] args) {
		Pedido pedido = new Pedido();
		pedido.setFechaPedido(LocalDate.now());
		pedido.setFechaEntrega(LocalDate.of(2024, 12, 1));
		pedido.setCliente("Lucas Cangrejo");
		pedido.setPedidos(new ArrayList<LineaPedido>());

		for (int j = 1; j <= 3; j++) {
			LineaPedido linea = new LineaPedido();
			linea.setPrecio(new BigDecimal(938));
			pedido.getPedidos().add(linea);
//			if (j == 3) { // para probar si hay error que no guarde nada
//				linea.setArticulo("fñalsdkfjñalsdjfñlasdjfñlasdjfñlasjdfñlasjdflñkjasdñlfjaslñdfjasñldfjañlsdjfñalsdjkfñlaksjdfñlkasjdflñkasjdfñlkasj");
//			}
		}

		PedidoService service = new PedidoService();
		try {
			service.registrarPedido(pedido);
		} catch (PedidoException e) {
			e.printStackTrace();
		}

	}

}
