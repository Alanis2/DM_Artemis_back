package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
