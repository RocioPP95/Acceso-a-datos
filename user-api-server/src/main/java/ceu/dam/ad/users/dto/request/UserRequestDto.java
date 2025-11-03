package ceu.dam.ad.users.dto.request;

import java.time.LocalDate;


public class UserRequestDto {

	private String username;
	private String email;
	private String password;

	private LocalDate createdDate;

	private LocalDate lastLoginDate;
}
