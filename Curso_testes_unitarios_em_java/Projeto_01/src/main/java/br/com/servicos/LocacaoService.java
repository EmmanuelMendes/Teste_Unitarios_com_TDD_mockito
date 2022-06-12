package br.com.servicos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static br.com.Utils.DataUtils.adicionarDias;
import br.com.Utils.DataUtils;
import br.com.daos.LocacaoDao;
import br.com.entidades.Filmes;
import br.com.entidades.Locacao;
import br.com.entidades.Usuario;
import br.com.exceptions.FilmeSemEstoqueException;
import br.com.exceptions.locadoraException;

public class LocacaoService {
	private LocacaoDao dao;
	public Locacao alugarFilme(Usuario usuario, List<Filmes> Filme) throws FilmeSemEstoqueException, locadoraException{

		if(usuario == null) {
			throw new locadoraException("Usuário informado esta vazio");
		}
		if(Filme == null || Filme.isEmpty()) {
			throw new locadoraException( "Filme informado esta vazio");
		}
		for(Filmes filme: Filme) {
			if(filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
		}
		Locacao locacao = new Locacao();
		locacao.setFilme(Filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		Double valorTotal = 0d;
		
		for(int i = 0; i<Filme.size(); i++) {
			Filmes filme = Filme.get(i);
			Double valorFilme = filme.getPrecoLocacao();
			switch (i) {
				case 2: valorFilme = valorFilme * 0.75;break;
				case 3: valorFilme = valorFilme * 0.5;break;
				case 4: valorFilme = valorFilme * 0.25;break;
				case 5: valorFilme = 0d; break;
			}
			valorTotal += valorFilme;
		}
		locacao.setValor(valorTotal);
		
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)) {
			dataEntrega = adicionarDias(dataEntrega, 1);
		}
		locacao.setDataRetorno(dataEntrega);
		
		dao.salvar(locacao);
		
		return locacao;
	}
	public void setLocacaoDao(LocacaoDao dao) {
		this.dao = dao;
		
	}
	

	

}
