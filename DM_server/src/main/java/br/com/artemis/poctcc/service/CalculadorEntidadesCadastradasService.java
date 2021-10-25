package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.controller.dto.relatorio.ResumoCadastroDataDTO;
import br.com.artemis.poctcc.repository.model.Doador;
import br.com.artemis.poctcc.repository.model.Instituicao;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class CalculadorEntidadesCadastradasService {


    public ResumoCadastroDataDTO calcular(List<Instituicao> instituicaos){
        ResumoCadastroDataDTO resumoCadastroDataDTO = new ResumoCadastroDataDTO();
        // Calculo Ong
        Long numerosemana = getCount(instituicaos,7);
        Long numeroMes = getCount(instituicaos,30);
        Long numeroAno = getCount(instituicaos,365);
        // TODO fazer os calculos
        resumoCadastroDataDTO.setSemana(numerosemana);
        resumoCadastroDataDTO.setMês(numeroMes);
        resumoCadastroDataDTO.setAno(numeroAno);



        return resumoCadastroDataDTO;
    }

    private Long getCount(List<Instituicao> instituicaos, Integer qtdPar) {
        return instituicaos.stream().filter(instituicao -> {
            LocalDateTime dataCriacao = instituicao.getDataCriacao();
            long quantidadeDias = Duration.between(dataCriacao, LocalDateTime.now()).toDays();
            return quantidadeDias < qtdPar;
        }).count();
    }

    // Calculo Doadores

    public ResumoCadastroDataDTO calcular2(List<Doador> doadors) {
        ResumoCadastroDataDTO resumoCadastroDataDTO = new ResumoCadastroDataDTO();
        //Calculo Doador

        Long numeroSemana = getConta(doadors, 7);
        Long numeromes = getConta(doadors, 30);
        Long numeroano = getConta(doadors, 365);

        resumoCadastroDataDTO.setSemana(numeroSemana);
        resumoCadastroDataDTO.setMês(numeromes);
        resumoCadastroDataDTO.setAno(numeroano);

        return resumoCadastroDataDTO;
    }

    private Long getConta(List<Doador> doadors, Integer qtdPar) {
        return doadors.stream().filter(doador -> {
            LocalDateTime dataCriacao = doador.getDataCriacao();
            long quantidadeDias = Duration.between(dataCriacao, LocalDateTime.now()).toDays();
            return quantidadeDias < qtdPar;
        }).count();
    }
}
