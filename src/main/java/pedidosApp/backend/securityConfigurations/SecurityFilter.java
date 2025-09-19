package pedidosApp.backend.securityConfigurations;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pedidosApp.backend.repository.UserRepository;
import pedidosApp.backend.service.authorizationService.TokenBlackListService;
import pedidosApp.backend.service.authorizationService.TokenService;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenBlackListService tokenBlackListService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = tokenService.recoverToken(request);

        if (token != null){
            if(tokenBlackListService.isTokenBlackListed(token)){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                try{
                    var username = tokenService.validateToken(token);
                    UserDetails user = userRepository.findByUsername(username);

                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }   catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }



}