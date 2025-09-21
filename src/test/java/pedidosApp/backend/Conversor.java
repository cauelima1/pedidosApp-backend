package pedidosApp.backend;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Conversor {

    public static double converterParaDouble(String valorTexto) {
        valorTexto = valorTexto.replace(".", "").replace(",", ".");
        return Double.parseDouble(valorTexto);
    }


    @Test
    public void testConversaoSimples() {
        String entrada = "1.234,56";
        double esperado = 1234.56;
        double resultado = Conversor.converterParaDouble(entrada);
        assertEquals(esperado, resultado, 0.001);
    }
    @Test
    public void testConversaoComZero() {
        String entrada = "0,00";
        double esperado = 0.0;
        double resultado = Conversor.converterParaDouble(entrada);
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    public void testConversaoInvalida() {
        String entrada = "abc";
        assertThrows(NumberFormatException.class, () -> {
            Conversor.converterParaDouble(entrada);
        });
    }


}
