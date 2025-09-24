package model;

import lombok.Data;

@Data
public class Cliente {

	private Integer id;
	private String firstName;
	private String lastName;
	private String mail;
	private Boolean activo;

}
