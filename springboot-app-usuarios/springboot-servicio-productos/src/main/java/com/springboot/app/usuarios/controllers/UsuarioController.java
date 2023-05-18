package com.springboot.app.usuarios.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.usuarios.models.entity.Usuario;
import com.springboot.app.usuarios.models.service.UsuarioService;

@RestController
public class UsuarioController {

	private UsuarioService usuarioService;

	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> obtenerUsuarios() {
		try {
			List<Usuario> usuarios = usuarioService.findAll();

			if (usuarios.isEmpty()) {
				return ResponseEntity.noContent().build();
			}

			return ResponseEntity.ok(usuarios);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/crear")
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
		if (!usuarioService.validarEmail(usuario.getEmail())) {
			throw new FormatoCorreoIncorrectoException("El formato del correo es incorrecto");
		}

		if (usuarioService.emailRegistrado(usuario.getEmail())) {
			throw new CorreoRegistradoException("El correo ya está registrado");
		}

		String contraseña = usuario.getContraseña();
		if (!usuarioService.validarContraseña(contraseña)) {
			throw new ContraseñaInvalida("La contraseña no cumple con el formato requerido");
		}

		if (usuarioService.contraseñaRegistrada(usuario.getContraseña())) {
			throw new ContraseñaInvalida("Contraseña en uso, opte por otra.");
		}

		Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);

		return ResponseEntity.ok(nuevoUsuario);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioRequest) {
	    try {
	        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioRequest);

	        if (usuarioActualizado == null) {
	            return ResponseEntity.notFound().build();
	        }

	        return ResponseEntity.ok(usuarioActualizado);
	    } catch (UsuarioNotFoundException e) {
	        return ResponseEntity.notFound().build();
	    } catch (RuntimeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        try {
            Usuario usuarioExistente = usuarioService.findById(id);

            if (usuarioExistente == null) {
                return ResponseEntity.notFound().build();
            }

            usuarioService.eliminarUsuario(id);

            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
