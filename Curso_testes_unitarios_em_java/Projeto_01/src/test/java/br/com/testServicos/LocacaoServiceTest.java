package br.com.testServicos;

import static br.com.Utils.DataUtils.isMesmaData;
import static br.com.Utils.DataUtils.obterDataComDiferencaDias;
import static br.com.builders.FilmeBuilder.umfilme;
import static br.com.builders.UsuarioBuilder.umUsuario;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import br.com.Utils.DataUtils;
import br.com.builders.FilmeBuilder;
import br.com.daos.LocacaoDao;
import br.com.daos.LocacaoDaoFaker;
import br.com.entidades.Filmes;
import br.com.entidades.Locacao;
import br.com.entidades.Usuario;
import br.com.exceptions.FilmeSemEstoqueException;
import br.com.exceptions.locadoraException;
import br.com.matchers.MatchersProprios;
import br.com.servicos.LocacaoService;
import buildermaster.BuilderMaster;

public class LocacaoServiceTest {
	private LocacaoService service;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		 service = new LocacaoService();
		 //LocacaoDao dao = new LocacaoDaoFaker(); // Utilizando faker para metodos externos
		 //service.setLocacaoDao(dao);
		 LocacaoDao dao= Mockito.mock(LocacaoDao.class); //Utilizando Mockito para gerenciar 
		 service.setLocacaoDao(dao);
		
	}

	@Test
	public void testeLocacao() throws Exception {
		//cenario
		
		Usuario usuario = umUsuario().usarioDoMomento();
		List<Filmes> filme = Arrays.asList(umfilme().agora());
		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
			
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(4.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
		error.checkThat(locacao.getDataRetorno(),  MatchersProprios.ehHojeComDiferencaDias(1));
		//error.checkThat(locacao.getDataRetorno(),  MatchersProprios.ehHoje());

	}
	
	@Test(expected = FilmeSemEstoqueException.class)	
	public void testLocacao_filmeSemEstoque() throws Exception{
		//cenario
		Usuario usuario = umUsuario().usarioDoMomento();
		List<Filmes> filmes = Arrays.asList(FilmeBuilder.umfilmeSemEstoque().agora());
		
		//acao
		service.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void deveAlugarFilme() throws FilmeSemEstoqueException, locadoraException {
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SUNDAY));
		
		Usuario usuario = umUsuario().usarioDoMomento();
		List<Filmes> filmes = Arrays.asList(umfilme().agora());
		
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		error.checkThat(locacao.getValor(), is(equalTo(4.0)));
		//error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date(), is(true)));
	}
	

