package com.springboot.app.usuarios.controllers;

public class CorreoRegistradoException extends RuntimeException {

	private static final long serialVersionUID = -3059888989734259995L;

	public CorreoRegistradoException(String message) {
        super(message);
    }
}