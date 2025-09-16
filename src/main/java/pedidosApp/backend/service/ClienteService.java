package pedidosApp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.Cliente;
import pedidosApp.backend.entity.DTO.ClienteDtoRequest;
import pedidosApp.backend.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


//    public void adicionarCliente(ClienteDtoRequest cliente){
////        Cliente novoCliente = cliente;
//        repository.save(novoCliente);
//    }



}
