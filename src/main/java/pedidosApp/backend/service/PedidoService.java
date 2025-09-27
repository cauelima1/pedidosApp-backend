package pedidosApp.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.Cliente;
import pedidosApp.backend.entity.DTO.PedidoDtoRequest;
import pedidosApp.backend.entity.Pedido;
import pedidosApp.backend.entity.enums.StatusPedido;
import pedidosApp.backend.repository.ClienteRepository;
import pedidosApp.backend.repository.ItemRepository;
import pedidosApp.backend.repository.PedidoRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Pedido salvarPedido(PedidoDtoRequest pedidoDtoRequest){
        Pedido novoPedido = new Pedido();
        novoPedido.setCliente(clienteRepository.findByCnpj(pedidoDtoRequest.idCliente()));

        novoPedido.setCondicaoFrete(pedidoDtoRequest.condicaoFrete());
        novoPedido.setData(Date.from(Instant.now()));
        novoPedido.setObservacoes(pedidoDtoRequest.observacoes());
        novoPedido.setCondicaoFrete(pedidoDtoRequest.condicaoFrete());
        novoPedido.setIpi(conversorDeValores(pedidoDtoRequest.ipi()));
        novoPedido.setSt(conversorDeValores(pedidoDtoRequest.st()));
        novoPedido.setMc(conversorDeValores(pedidoDtoRequest.mc()));
        novoPedido.setMc1(conversorDeValores(pedidoDtoRequest.mc1()));
        novoPedido.setFrete(conversorDeValores(pedidoDtoRequest.frete()));
        novoPedido.setStvd(conversorDeValores(pedidoDtoRequest.stvd()));
        novoPedido.setIcms(conversorDeValores(pedidoDtoRequest.icms()));
        novoPedido.setStatusPedido(StatusPedido.ABERTO);

        Pedido pedidoSalvo = pedidoRepository.save(novoPedido);
        pedidoRepository.save(pedidoSalvo);
    return novoPedido;
    }

    public Pedido alterar(Long id, PedidoDtoRequest pedidoDtoRequest){
return null;
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

    public BigDecimal conversorDeValores (String valorString){

        if(valorString!= null){
            valorString = valorString.replace(",", ".");
        } else {
            valorString = "0.0";
        }
        return new BigDecimal(valorString);
    }
}
