package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.item.ItemRequest;
import br.com.artemis.poctcc.repository.ItemRepository;
import br.com.artemis.poctcc.repository.model.Item;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.service.AuthenticationManagerService;
import br.com.artemis.poctcc.service.ItemMaper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/itens")
public class ItemController {

//    , @RequestHeader("Authorization") String token

    private ItemMaper itemMaper;
    private ItemRepository itemRepository;
    private AuthenticationManagerService authenticationManagerService;

    @PostMapping()
    public ResponseEntity<Item> create(@RequestBody ItemRequest request, @RequestHeader("Authorization") String token){

        //TODO buscar usuario logado
        Usuario usuario = authenticationManagerService.getUsuarioByToken(token);

        Item item = itemMaper.mapearTabela(request, usuario);

        item = itemRepository.save(item);

        return ResponseEntity.status(200).body(item);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> buscarId(@PathVariable Long id){
        Item item = itemRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        return ResponseEntity.status(200).body(item);
    }

    @GetMapping
    public  ResponseEntity<List<Item>> buscarTodos(@RequestHeader("Authorization") String token){

        Usuario usuario = authenticationManagerService.getUsuarioByToken(token);

        List <Item> itens = itemRepository
                .findAllByUsuario(usuario);

        return ResponseEntity.status(200).body(itens);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Item> atualizar(@RequestBody ItemRequest request, @PathVariable Long id){
        Item item = itemRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        item = itemMaper.mapearTabela(request, item);
        itemRepository.save(item);

        return ResponseEntity.status(200).body(item);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Item> deletar(@PathVariable Long id){
        Item item = itemRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        itemRepository.delete(item);

        return ResponseEntity.noContent().build();
    }
}
