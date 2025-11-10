package ceu.dam.ad.users.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRequestDto {

	private String username;
	private String email;
	private String password;


}
