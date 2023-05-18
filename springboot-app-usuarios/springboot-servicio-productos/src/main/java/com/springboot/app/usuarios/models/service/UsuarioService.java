package com.springboot.app.usuarios.models.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.usuarios.controllers.UsuarioNotFoundException;
import com.springboot.app.usuarios.models.dao.UsuarioDao;
import com.springboot.app.usuarios.models.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	private UsuarioDao usuarioRepository;
	private Pattern emailPattern;
	private String formatoContraseñaRegex;

	@Autowired
	public UsuarioService(UsuarioDao usuarioRepository,
			@Value("${formatoContraseñaRegex}") String formatoContraseñaRegex,
			@Value("${email-pattern}") String emailPattern) {
		this.usuarioRepository = usuarioRepository;
		this.formatoContraseñaRegex = formatoContraseñaRegex;
		this.emailPattern = Pattern.compile(emailPattern);
	}

	public boolean validarEmail(String email) {
		return emailPattern.matcher(email).matches();
	}

	public boolean emailRegistrado(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	public boolean contraseñaRegistrada(String contraseña) {
		return usuarioRepository.existsByContraseña(contraseña);
	}

	public Usuario crearUsuario(Usuario usuario) {

		UUID id = UUID.randomUUID();
		LocalDateTime now = LocalDateTime.now();
		String token = UUID.randomUUID().toString();

		usuario.setIdUsuario(id);
		usuario.setCreado(now);
		usuario.setModificado(now);
		usuario.setUltimoLogin(now);
		usuario.setToken(token);
		usuario.setActivo(true);

		usuarioRepository.save(usuario);

		return usuario;
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public UsuarioDao getUsuarioRepository() {
		return usuarioRepository;
	}

	public void setUsuarioRepository(UsuarioDao usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Pattern getEmailPattern() {
		return emailPattern;
	}

	public void setEmailPattern(Pattern emailPattern) {
		this.emailPattern = emailPattern;
	}

	public String getFormatoContraseñaRegex() {
		return formatoContraseñaRegex;
	}

	public void setFormatoContraseñaRegex(String formatoContraseñaRegex) {
		this.formatoContraseñaRegex = formatoContraseñaRegex;
	}

	@Override
	public boolean validarContraseña(String contraseña) {
		return contraseña.matches(formatoContraseñaRegex);
	}
	
	@Override
	public Usuario findById(Long id) {
	    return usuarioRepository.findById(id).orElse(null);
	}

	
	@Transactional
	@Override
	public Usuario actualizarUsuario(Long id, Usuario usuarioRequest) {
	    Optional<Usuario> usuarioExistenteOptional = usuarioRepository.findById(id);

	    if (usuarioExistenteOptional.isPresent()) {
	        Usuario usuarioExistente = usuarioExistenteOptional.get();
	        usuarioExistente.setNombre(usuarioRequest.getNombre());
	        usuarioExistente.setContraseña(usuarioRequest.getContraseña());
	        usuarioExistente.setEmail(usuarioRequest.getEmail());
	        usuarioExistente.setModificado(LocalDateTime.now());
	        usuarioExistente.setUltimoLogin(LocalDateTime.now());

	        return usuarioRepository.save(usuarioExistente);
	    } else {
	        throw new UsuarioNotFoundException("El usuario con el ID proporcionado no se encontró");
	    }
	}



	@Transactional
    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}
