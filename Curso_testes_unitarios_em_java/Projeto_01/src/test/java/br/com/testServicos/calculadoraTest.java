package br.com.testServicos;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.servicos.Calculadora;


public class calculadoraTest {
	private Calculadora calc;
	@Before
	public void setup() {
		calc = new Calculadora();
	}
	
	@Test
	public void testSoma() {
		int a = 10;
		int b = 20;
		
		int somando = calc.soma(a, b);
		
		//esperado
		int resultado = 30;
		
		Assert.assertEquals(resultado, somando);
		
	}
	@Test
	public void testDivisao() {
		int a = 10;
		int b = 2;
		
		int divisao = calc.div(a, b);
		
		//Esperado
		int resultado = 5;
		
		Assert.assertEquals(resultado, divisao);
	}
	@Test
	public void testSubtracao() {
		int a = 50;
		int b = 20;
		
		int subtracao = calc.sub(a, b);
		
		//Esperado
		int resultado = 30;
		
		Assert.assertEquals(resultado, subtracao);
	}
	@Test
	public void testMultiplicacao() {
		int a = 10;
		int b = 5;
		
		int multiplicacao = calc.mult(a, b);
		
		//Esperado
		
		int resultado = 50;
		
		Assert.assertEquals(resultado, multiplicacao);
	}
}
