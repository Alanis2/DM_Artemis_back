package br.com.artemis.poctcc.controller.dto.doador;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoadorRequest {
    private String nome;
    private String cpf;
    private String dtNasc;
    private String telefone;
    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
    private String email;
    private String senha;
}
