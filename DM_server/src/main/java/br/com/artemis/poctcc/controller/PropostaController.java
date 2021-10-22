package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.proposta.PropostaRequest;
import br.com.artemis.poctcc.repository.model.Proposta;
import br.com.artemis.poctcc.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/propostas")
@AllArgsConstructor
public class PropostaController {

    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<Proposta> create(@RequestBody PropostaRequest request){

        Proposta proposta = propostaService
                .criarProposta(request.getIdItem(), request.getIdInstituicao());

        return ResponseEntity.ok(proposta);
    }
}
