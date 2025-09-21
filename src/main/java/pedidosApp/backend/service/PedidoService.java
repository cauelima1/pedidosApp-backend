package pedidosApp.backend.service;

import org.aspectj.weaver.AnnotationAnnotationValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.Cliente;
import pedidosApp.backend.entity.DTO.ItemDtoRequest;
import pedidosApp.backend.entity.DTO.PedidoDtoRequest;
import pedidosApp.backend.entity.Item;
import pedidosApp.backend.entity.Pedido;
import pedidosApp.backend.repository.ClienteRepository;
import pedidosApp.backend.repository.ItemRepository;
import pedidosApp.backend.repository.PedidoRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Pedido novoPedido(PedidoDtoRequest pedidoDtoRequest){
        Pedido novoPedido = new Pedido();
        if(!pedidoRepository.existsById(pedidoDtoRequest.cnpj())){
            novoPedido.setCliente(buscarCliente(pedidoDtoRequest.cnpj()));
            novoPedido.setNomeCliente(novoPedido.getCliente().getNome());
            novoPedido.setICMS(conversorDeValores(pedidoDtoRequest.ICMS()));
            novoPedido.setDIFAL(conversorDeValores(pedidoDtoRequest.DIFAL()));
            novoPedido.setIPI(conversorDeValores(pedidoDtoRequest.IPI()));
            novoPedido.setCOFINS(conversorDeValores(pedidoDtoRequest.COFINS()));
            novoPedido.setValidade(pedidoDtoRequest.validade());
            novoPedido.setCondicaoFrete(pedidoDtoRequest.condicoesFrete());
            novoPedido.setObservacoes(pedidoDtoRequest.observacoes());
            novoPedido.setData(Date.from(Instant.now()));
            pedidoRepository.save(novoPedido);
            return novoPedido;
        } else {
            throw new RuntimeException("Client id (CNPJ) not exists");
        }
    }

    public Pedido alterar(Long id, PedidoDtoRequest pedidoDtoRequest){
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if(pedidoExistente.isPresent()){
            pedidoExistente.get().setICMS(conversorDeValores(pedidoDtoRequest.ICMS()));
            pedidoExistente.get().setDIFAL(conversorDeValores(pedidoDtoRequest.DIFAL()));
            pedidoExistente.get().setIPI(conversorDeValores(pedidoDtoRequest.IPI()));
            pedidoExistente.get().setCOFINS(conversorDeValores(pedidoDtoRequest.COFINS()));
            pedidoExistente.get().setValidade(pedidoDtoRequest.validade());
            pedidoExistente.get().setCondicaoFrete(pedidoDtoRequest.condicoesFrete());
            pedidoExistente.get().setObservacoes(pedidoDtoRequest.observacoes());
            pedidoExistente.get().setData(Date.from(Instant.now()));
            pedidoRepository.save(pedidoExistente.get());
            return pedidoExistente.get();
        } else {
            throw new RuntimeException("Client id (CNPJ) not exists");
        }
    }

    public void deletar(Long id) {
        if(pedidoRepository.existsById(id)){
            pedidoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pedido not exists");
        }
    }

    public List<Pedido> getPedidos () {
        return pedidoRepository.findAll();
    }

    private Cliente buscarCliente (Long cnpj){
        return clienteRepository.findByCnpj(cnpj);
    }

    private BigDecimal conversorDeValores (String valorString){
        valorString = valorString.replace(",", ".");
        return new BigDecimal(valorString);
    }
}
