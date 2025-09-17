package pedidosApp.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.User;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

@Service
public class TokenService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.secret}")
    private String secret;

    public String genereteToken (User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withClaim("role","USER")
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            System.out.println("token gerado: " + token);
            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Error while generating token" , e);
        }
    }

    public String validateToken (String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Error on token validation");
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public void salvarToken(String username, String token) {
        System.out.println("salvando Token para: " + username + " = " + token);
        redisTemplate.opsForValue().set("TOKEN_" + username, token, Duration.ofHours(2));
    }

    public boolean tokenValido(String username, String token) {
        String tokenSalvo = redisTemplate.opsForValue().get("TOKEN_" + username);
        return token.equals(tokenSalvo);
    }


}
