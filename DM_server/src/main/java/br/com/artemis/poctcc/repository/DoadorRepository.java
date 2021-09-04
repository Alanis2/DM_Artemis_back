package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoadorRepository extends JpaRepository<Doador, Long> {
}