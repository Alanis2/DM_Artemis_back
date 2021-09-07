package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.instituicao.InstituicaoResquest;
import br.com.artemis.poctcc.repository.InstituicaoRepository;
import br.com.artemis.poctcc.repository.model.Instituicao;
import br.com.artemis.poctcc.service.InstituicaoMaper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/instituicoes")
public class InstituicaoController {

    private InstituicaoMaper instituicaoMaper;
    private InstituicaoRepository instituicaoRepository;

    @PostMapping()
    public ResponseEntity<Instituicao> create(@RequestBody InstituicaoResquest resquest) {

        // Converte O Resquest Em Doador
        Instituicao instituicao = instituicaoMaper.mapearTabela(resquest);

        // Envia O Doador Para O Banco De Dados
        instituicao = instituicaoRepository.save(instituicao);

        // Mostrar A Resposta Do Status 201 E A Instituicao
        return ResponseEntity.status(201).body(instituicao);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Instituicao> buscarId(@PathVariable Long id) {

        // Buscar Pelo Id No Banco De Dados
        Instituicao instituicao = instituicaoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Instituicao não encontrada"));
        return ResponseEntity.status(200).body(instituicao);
    }


    @GetMapping
    public ResponseEntity<List<Instituicao>> BuscarTodos() {

        List<Instituicao> instituicoes = instituicaoRepository
                .findAll();

        return ResponseEntity.status(200).body(instituicoes);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Instituicao> atualizar(@RequestBody InstituicaoResquest request, @PathVariable Long id) {

        // Buscar Pelo Id No Banco De Dados
        Instituicao instituicao = instituicaoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Instituicao não encontrada"));

        instituicao = instituicaoMaper.mapearTabela(request, instituicao);
        instituicaoRepository.save(instituicao);

        return ResponseEntity.status(200).body(instituicao);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Instituicao> deletar(@PathVariable Long id ){
        Instituicao instituicao = instituicaoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Instituicao não encontrada"));

        instituicaoRepository.delete(instituicao);

        return ResponseEntity.noContent().build();
    }
}
