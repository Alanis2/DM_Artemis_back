package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.proposta.PropostaRequest;
import br.com.artemis.poctcc.repository.ItemRepository;
import br.com.artemis.poctcc.repository.PropostaRepository;
import br.com.artemis.poctcc.repository.UsuarioRepository;
import br.com.artemis.poctcc.repository.model.Proposta;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/propostas")
@AllArgsConstructor
public class PropostaController {

    private PropostaService propostaService;
    private PropostaRepository propostaRepository;
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Proposta> create(@RequestBody PropostaRequest request){

        Proposta proposta = propostaService
                .criarProposta(request.getIdItem(), request.getIdInstituicao());

        return ResponseEntity.ok(proposta);
    }

    @GetMapping
    public ResponseEntity<List<Proposta>> buscarTodos(@RequestHeader("Authorization") String token){

        Usuario usuario = usuarioRepository.findById(Long.parseLong(token))
                .orElseThrow(() -> new RuntimeException());
        List<Proposta> propostas = propostaRepository
                .findByItem_Usuario(usuario);
        return ResponseEntity.status(200).body(propostas);
    }
}
