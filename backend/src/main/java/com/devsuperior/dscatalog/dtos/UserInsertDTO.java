package com.devsuperior.dscatalog.dtos;

import com.devsuperior.dscatalog.services.validation.UserInsertValid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@UserInsertValid
public class UserInsertDTO extends UserDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String password;

}
