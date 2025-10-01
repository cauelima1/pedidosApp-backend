package pedidosApp.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedidosApp.backend.entity.DTO.ItemDtoRequest;
import pedidosApp.backend.entity.DTO.PedidoDtoRequest;
import pedidosApp.backend.entity.DTO.StatusEnumDTO;
import pedidosApp.backend.entity.Pedido;
import pedidosApp.backend.service.ItemService;
import pedidosApp.backend.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;



    @PostMapping
    public ResponseEntity<Long> criarPedido (@RequestBody PedidoDtoRequest pedidoDTO){
          Pedido pedido = pedidoService.salvarPedido(pedidoDTO);
          Long n = pedido.getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(n);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Pedido> finalizarPedido (@PathVariable Long id){
        Pedido pedido = pedidoService.finalizar(id);
        return ResponseEntity.ok().body(pedido);
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

    @GetMapping("/{id}")
    public ResponseEntity<List<Pedido>> getPedidosCliente (@PathVariable Long id) {
        List<Pedido> list = pedidoService.getPedidosCliente(id);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<Pedido> atualizarStatusPedido(@PathVariable Long id, @RequestBody StatusEnumDTO status){
        Pedido pedido = pedidoService.atualizarStatus(id, status);
        return ResponseEntity.ok().body(pedido);
    }

    @DeleteMapping("/{id}")
    public void deletarPedido(@PathVariable Long id){
        pedidoService.deletar(id);
    }
}
