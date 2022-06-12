package br.com.builders;

import br.com.entidades.Filmes;

public class FilmeBuilder {
	private Filmes filme;
	
	private FilmeBuilder() {
		
	}
	public static FilmeBuilder umfilme() {
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filmes();
		builder.filme.setName("FIlme 1");
		builder.filme.setEstoque(2);
		builder.filme.setPrecoLocacao(4.0);
		return builder;	
		
	}
	public Filmes agora() {
		return filme;
	}
	public FilmeBuilder semValor(Double valor) {
		filme.setPrecoLocacao(valor);
		return this;
	}
	public FilmeBuilder semEstoque() {
		filme.setEstoque(0);
		return this;
	}
	public FilmeBuilder comValor(Double valor) {
		filme.setPrecoLocacao(valor);
		return this;
	}
	public static FilmeBuilder umfilmeSemEstoque() {
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filmes();
		builder.filme.setName("FIlme 1");
		builder.filme.setEstoque(0);
		builder.filme.setPrecoLocacao(4.0);
		return builder;	
	}

}
