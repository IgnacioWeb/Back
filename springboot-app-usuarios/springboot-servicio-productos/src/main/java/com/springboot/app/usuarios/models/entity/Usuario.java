package com.springboot.app.usuarios.models.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	private String nombre;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String contraseña;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Contacto> contacto;

	private UUID idUsuario;
	private LocalDateTime creado;
	private LocalDateTime modificado;
	private LocalDateTime ultimoLogin;
	private String token;
	private boolean activo;
	
	public Usuario() {
    }

    public Usuario(String nombre, String email, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
    }
	

	public Long getId() {
		return id;
	}

	public void setId(Long uuid) {
		this.id = uuid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public List<Contacto> getContacto() {
		return contacto;
	}

	public void setContacto(List<Contacto> contacto) {
		this.contacto = contacto;
	}

	public LocalDateTime getCreado() {
		return creado;
	}

	public void setCreado(LocalDateTime creado) {
		this.creado = creado;
	}

	public LocalDateTime getModificado() {
		return modificado;
	}

	public void setModificado(LocalDateTime modificado) {
		this.modificado = modificado;
	}

	public LocalDateTime getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(LocalDateTime ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public UUID getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UUID idUsuario) {
		this.idUsuario = idUsuario;
	}

}