package pedidosApp.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pedidosApp.backend.entity.DTO.ItemDtoRequest;
import pedidosApp.backend.entity.Item;
import pedidosApp.backend.repository.ItemRepository;
import pedidosApp.backend.repository.PedidoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Item novoItem (ItemDtoRequest itemDtoRequest){
    if(pedidoRepository.existsById(itemDtoRequest.idPedido())) {
        Item item = new Item();
        item.setPedido(pedidoRepository.findById(itemDtoRequest.idPedido()).get());
        item.setFabricante(itemDtoRequest.fabricante());
        item.setNome(itemDtoRequest.nome());
        item.setObs(itemDtoRequest.obs());
        item.setQuantidade((pedidoService.conversorDeValores(itemDtoRequest.quantidade())));
        item.setTipo(itemDtoRequest.tipo());
        item.setCusto(pedidoService.conversorDeValores(itemDtoRequest.custo()));
        calculoItem(item);
        return item;
        } else {
        return null;
    }
    }

    public Item alterarItem(ItemDtoRequest itemDtoRequest, Long id){
        if(itemRepository.existsById(id)){
            Item itemAlterado = itemRepository.findById(id).get();
            itemAlterado.setNome(itemDtoRequest.nome());
            itemAlterado.setObs(itemDtoRequest.obs());
            itemAlterado.setFabricante(itemDtoRequest.fabricante());
            itemAlterado.setQuantidade(pedidoService.conversorDeValores(itemDtoRequest.quantidade()));
            itemAlterado.setCusto(pedidoService.conversorDeValores(itemDtoRequest.custo()));
            itemAlterado.setTipo(itemDtoRequest.tipo());
            return itemRepository.save(itemAlterado);
        } else {
            throw new RuntimeException("Erro ao alterar Item.");
        }
    }

    public void calculoItem (Item item) {
        BigDecimal ipi = (item.getPedido().getIpi()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal st = (item.getPedido().getSt()).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);
        BigDecimal mc = (item.getPedido().getMc()).divide(BigDecimal.valueOf(100),2 , RoundingMode.HALF_UP);
        BigDecimal mc1 = (item.getPedido().getMc1()).divide(BigDecimal.valueOf(100),2 , RoundingMode.HALF_UP);
        BigDecimal frete = (item.getPedido().getFrete()).divide(BigDecimal.valueOf(100),2 , RoundingMode.HALF_UP);
        BigDecimal stvd = (item.getPedido().getStvd()).divide(BigDecimal.valueOf(100),2 , RoundingMode.HALF_UP);

        item.setIpi(item.getCusto().multiply(ipi));
        item.setSt(item.getCusto().multiply(st));
        item.setCustoUnitario(item.getCusto().add(item.getIpi()).add(item.getSt()));
        item.setCustoTotal(item.getCustoUnitario().multiply(item.getQuantidade()));
        item.setMc(item.getCustoUnitario().multiply(mc));
        item.setMc1(item.getMc().multiply(mc1));
        item.setFrete(item.getMc().multiply(frete));
        item.setVtot((item.getMc().add(item.getFrete())).multiply(item.getQuantidade()));
        item.setStvd((item.getMc1().add(item.getFrete())).multiply(stvd));
        item.setPrcf(item.getMc1().add(item.getFrete().add(item.getStvd())));
        item.setVtotf(item.getPrcf().multiply(item.getQuantidade()));
        itemRepository.save(item);
    }

    public List<Item> listarItemsPedido(Long pedidoId){
        return itemRepository.findAll().stream().filter(
                p-> p.getPedido().getId().equals(pedidoId)).toList();
    }

    public void deletarItem(Long id){
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
        }
    }
}
