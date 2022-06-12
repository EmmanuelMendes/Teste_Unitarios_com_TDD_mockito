package br.com.builders;

import br.com.entidades.Usuario;

public class UsuarioBuilder {
	private Usuario usuario;
	
	private UsuarioBuilder() {
		
	}
	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario();
		builder.usuario.setName("Emmanuel");
		return builder;
		
	}
	public Usuario usarioDoMomento() {
		return usuario;
	}
}
