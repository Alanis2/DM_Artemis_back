package br.com.artemis.poctcc.controller.dto.itemMonetario;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemMonetarioRequest {
        private Long id;
        private String nome;
        private String image;
        private Double valor;
        private String descricao;
}
