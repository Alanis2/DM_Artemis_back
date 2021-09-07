package br.com.artemis.poctcc.repository;

import br.com.artemis.poctcc.repository.model.Item;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUsuario(Usuario usuario);
}
