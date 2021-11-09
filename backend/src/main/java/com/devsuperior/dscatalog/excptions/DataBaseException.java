package com.devsuperior.dscatalog.excptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class DataBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataBaseException(String msg) {
		super(msg);
	}

}
