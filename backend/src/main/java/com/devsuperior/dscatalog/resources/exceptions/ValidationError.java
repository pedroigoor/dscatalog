package com.devsuperior.dscatalog.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;


public class ValidationError extends StandardError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	private List<FieldMessage> erros = new ArrayList<>();
	
	public void addError(String fieldName, String message) {
		erros.add( new FieldMessage (fieldName,message));
	}
}
