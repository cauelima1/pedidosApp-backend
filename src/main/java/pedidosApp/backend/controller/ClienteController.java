package pedidosApp.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/clientes")
@RestController
public class ClienteController {

    @GetMapping
    public String home(){
        return "API Pedidos";
    }


//    @PostMapping("/novo")
//    public ResponseEntity novoCliente(@RequestBody ClienteDto clienteDto){
//
//    }

}
