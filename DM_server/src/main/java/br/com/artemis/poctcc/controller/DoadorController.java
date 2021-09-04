package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.doador.DoadorRequest;
import br.com.artemis.poctcc.repository.DoadorRepository;
import br.com.artemis.poctcc.repository.model.Doador;
import br.com.artemis.poctcc.service.DoadorMaper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/doadores")
public class DoadorController {


    private DoadorMaper doadorMaper;
    private DoadorRepository doadorRepository;

    @PostMapping
    public ResponseEntity<Doador> create(@RequestBody DoadorRequest request) {


        // Converter Request em Doador
        Doador doador = doadorMaper.mapearTabela(request);

        // Enviar para o banco um Doador
        doador = doadorRepository.save(doador);

        // Retonar response de sucesso status 201 e Objeto Doador
        return ResponseEntity.status(201).body(doador);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Doador> buscarId(@PathVariable Long id) {

        //TODO Buscar Doador Na Tabela Com Id No Caso De Não Existir, Criar Response Com 404
        Doador doador = doadorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Doador Não Encontrado"));
        //TODO Retornar Response Com Doador, Status 200
        return ResponseEntity.status(200).body(doador);
    }


    @GetMapping
    public ResponseEntity<List<Doador>> buscarTodos() {

        //TODO Buscar Doadores Da Tabela
        List<Doador> doadores = doadorRepository
                .findAll();
        //TODO Retornar Response Com Doadores, Status 200
        return ResponseEntity.status(200).body(doadores);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Doador> atualizar(@RequestBody DoadorRequest request, @PathVariable Long id){

        //TODO Buscar No Banco Dados Passando O Id
        Doador doador = doadorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Doador Não Encontrado"));

        // TODO Atualizar Os Dados Do Doador
        doador = doadorMaper.mapearTabela(request, doador);
        doadorRepository.save(doador);


        // TODO Retornar Response Com Doador Atualizado Status 200

        return ResponseEntity.status(200).body(doador);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        //TODO Buscar No Banco Dados Passando O Id
        Doador doador = doadorRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Doador Não Encontrado"));

        // TODO Atualizar Os Dados Do Doador
        doadorRepository.delete(doador);


        // TODO Retornar Response Com Doador Atualizado Status 200

        return ResponseEntity.noContent().build();
    }
}
