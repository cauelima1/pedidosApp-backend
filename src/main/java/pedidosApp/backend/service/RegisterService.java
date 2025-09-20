package pedidosApp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.DTO.UserDtoRequest;
import pedidosApp.backend.entity.User;
import pedidosApp.backend.repository.UserRepository;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> register (UserDtoRequest user){
        if(userRepository.findByUsername(user.username()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        String encriptedPassword = new BCryptPasswordEncoder().encode(user.password());
        User newUser = new User(user.username(), encriptedPassword);
        userRepository.save(newUser);
        System.out.println("Cadastrado: " + newUser);
        return ResponseEntity.ok().build();

    }

}
