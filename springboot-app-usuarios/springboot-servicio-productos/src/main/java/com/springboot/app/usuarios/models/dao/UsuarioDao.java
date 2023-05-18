package com.springboot.app.usuarios.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.usuarios.models.entity.Usuario;


public interface UsuarioDao extends JpaRepository<Usuario, Long>{
	public Usuario findByEmail(String email);
	public boolean existsByEmail(String email);
	public boolean existsByContraseña(String contraseña);

}
