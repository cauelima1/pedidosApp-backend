package pedidosApp.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pedidosApp.backend.entity.User;
import pedidosApp.backend.service.authorizationService.TokenService;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TokenServiceTest {

    private TokenService tokenService;

    //preparação para gerar o secret e simular o valor
    @BeforeEach
    public void setup() throws Exception {
        tokenService = new TokenService();
        Field secretField = TokenService.class.getDeclaredField("secret");
        secretField.setAccessible(true);
        secretField.set(tokenService, "chave-super-secreta-para-jwt-1234567890");
    }

    @Test
    public void deveGerarTokenValido(){
        User user = new User();
        user.setUsername("cauetest");

        String token = tokenService.genereteToken(user);

        assertNotNull(token);
        assertTrue(token.startsWith("ey"));
        System.out.println("Token gerado: " + token);
    }

}
