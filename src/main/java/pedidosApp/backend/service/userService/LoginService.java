package pedidosApp.backend.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.DTO.UserDtoRequest;
import pedidosApp.backend.entity.User;
import pedidosApp.backend.repository.UserRepository;
import pedidosApp.backend.service.authorizationService.TokenService;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String login (UserDtoRequest userDtoRequest){
        UserDetails user = userRepository.findByUsername(userDtoRequest.username());
        if (user != null){
            try {
                var login = new UsernamePasswordAuthenticationToken(userDtoRequest.username(), userDtoRequest.password());
                var auth = authenticationManager.authenticate(login);
                var token = tokenService.genereteToken((User) auth.getPrincipal());
                System.out.println("Token válido: ");
                      return token;
            } catch (Exception e) {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("Username not found");
        }
    }

}