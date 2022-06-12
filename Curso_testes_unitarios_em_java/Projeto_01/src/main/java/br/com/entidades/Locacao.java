package br.com.entidades;

import java.util.Date;
import java.util.List;

public class Locacao {
	private Usuario usuario;
	private List<Filmes> filme;
	private Date dataLocacao;
	private Date dataRetorno;
	private Double valor;
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Filmes> getFilme() {
		return filme;
	}
	public void setFilme(List<Filmes> filme) {
		this.filme = filme;
	}
	public Date getDataLocacao() {
		return dataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	public Date getDataRetorno() {
		return dataRetorno;
	}
	public void setDataRetorno(Date dataRetorno) {
		this.dataRetorno = dataRetorno;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
	
	
	

}
