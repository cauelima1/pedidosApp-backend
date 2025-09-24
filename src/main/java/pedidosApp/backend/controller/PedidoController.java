package pedidosApp.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedidosApp.backend.entity.DTO.PedidoDtoRequest;
import pedidosApp.backend.entity.Pedido;
import pedidosApp.backend.service.ItemService;
import pedidosApp.backend.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<?> criarPedido (@RequestBody PedidoDtoRequest pedidoDTO){
         pedidoService.salvarPedido(pedidoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarPedido (@PathVariable Long id, @RequestBody PedidoDtoRequest pedidoDTO){
        Pedido novoPedido = pedidoService.alterar(id, pedidoDTO);
        return ResponseEntity.ok(novoPedido);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos (){
        return ResponseEntity.ok(pedidoService.getPedidos());
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id){
        pedidoService.deletar(id);
    }
}
