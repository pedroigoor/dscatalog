package com.devsuperior.dscatalog.dtos;

import java.io.Serializable;

import javax.persistence.Entity;

import com.devsuperior.dscatalog.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String authority;
	
	public RoleDTO(Role entity) {
		this.id = entity.getId();
		this.authority = entity.getAuthority();
	}
	
}