//	@Test
//	public void testLocacao_filmeSemEstoque_2() {
//		//cenario
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = umUsuario().usarioDoMomento();
//		Filme filme = new Filme("Filme 2", 0, 4.0);
//		
//		//acao
//		try {
//			service.alugarFilme(usuario, filme);
//			Assert.fail("Deveria ter lancado uma excecao");
//		} catch (Exception e) {
//			assertFail(e.getMessage());
//			assertThat(e.getMessage(), is("Filme sem estoque"));
//		}
//	}
//	
//
//
//
//	@Test
//	public void testLocacao_filmeSemEstoque_3() throws Exception {
//		//cenario
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = umUsuario().usarioDoMomento();
//		Filme filme = new Filme("Filme 2", 1, 4.0);
//		
//		exception.expect(Exception.class);
//		exception.expectMessage("Filme sem estoque");
//		
//		//acao
//		service.alugarFilme(usuario, filme);
//	}
	
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		
		List<Filmes> filme = Arrays.asList(FilmeBuilder.umfilme().agora());
		
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		}catch(locadoraException e) {
			assertThat(e.getMessage(), is("Usuário informado esta vazio"));
		}
		
		
	}
	@Test
	public void testLocacao_filmeVazio() throws FilmeSemEstoqueException, locadoraException {
		Usuario usuario = umUsuario().usarioDoMomento();
		exception.expect(locadoraException.class);
		exception.expectMessage("Filme informado esta vazio");
		
		service.alugarFilme(usuario, null);
		
		
	}
	@Test
	public void devePagar_75PctNoFilme3() throws FilmeSemEstoqueException, locadoraException {
		//cenario
		Usuario usuario = umUsuario().usarioDoMomento();
		List<Filmes> filmes = Arrays.asList(FilmeBuilder.umfilme().comValor(4.0).agora(), FilmeBuilder.umfilme().comValor(4.0).agora(), FilmeBuilder.umfilme().comValor(4.0).agora());
		
		//ação
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		Assert.assertThat(locacao.getValor(), is(11.0));
	}
	@Test
	public void devePagar_50PctNoFilme4() throws FilmeSemEstoqueException, locadoraException {
		//cenario
		Usuario usuario = umUsuario().usarioDoMomento();
		List<Filmes> filmes = Arrays.asList(FilmeBuilder.umfilme().comValor(4.0).agora(), FilmeBuilder.umfilme().comValor(4.0).agora(), FilmeBuilder.umfilme().comValor(4.0).agora(), FilmeBuilder.umfilme().comValor(4.0).agora());
		
		//ação
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		Assert.assertThat(locacao.getValor(), is(13.0));
	}
	@Test
	public void devePagar_25PctNoFilme4() throws FilmeSemEstoqueException, locadoraException {
		//cenario
		Usuario usuario = umUsuario().usarioDoMomento();
		List<Filmes> filmes = Arrays.asList(
				FilmeBuilder.umfilme().comValor(4.0).agora(), 
				FilmeBuilder.umfilme().comValor(4.0).agora(), 
				FilmeBuilder.umfilme().comValor(4.0).agora(), 
				FilmeBuilder.umfilme().comValor(4.0).agora(),
				FilmeBuilder.umfilme().comValor(4.0).agora());
		
		//ação
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		Assert.assertThat(locacao.getValor(), is(14.0));
	}
	public void devePagar_0PctNoFilme4() throws FilmeSemEstoqueException, locadoraException {
		//cenario
		Usuario usuario = umUsuario().usarioDoMomento();
		List<Filmes> filmes = Arrays.asList(
				FilmeBuilder.umfilme().comValor(4.0).agora(), 
				FilmeBuilder.umfilme().comValor(4.0).agora(), 
				FilmeBuilder.umfilme().comValor(4.0).agora(), 
				FilmeBuilder.umfilme().comValor(4.0).agora(),
				FilmeBuilder.umfilme().comValor(4.0).agora(),
				FilmeBuilder.umfilme().comValor(4.0).agora());
		
		//ação
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		Assert.assertThat(locacao.getValor(), is(14.0));
	}
	@Test
	@Ignore
	public void naoPodeDevolverFilmeNoDomingo() throws FilmeSemEstoqueException, locadoraException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SUNDAY));
		Usuario usuario = umUsuario().usarioDoMomento();
		List<Filmes> filmes = Arrays.asList(new Filmes("Filme 1", 2, 2.50));
		
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		boolean segunda = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY);
		Assert.assertTrue(segunda);
	}
	/*
	@Test
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, locadoraException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.MONDAY));
		
		Usuario usuario = umUsuario().usarioDoMomento();
		List<Filmes> filme = Arrays.asList(new Filmes("As fuga das galinhas", 2, 3.50));
		
		Locacao retorno = service.alugarFilme(usuario, filme);
		
		boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
		Assert.assertTrue(ehSegunda);
		
		Assert.assertThat(retorno.getDataRetorno(), MatchersProprios.caiEm(Calendar.MONDAY));
		Assert.assertThat(retorno.getDataRetorno(), MatchersProprios.caiNumaSegunda());

		
		//Assert.assertThat(retorno.getDataRetorno(), new diaDaSemanaMatchers(Calendar.MONDAY));
		
		
	}
	*/
	public static void main(String[] args) {
		new BuilderMaster().gerarCodigoClasse(Locacao.class);
	}
	

}
