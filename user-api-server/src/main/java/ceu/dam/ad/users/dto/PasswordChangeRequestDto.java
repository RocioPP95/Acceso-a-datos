package ceu.dam.ad.users.dto;

import lombok.Data;

@Data
public class PasswordChangeRequestDto {
	private String oldPassword;
	private String newPassword;
}
