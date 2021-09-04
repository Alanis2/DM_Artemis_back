package br.com.artemis.poctcc.repository.model;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Instituicao {

    private Long Id;
    private String nomeFantasia;
    private String razaoSocial;
    private String focoInstitucional;
    private String CNPJ;
    private String dtFundacao;
    private String telefone;
    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String CEP;
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Usuario usuario;

}
