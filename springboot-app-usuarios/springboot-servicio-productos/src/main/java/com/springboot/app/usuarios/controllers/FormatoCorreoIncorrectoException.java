package com.springboot.app.usuarios.controllers;


public class FormatoCorreoIncorrectoException extends RuntimeException {

	private static final long serialVersionUID = -2212835074623688829L;

	public FormatoCorreoIncorrectoException(String message) {
        super(message);
    }
}
