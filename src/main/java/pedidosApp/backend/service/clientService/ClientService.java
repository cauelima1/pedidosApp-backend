package pedidosApp.backend.service.clientService;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.Cliente;
import pedidosApp.backend.entity.DTO.ClienteDtoRequest;
import pedidosApp.backend.repository.ClienteRepository;
import pedidosApp.backend.repository.UserRepository;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<HttpStatus> salvarCliente (ClienteDtoRequest novoCliente){
        if(clienteRepository.findByCnpj(novoCliente.cnpj()) == null) {
            Cliente cliente = new Cliente();
            cliente.setNome(novoCliente.nome());
            cliente.setCnpj(novoCliente.cnpj());
            cliente.setObs(novoCliente.obs());
            cliente.setCep(novoCliente.cep());
            cliente.setEndereco(novoCliente.endereco());
            cliente.setMunicipio(novoCliente.municipio());
            cliente.setUf(novoCliente.uf());
            cliente.setDf(novoCliente.df());
            cliente.setICMS(novoCliente.isICMS());

            clienteRepository.save(cliente);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public void alterarCliente(ClienteDtoRequest novoCliente){
        Cliente cliente = clienteRepository.findByCnpj(novoCliente.cnpj());
        if(cliente!=null){
            cliente.setNome(novoCliente.nome());
            cliente.setObs(novoCliente.obs());
            cliente.setCep(novoCliente.cep());
            cliente.setEndereco(novoCliente.endereco());
            cliente.setMunicipio(novoCliente.municipio());
            cliente.setUf(novoCliente.uf());
            cliente.setDf(novoCliente.df());
            cliente.setICMS(novoCliente.isICMS());
            clienteRepository.save(cliente);
        }

    }

    public void deletarCliente(long cnpj){
        System.out.println("Entrou nodelete");
        clienteRepository.delete(clienteRepository.findByCnpj(cnpj));
    }

    public List<Cliente> listarClientes (){
        return clienteRepository.findAll();
    }
}
