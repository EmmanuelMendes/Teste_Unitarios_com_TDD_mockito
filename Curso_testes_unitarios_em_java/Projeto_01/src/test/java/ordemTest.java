import org.junit.Assert;
import org.junit.Test;



public class ordemTest {
	
	public static int contador = 0;
	
	@Test
	public void inicial() {
		contador = 1;
	}
	@Test
	public void verifica() {
		Assert.assertEquals(0, contador);
	}

}
