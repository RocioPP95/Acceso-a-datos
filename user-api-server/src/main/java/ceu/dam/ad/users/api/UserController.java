package ceu.dam.ad.users.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.users.dto.request.UserRequestDto;
import ceu.dam.ad.users.dto.response.UserResponseDto;
import ceu.dam.ad.users.exception.DuplicateUserException;
import ceu.dam.ad.users.exception.UserException;
import ceu.dam.ad.users.exception.UserNotFoundException;
import ceu.dam.ad.users.exception.UserUnauthorizedException;
import ceu.dam.ad.users.model.User;
import ceu.dam.ad.users.service.UserServiceImpl;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	private UserServiceImpl service;

	@PostMapping("")

	public UserResponseDto post(@RequestBody UserRequestDto userDto) throws DuplicateUserException, UserException {
		User userEntity = new ModelMapper().map(userDto, User.class);
		service.createUser(userEntity);
		return new ModelMapper().map(userEntity, UserResponseDto.class);

	}

	@PutMapping("/{id}")
	public UserResponseDto put(@PathVariable Long id, @RequestParam String oldPassword,
			@RequestParam String newPassword) throws UserNotFoundException, UserUnauthorizedException, UserException {
		service.changePassword(id, oldPassword, newPassword);
		System.out.println("Contraseña actualizada");

		return null;
	}
}
