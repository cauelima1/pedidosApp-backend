package pedidosApp.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedidosApp.backend.entity.Cliente;
import pedidosApp.backend.entity.DTO.ClienteDtoRequest;
import pedidosApp.backend.service.clientService.ClientService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clientes")
@RestController
public class ClienteController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<?> saveCliente(@RequestBody ClienteDtoRequest clienteDto){
        clientService.salvarCliente(clienteDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> carregarClientes (){
        List<Cliente> clientes = clientService.listarClientes();
        return ResponseEntity.ok().body(clientes);
    }

}
