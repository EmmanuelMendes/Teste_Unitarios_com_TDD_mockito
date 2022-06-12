package br.com.entidades;

public class Filmes {
	private String name;
	private Integer estoque;
	private Double precoLocacao;
	
	public Filmes(String name, Integer estoque, Double precoLocacao) {
		this.name = name;
		this.estoque = estoque;
		this.precoLocacao = precoLocacao;
	}
	public Filmes() {
	
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Double getPrecoLocacao() {
		return precoLocacao;
	}

	public void setPrecoLocacao(Double precoLocacao) {
		this.precoLocacao = precoLocacao;
	}

	

}
