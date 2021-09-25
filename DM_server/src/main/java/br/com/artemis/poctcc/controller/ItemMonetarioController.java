package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.item.ItemRequest;
import br.com.artemis.poctcc.controller.dto.itemMonetario.ItemMonetarioRequest;
import br.com.artemis.poctcc.repository.ItemMonetarioRepository;
import br.com.artemis.poctcc.repository.model.Item;
import br.com.artemis.poctcc.repository.model.ItemMonetario;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.service.AuthenticationManagerService;
import br.com.artemis.poctcc.service.ItemMonetarioMaper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/itensMonetarios")
public class ItemMonetarioController {

    private ItemMonetarioMaper itemMonetarioMaper;
    private ItemMonetarioRepository itemMonetarioRepository;
    private AuthenticationManagerService authenticationManagerService;

    @PostMapping
    public ResponseEntity<ItemMonetario> create(@RequestBody ItemMonetarioRequest request, @RequestHeader("Authorization") String token){
        Usuario usuario = authenticationManagerService.getUsuarioByToken(token);

        ItemMonetario itemMonetario = itemMonetarioMaper.mapearTabela(request, usuario);

        itemMonetario = itemMonetarioRepository.save(itemMonetario);

        return ResponseEntity.status(201).body(itemMonetario);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemMonetario> buscarId(@PathVariable Long id){
        ItemMonetario itemMonetario = itemMonetarioRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        return ResponseEntity.status(200).body(itemMonetario);
    }

    @GetMapping
    public ResponseEntity<List<ItemMonetario>> buscarTodos(@RequestHeader("Authorization") String token){
        Usuario usuario = authenticationManagerService.getUsuarioByToken(token);

        List <ItemMonetario> itensMonetarios = itemMonetarioRepository
                .findAllByUsuario(usuario);

        return ResponseEntity.status(200).body(itensMonetarios);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemMonetario> atualizar(@RequestBody ItemMonetarioRequest request, @PathVariable Long id){
        ItemMonetario itemMonetario = itemMonetarioRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        itemMonetario = itemMonetarioMaper.mapearTabela(request, itemMonetario);
        itemMonetarioRepository.save(itemMonetario);

        return ResponseEntity.status(200).body(itemMonetario);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ItemMonetario> deletar(@PathVariable Long id) {
        ItemMonetario itemMonetario = itemMonetarioRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        itemMonetarioRepository.delete(itemMonetario);

        return ResponseEntity.noContent().build();
    }
}
