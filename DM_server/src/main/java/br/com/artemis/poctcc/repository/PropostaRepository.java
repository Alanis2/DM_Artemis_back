package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Doador;
import br.com.artemis.poctcc.repository.model.Instituicao;
import br.com.artemis.poctcc.repository.model.Proposta;
import br.com.artemis.poctcc.repository.model.Usuario;
import br.com.artemis.poctcc.repository.model.enums.StatusProposta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    List<Proposta> findByItem_Usuario(Usuario usuario);
    List<Proposta> findByInstituicao(Instituicao instituicao);
    List<Proposta> findByInstituicaoAndStatus(Instituicao instituicao, StatusProposta status);

//    List<Proposta> findByDoador(Doador doador);
//
//    List<Proposta> findByDoadorAndStatus(Doador doador, StatusProposta status);
}
