package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.controller.dto.ChaveDTO;
import br.com.artemis.poctcc.controller.dto.LoginDTO;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.Random;

@RestController
@AllArgsConstructor
public class LoginController {

    UsuarioRepository repository;

    @PostMapping("/login")
    public ChaveDTO login(@RequestBody LoginDTO loginDTO) {


        Usuario usuarioLogin = repository.findAll()
                .stream()
                .filter(usuario ->
                        usuario
                                .getEmail().equals(loginDTO.getEmail())
                                &&
                                usuario
                                        .getSenha().equals(loginDTO.getSenha())
                )
                .findFirst()
                .orElse(null);

        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return new ChaveDTO(generatedString);
    }
}
