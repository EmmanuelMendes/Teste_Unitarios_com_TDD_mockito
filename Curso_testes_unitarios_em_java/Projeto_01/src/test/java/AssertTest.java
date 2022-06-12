import org.junit.Assert;
import org.junit.Test;

import br.com.entidades.Usuario;
public class AssertTest {
	
	@Test
	public void testAssert() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		Assert.assertEquals("Erro de comparação", 1,1);
		Assert.assertEquals(0.51234, 0.512, 0.01);
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		int i = 10;
		Integer i2 = 10;
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("bola", "bola");
		Assert.assertEquals("bola", "bola");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		Usuario usuario1 = new Usuario("Usuario 1");
		Usuario usuario2 = new Usuario("Usuario 1");
		Usuario usuario3 = null;
		
		
		Assert.assertEquals(usuario1, usuario2);
		
		Assert.assertSame(usuario2, usuario2);
		Assert.assertNotSame(usuario1, usuario2);
		
		Assert.assertNull(usuario3);
		Assert.assertNotNull(usuario2);
		
		
	}

}
