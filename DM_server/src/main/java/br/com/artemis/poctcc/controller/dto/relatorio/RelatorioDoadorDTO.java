package br.com.artemis.poctcc.controller.dto.relatorio;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RelatorioDoadorDTO {
    private Long id;
    private Long idDoador;
    private String dtNasc;
    private String nomeDoador;
}
