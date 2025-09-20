package pedidosApp.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pedidosApp.backend.entity.Cliente;
import pedidosApp.backend.entity.DTO.PedidoDtoRequest;
import pedidosApp.backend.entity.Item;
import pedidosApp.backend.service.ItemService;
import pedidosApp.backend.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<?> criarPedido (@RequestBody PedidoDtoRequest pedidoDTO){
        pedidoService.novoPedido(pedidoDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/item")
    public void criarItem(@RequestBody Item item){
        itemService.novoItem(item);
    }
}
