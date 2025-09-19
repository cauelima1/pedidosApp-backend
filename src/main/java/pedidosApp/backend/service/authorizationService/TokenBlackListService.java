package pedidosApp.backend.service.authorizationService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.BlackListedToken;
import pedidosApp.backend.repository.BlackListedTokenRepository;
import pedidosApp.backend.securityConfigurations.JwtUtils;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Service
public class TokenBlackListService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BlackListedTokenRepository repository;

    public void blackListToken (HttpServletRequest request)    {
        try{

            String token = tokenService.recoverToken(request);
            long expirationMillis = jwtUtils.getExpirationFromToken(token).getTime() - System.currentTimeMillis();
            BlackListedToken blackListedToken = new BlackListedToken();
            blackListedToken.setToken(token);
            blackListedToken.setExpirationMillis(expirationMillis);
            blackListedToken.setBlacklistedAt(LocalDateTime.now());
            repository.save(blackListedToken);

        } catch (Exception e){
            throw new RuntimeException();
        }
    }

    public boolean isTokenBlackListed (String token){
        return repository.existsByToken(token);
    }

}
