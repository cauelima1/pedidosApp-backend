package pedidosApp.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedidosApp.backend.entity.DTO.ItemDtoRequest;
import pedidosApp.backend.entity.Item;
import pedidosApp.backend.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/pedidos/item")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> criarItem(@RequestBody ItemDtoRequest itemDtoRequest){
        Item item = itemService.novoItem(itemDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Item> alterarItem(@RequestBody ItemDtoRequest itemDtoRequest, @PathVariable Long itemId){
        Item item = itemService.alterarItem(itemDtoRequest, itemId);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<List<Item>> listaItemsPedido (@PathVariable Long pedidoId){
        List<Item> itemsLista = itemService.listarItemsPedido(pedidoId);
        return ResponseEntity.ok().body(itemsLista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id){
        itemService.deletarItem(id);
        return ResponseEntity.ok().build();
    }

}
