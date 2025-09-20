package pedidosApp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.Cliente;
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

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    public void novoPedido(PedidoDtoRequest pedidoDtoRequest){
        Pedido novoPedido = new Pedido();
        novoPedido.setCliente(buscarCliente(pedidoDtoRequest.cnpj()));
        novoPedido.setICMS(new BigDecimal(pedidoDtoRequest.ICMS()));
        novoPedido.setDIFAL(new BigDecimal(pedidoDtoRequest.DIFAL()));
        novoPedido.setIPI(new BigDecimal(pedidoDtoRequest.IPI()));
        novoPedido.setCOFINS(new BigDecimal(pedidoDtoRequest.COFINS()));
        novoPedido.setValidade(pedidoDtoRequest.validade());
        novoPedido.setCondicaoFrete(pedidoDtoRequest.condicoesFrete());
        novoPedido.setObservacoes(pedidoDtoRequest.observacoes());
        novoPedido.setData(Date.from(Instant.now()));
        pedidoRepository.save(novoPedido);
    }

    public void novoItem (Item item){
        itemRepository.save(item);
    }

    private Cliente buscarCliente (Long cnpj){
        return clienteRepository.findByCnpj(cnpj);
    }
}
