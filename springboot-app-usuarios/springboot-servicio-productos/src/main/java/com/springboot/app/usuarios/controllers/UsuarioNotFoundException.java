package com.springboot.app.usuarios.controllers;

public class UsuarioNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -1331349136732706820L;

	public UsuarioNotFoundException(String message) {
        super(message);
    }
}