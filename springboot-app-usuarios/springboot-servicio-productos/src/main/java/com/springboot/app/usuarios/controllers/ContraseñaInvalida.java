package com.springboot.app.usuarios.controllers;

public class ContraseñaInvalida extends RuntimeException{

	private static final long serialVersionUID = 2638981610234300058L;

	public ContraseñaInvalida(String mensaje) {
        super(mensaje);
    }
}