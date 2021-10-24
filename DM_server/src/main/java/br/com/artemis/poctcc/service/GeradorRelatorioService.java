package br.com.artemis.poctcc.service;

import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioOngDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Cell;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class GeradorRelatorioService {

    public static ByteArrayInputStream montarRelatorioOngs(RelatorioOngDTO relatorioOngDTO) {

        try{
            Document document = new Document(PageSize.A4);
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance( document, out);

            Paragraph quantidadeOng = new Paragraph("Quantidade de Ongs Cadastradas");

            quantidadeOng.setSpacingAfter(20);

            PdfPTable table = new PdfPTable(2);
            table.addCell("Ongs Cadastradas");
            table.addCell(new Phrase("Quantidade"));

            PdfPCell cell1 = new PdfPCell();
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            Map<String,Integer> qtdOng = new HashMap<String,Integer>();

            qtdOng.put("semana", 321);
            qtdOng.put("mes", 332);
            qtdOng.put("ano", 3200);

            qtdOng.forEach((key, value) -> {
                table.addCell(key);
                cell1.setPhrase(new Phrase(value.toString()));
                table.addCell(cell1);
            });

            table.setSpacingAfter(20);

            Paragraph ongLista = new Paragraph("Lista de Ongs");
            ongLista.setSpacingAfter(20);

            PdfPCell cell2 = new PdfPCell();
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

            Map<String, Integer> ltOng = new HashMap<String,Integer>();

            PdfPTable table2 = new PdfPTable(3);

            table2.addCell("Nome Fantasia");
            table2.addCell(new Phrase("Razão Social"))   ;
            table2.addCell(new Phrase("Categoria"));

            for (int i = 0; i < 12; i++){
                table2.addCell("Criança Esperança");
            }


            document.addAuthor("Doe+");
            document.addTitle("Relatorio Da Ong");

            Paragraph paragraph = new Paragraph("Relatorio Da Ong");
            paragraph.setAlignment(1);

            document.open();
            document.add(paragraph);
            document.add( Chunk.NEWLINE);
            document.add(quantidadeOng);
            document.add(table);
            document.add(ongLista);
            document.add(table2);

            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException de ){
            System.err.println(de.getMessage());
        }
        return null;
    }
}
