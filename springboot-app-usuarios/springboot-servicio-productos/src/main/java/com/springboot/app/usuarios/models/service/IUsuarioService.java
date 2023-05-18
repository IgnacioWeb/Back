package com.springboot.app.usuarios.models.service;

import com.springboot.app.usuarios.models.dao.UsuarioDao;
import com.springboot.app.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.regex.Pattern;

public interface IUsuarioService {
    public boolean validarContraseña(String contraseña);
    public UsuarioDao getUsuarioRepository();
    public void setUsuarioRepository(UsuarioDao usuarioRepository);
    public Pattern getEmailPattern();
    public void setEmailPattern(Pattern emailPattern);
    List<Usuario> findAll();
    public void eliminarUsuario(Long id);
    public Usuario findById(Long id);
	public Usuario actualizarUsuario(Long id, Usuario usuarioRequest);
}
