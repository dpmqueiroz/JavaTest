package com.sigabem.domain.exception;

public class ArgumentoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArgumentoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
