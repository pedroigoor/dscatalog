package com.devsuperior.dscatalog.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.devsuperior.dscatalog.entities.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@Email(message = "email invalido")
	private String email;
	@NotBlank(message = "nome obrigatorio")
	private String firstName;
	private String lastName;

	private Set<RoleDTO> roles = new HashSet<>();

	public UserDTO(User entity) {
		this.id = entity.getId();
		this.email = entity.getEmail();
		this.firstName = entity.getFirstName();
		this.lastName = entity.getLastName();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

}
