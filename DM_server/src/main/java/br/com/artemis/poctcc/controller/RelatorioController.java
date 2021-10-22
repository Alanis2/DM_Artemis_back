package br.com.artemis.poctcc.controller;

import br.com.artemis.poctcc.service.GeradorRelatorioService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {


    @PostMapping("/ong")
    public ResponseEntity<InputStreamResource> criarRelatorioOng() {

        // TODO busca base de dados das ongs
        // TODO mapear para dto do pdf
        ByteArrayInputStream pdf = GeradorRelatorioService.montarRelatorioOngs(null);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","inline; filename=ongs.pdf");

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}
