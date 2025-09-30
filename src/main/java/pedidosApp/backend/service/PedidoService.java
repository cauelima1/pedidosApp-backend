package pedidosApp.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.Cliente;
import pedidosApp.backend.entity.DTO.PedidoDtoRequest;
import pedidosApp.backend.entity.Item;
import pedidosApp.backend.entity.Pedido;
import pedidosApp.backend.entity.enums.StatusPedido;
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

    @Lazy
    @Autowired
    private ItemService itemService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Pedido salvarPedido(PedidoDtoRequest pedidoDtoRequest){
            Pedido novoPedido = new Pedido();
            Cliente cliente = clienteRepository.findByCnpj(pedidoDtoRequest.idCliente());
        if (cliente != null) {
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
        } else {
            throw new RuntimeException("Erro ao cadastrar pedido;");
        }
    }

    public Pedido finalizar(Long id){
        if(pedidoRepository.existsById(id)){
            return calcularTotalPedido(id);
        }
        throw new RuntimeException("Id não do pedido não existe");
    }

    private Pedido calcularTotalPedido(Long id){
        Pedido pedidoParaFinalizar = pedidoRepository.findById(id).get();
        pedidoParaFinalizar.setValorTotal(pedidoParaFinalizar.getItems().stream()
                .map(Item::getVtotf)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return pedidoRepository.save(pedidoParaFinalizar);
    }

    public Pedido alterar(Long id, PedidoDtoRequest pedidoDtoRequest){
        if (pedidoRepository.existsById(id)){
            Pedido pedido = pedidoRepository.findById(id).get();
            pedido.setCondicaoFrete(pedidoDtoRequest.condicaoFrete());
            pedido.setObservacoes(pedidoDtoRequest.observacoes());
            pedido.setIpi(conversorDeValores(pedidoDtoRequest.ipi()));
            pedido.setSt(conversorDeValores(pedidoDtoRequest.st()));
            pedido.setMc(conversorDeValores(pedidoDtoRequest.mc()));
            pedido.setMc1(conversorDeValores(pedidoDtoRequest.mc1()));
            pedido.setFrete(conversorDeValores(pedidoDtoRequest.frete()));
            pedido.setStvd(conversorDeValores(pedidoDtoRequest.stvd()));
            pedido.setIcms(conversorDeValores(pedidoDtoRequest.icms()));
            List<Item> items = pedido.getItems();
            items.forEach(i -> itemService.calculoItem(i));
            return calcularTotalPedido(pedido.getId());
        } else {
            throw new RuntimeException("Pedido não existe!");
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

    public List<Pedido> getPedidosCliente (Long cnpj){
       return pedidoRepository.findAll().stream()
                .filter(p -> p.getCliente() != null && p.getCliente().getCnpj().equals(cnpj))
                .toList();
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
