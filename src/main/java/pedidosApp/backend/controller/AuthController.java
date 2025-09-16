package pedidosApp.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedidosApp.backend.entity.DTO.UserDtoRequest;
import pedidosApp.backend.entity.DTO.UserDtoResponse;
import pedidosApp.backend.service.LoginService;
import pedidosApp.backend.service.RegisterService;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;


    @GetMapping("/endpoint")
    public String iniciar (){
        return "API rodando.";
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody UserDtoRequest user){
        return registerService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity token (@RequestBody UserDtoRequest user){
        var token = loginService.login(user);
        return ResponseEntity.ok(new UserDtoResponse(token));
    }
}
