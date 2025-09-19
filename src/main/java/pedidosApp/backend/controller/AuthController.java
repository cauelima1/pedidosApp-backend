package pedidosApp.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedidosApp.backend.entity.DTO.UserDtoRequest;
import pedidosApp.backend.service.authorizationService.TokenBlackListService;
import pedidosApp.backend.service.authorizationService.TokenService;
import pedidosApp.backend.service.userService.LogoutService;
import pedidosApp.backend.service.userService.RegisterService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private pedidosApp.backend.service.userService.LoginService loginService;


    @Autowired
    private LogoutService logoutService;

    @Autowired
    private TokenBlackListService tokenBlackListService;


    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserDtoRequest user){
        return registerService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserDtoRequest user){
        var token = loginService.login(user);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout (@RequestHeader("Authorization") String authHeader, HttpServletRequest request){
        return logoutService.logout(request);
    }
}
