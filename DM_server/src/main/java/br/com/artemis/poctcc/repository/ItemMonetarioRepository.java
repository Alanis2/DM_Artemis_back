package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Item;
import br.com.artemis.poctcc.repository.model.ItemMonetario;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMonetarioRepository extends JpaRepository<ItemMonetario, Long> {
    List<ItemMonetario> findAllByUsuario(Usuario usuario);
}
