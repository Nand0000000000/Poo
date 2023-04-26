package testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.gov.cesarschool.poo.fidelidade.util.ValidadorCPF;

class TestesCPF {

	public class ValidadorCPFTest {

	    @Test
	    public void testEhCpfValidoCom11Digitos() {
	        assertTrue(ValidadorCPF.ehCpfValido("12345678901"));
	    }

	    @Test
	    public void testEhCpfValidoComMenosDe11Digitos() {
	        assertFalse(ValidadorCPF.ehCpfValido("1234567890"));
	    }

	    @Test
	    public void testEhCpfValidoComMaisDe11Digitos() {
	        assertFalse(ValidadorCPF.ehCpfValido("123456789012"));
	    }
	}
		@Test
	    public void testEhCpfValidoComCpfNulo() {
	        assertFalse(ValidadorCPF.ehCpfValido(null));
	    }
	    @Test
	    public void testEhCpfValidoComDigitosInvalidos() {
	        assertFalse(ValidadorCPF.ehCpfValido("1234567890a"));
	    }
	    @Test
	    public void testEhCpfValidoComDigitoVerificadorInvalido() {
	        assertFalse(ValidadorCPF.ehCpfValido("12345678900"));
	    }
	    @Test
	    public void testEhCpfValidoComDigitoVerificadorValido() {
	        assertTrue(ValidadorCPF.ehCpfValido("52998224725"));
	    }
	    @Test
	    public void testEhCpfValidoComCpfBranco() {
	        assertFalse(ValidadorCPF.ehCpfValido(""));
	        assertFalse(ValidadorCPF.ehCpfValido("   "));
	    }
}
