package pedidosApp.backend.service.userService;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pedidosApp.backend.securityConfigurations.JwtUtils;
import pedidosApp.backend.service.authorizationService.TokenBlackListService;
import pedidosApp.backend.service.authorizationService.TokenService;


@Service
public class LogoutService {

    @Autowired
    private TokenBlackListService tokenBlackListService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<?> logout (HttpServletRequest request){
        tokenBlackListService.blackListToken(request);
        return ResponseEntity.ok().build();
    }
}
