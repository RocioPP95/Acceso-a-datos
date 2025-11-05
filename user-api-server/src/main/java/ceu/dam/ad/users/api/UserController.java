package ceu.dam.ad.users.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.users.dto.LoginRequestDto;
import ceu.dam.ad.users.dto.PasswordChangeRequestDto;
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
	public void putPassword(@PathVariable Long id, @RequestBody PasswordChangeRequestDto passwordChangeDto)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		service.changePassword(id, passwordChangeDto.getOldPassword(), passwordChangeDto.getNewPassword());
		System.out.println("Contraseña actualizada");

	}

//	@PostMapping("")    
//	public User login(@RequestParam String login, @RequestParam String password)
//	        throws UserNotFoundException, UserUnauthorizedException, UserException {
//	    return service.login(login, password);
//	}
//	
//	
	@PostMapping("")
	public User login(@RequestBody LoginRequestDto dto)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		return service.login(dto.getLogin(), dto.getPassword());
	}

	@PutMapping("/{id}")
	public UserResponseDto get(@PathVariable Long id) throws UserNotFoundException, UserException {
		User userEntity = service.getUser(id);
		return new ModelMapper().map(userEntity, UserResponseDto.class);

	}

}
