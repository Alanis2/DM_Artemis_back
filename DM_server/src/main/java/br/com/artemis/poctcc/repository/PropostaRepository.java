package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Proposta;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    List<Proposta> findByItem_Usuario(Usuario usuario);
}
