package ceu.dam.psp.examen.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.psp.examen.dto.request.ArticuloRequestDto;
import ceu.dam.psp.examen.dto.request.LoginRequestDto;
import ceu.dam.psp.examen.dto.request.UsuarioRequestDto;
import ceu.dam.psp.examen.dto.response.ArticuloResponseDto;
import ceu.dam.psp.examen.dto.response.UsuarioResponseDto;
import ceu.dam.psp.examen.exceptions.ErrorPagoException;
import ceu.dam.psp.examen.exceptions.NoAutorizadoException;
import ceu.dam.psp.examen.exceptions.NoExisteException;
import ceu.dam.psp.examen.exceptions.UsuarioDuplicadoException;
import ceu.dam.psp.examen.model.Articulo;
import ceu.dam.psp.examen.model.Usuario;
import ceu.dam.psp.examen.service.WallapopService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallapop/")
public class WallapopController {

	@Autowired
	private WallapopService service;

	@PostMapping
	@Operation(summary = "Crea un nuevo usuario en el sistema")
	public UsuarioResponseDto createUser(@Valid @RequestBody UsuarioRequestDto usuarioNuevo)
			throws UsuarioDuplicadoException {
		ModelMapper modelMapper = new ModelMapper();
		Usuario user = modelMapper.map(usuarioNuevo, Usuario.class);
		user = service.crearUsuario(usuarioNuevo.getIdentificador(), usuarioNuevo.getContraseña(),
				usuarioNuevo.getDescripcion());
		return modelMapper.map(user, UsuarioResponseDto.class);
	}

	@PostMapping("/login")
	@Operation(summary = "Autentica un usuario en el sistema")
	public UsuarioResponseDto autenticarUsuario(@Valid @RequestBody LoginRequestDto request)
			throws NoAutorizadoException {
		String passwordSha512 = DigestUtils.sha512Hex(request.getContraseña());
		Usuario usuario = service.autenticarUsuario(request.getIdentificador(), passwordSha512);
		return new ModelMapper().map(usuario, UsuarioResponseDto.class);

	}

	@GetMapping("/usuarios/{id}")
	@Operation(summary = "Consulta los datos de un usuario por su identificador")
	public UsuarioResponseDto consultarUsuario(@PathVariable String id) throws NoExisteException {

		Usuario usuario = service.consultarUsuario(id);
		return new ModelMapper().map(usuario, UsuarioResponseDto.class);
	}

	@GetMapping("/articulos")
	public List<ArticuloResponseDto> buscarArticulo(@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String descripcion,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaAltaMinima)
			throws NoExisteException {
		List<Articulo> articulos = service.buscarArticulos(nombre, descripcion, fechaAltaMinima);
		return articulos.stream().map(articulo -> new ModelMapper().map(articulo, ArticuloResponseDto.class))
				.collect(Collectors.toList());
	}

	@PostMapping("/articulos")
	@Operation(summary = "Crea un nuevo artículo en el sistema. El artículo debe tener indicado el identificador de su propietario.")
	public UsuarioResponseDto crearArticulo(@Valid @RequestBody ArticuloRequestDto articuloDto)
			throws NoExisteException {
		ModelMapper modelMapper = new ModelMapper();
		Articulo articulo = modelMapper.map(articuloDto, Articulo.class);

		// Me falta comprobar que el usuario propietario existe, si no existe, se
		// lanzará NoExisteException y relllenar datos del articulo

		Usuario usuario = service.crearArticulo(articulo);
		return modelMapper.map(usuario, UsuarioResponseDto.class);
	}

	@PutMapping("/articulos/{codigo}/comprar")
	@Operation(summary = "Compra un artículo por su código")
	public void comprarArticulo(@PathVariable Long codigo) throws ErrorPagoException {
		service.comprarArticulo(codigo);
	}

}
