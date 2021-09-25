package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.controller.dto.itemMonetario.ItemMonetarioRequest;
import br.com.artemis.poctcc.repository.model.ItemMonetario;
import br.com.artemis.poctcc.repository.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ItemMonetarioMaper {

    public ItemMonetario mapearTabela(ItemMonetarioRequest request, Usuario usuario){
        ItemMonetario itemMonetario = new ItemMonetario();

        itemMonetario.setNome(request.getNome());
        itemMonetario.setImage(request.getImage());
        itemMonetario.setValor(request.getValor());
        itemMonetario.setDescricao(request.getDescricao());
        itemMonetario.setUsuario(usuario);

        return itemMonetario;
    }

    public ItemMonetario mapearTabela(ItemMonetarioRequest request, ItemMonetario itemMonetario){
        itemMonetario.setNome(request.getNome());
        itemMonetario.setImage(request.getImage());
        itemMonetario.setValor(request.getValor());
        itemMonetario.setDescricao(request.getDescricao());

        return itemMonetario;
    }

}
