package br.com.artemis.poctcc.controller;


import br.com.artemis.poctcc.model.Usuario;
import br.com.artemis.poctcc.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsuarioController {

    UsuarioRepository repository;

    @GetMapping("/usuario")
    public List<Usuario> getAllUsuarios(){
        return repository.findAll();
    }

    @PostMapping("/usuario")
    public Usuario saveUsuario(@RequestBody Usuario usuario){
        repository.findAll()
                .stream()
                .filter(usuario1 -> usuario1.getCpf().equals(usuario.getCpf()))
                .findFirst()
                .ifPresent(usuario1 -> {
                    throw new RuntimeException("CPF já cadastrado!");
                });
        return repository.save(usuario);
    }
}
