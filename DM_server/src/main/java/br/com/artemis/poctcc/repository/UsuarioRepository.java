package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
