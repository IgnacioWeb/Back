package com.springboot.app.usuarios.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormatoCorreoIncorrectoException.class)
    public ResponseEntity<?> handleFormatoCorreoIncorrectoException(FormatoCorreoIncorrectoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(CorreoRegistradoException.class)
    public ResponseEntity<?> handleCorreoRegistradoException(CorreoRegistradoException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());
    }
    
    @ExceptionHandler(ContraseñaInvalida.class)
    public ResponseEntity<?> handleContraseñaInvalida(ContraseñaInvalida e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

}

