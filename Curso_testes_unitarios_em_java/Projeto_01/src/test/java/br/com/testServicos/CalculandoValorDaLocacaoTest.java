package br.com.testServicos;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import static br.com.builders.UsuarioBuilder.umUsuario;

import static org.hamcrest.CoreMatchers.is;

import br.com.daos.LocacaoDao;
import br.com.daos.LocacaoDaoFaker;
import br.com.entidades.Filmes;
import br.com.entidades.Locacao;
import br.com.entidades.Usuario;
import br.com.exceptions.FilmeSemEstoqueException;
import br.com.exceptions.locadoraException;
import br.com.servicos.LocacaoService;

@RunWith(Parameterized.class)
public class CalculandoValorDaLocacaoTest {
	private LocacaoService service;
	private LocacaoDao dao;
	
	
	@Parameter
	public List<Filmes> filmes;
	
	@Parameter(value = 1)
	public Double valorLocacao;
	
	@Parameter(value = 2)
	public String cenario;

	
	@Before
	public void setup() {
		 service = new LocacaoService();
		 //LocacaoDao dao = new LocacaoDaoFaker();
		 //service.setLocacaoDao(dao);
		 dao = Mockito.mock(LocacaoDao.class);
		 service.setLocacaoDao(dao);

	}
	private static Filmes filme1 = new Filmes("Esqueceram de mim", 2, 4.0);
	private static Filmes filme2 = new Filmes("Locademia de policia", 2, 4.0);
	private static Filmes filme3 = new Filmes("O Auto da Compadecida", 2, 4.0);
	private static Filmes filme4 = new Filmes("Lisbela e o Prisioneiro", 2, 4.0);
	private static Filmes filme5 = new Filmes("Top Gun - Ases Indomáveis", 2, 4.0);
	private static Filmes filme6 = new Filmes("O Poderoso Chefão", 2, 4.0);
	private static Filmes filme7 = new Filmes("Jango Livre", 2, 4.0);
	
	
	@Parameters(name="Teste {index} = {2}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[] [] {
			{Arrays.asList(filme1, filme2 ,filme3), 11.0, "3 Filmes 25%"},
			{Arrays.asList(filme1, filme2 ,filme3, filme4), 13.0, "4 Filmes 50%"},
			{Arrays.asList(filme1, filme2 ,filme3, filme4, filme5), 14.0, "5 Filmes 75%"},
			{Arrays.asList(filme1, filme2 ,filme3, filme4, filme5, filme6), 14.0, "6 Filmes 100%"},
			{Arrays.asList(filme1, filme2 ,filme3, filme4, filme5, filme6, filme7), 18.0, "7 Filmes não contém desconto"}
		});
	}
	
	//private static Filme filme1 = umFilme().agora();
	//private static Filme filme2 = umFilme().agora();
	//private static Filme filme3 = umFilme().agora();
	//private static Filme filme4 = umFilme().agora();
	//private static Filme filme5 = umFilme().agora();
	//private static Filme filme6 = umFilme().agora();
	//private static Filme filme7 = umFilme().agora();
	
	@Test
	public void deveCalularValorPromocaoConsiderandoDesconto() throws FilmeSemEstoqueException, locadoraException {
	
		Usuario usuario = umUsuario().usarioDoMomento();
		
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		Assert.assertThat(locacao.getValor(), is(valorLocacao));
		
	}
	

}
