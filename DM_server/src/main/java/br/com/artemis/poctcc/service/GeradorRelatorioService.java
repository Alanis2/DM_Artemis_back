package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioOngDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class GeradorRelatorioService {

    public static ByteArrayInputStream montarRelatorioOngs(RelatorioOngDTO relatorioOngDTO) {

        try{
            Document document = new Document(PageSize.A4);

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance( document, out);
            document.addAuthor("Doe+");
            document.addTitle("Relatorio Da Ong");

            document.open();
            document.add(new Paragraph("Hello Word"));
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException de ){
            System.err.println(de.getMessage());
        }
        return null;
    }
}
