package ceu.dam.psp.examen.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.psp.examen.dto.request.LoginRequestDto;
import ceu.dam.psp.examen.dto.request.UsuarioRequestDto;
import ceu.dam.psp.examen.dto.response.UsuarioResponseDto;
import ceu.dam.psp.examen.exceptions.NoAutorizadoException;
import ceu.dam.psp.examen.exceptions.NoExisteException;
import ceu.dam.psp.examen.exceptions.UsuarioDuplicadoException;
import ceu.dam.psp.examen.model.Usuario;
import ceu.dam.psp.examen.service.WallapopService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallapop/")
public class WallapopController {

	@Autowired
	private WallapopService service;

	@PostMapping
	public UsuarioResponseDto createUser(@Valid @RequestBody UsuarioRequestDto usuarioNuevo)
			throws UsuarioDuplicadoException {
		ModelMapper modelMapper = new ModelMapper();
		Usuario user = modelMapper.map(usuarioNuevo, Usuario.class);
		user = service.crearUsuario(usuarioNuevo.getIdentificador(), usuarioNuevo.getContraseña(),
				usuarioNuevo.getDescripcion());
		return modelMapper.map(user, UsuarioResponseDto.class);
	}

	@PostMapping("/login")
	public UsuarioResponseDto autenticarUsuario(@RequestBody LoginRequestDto rerquest) throws NoAutorizadoException {
		return null;

	}

	@GetMapping("/usuarios/{id}")

	public UsuarioResponseDto consultarUsuario(@PathVariable String id) throws NoExisteException {

		Usuario usuario = service.consultarUsuario(id);
		ModelMapper mapper = new ModelMapper();
		return null;

	}

}
