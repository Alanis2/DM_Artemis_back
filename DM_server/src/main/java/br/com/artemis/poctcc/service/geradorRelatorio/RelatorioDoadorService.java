package br.com.artemis.poctcc.service.geradorRelatorio;

import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioDoadorDTO;
import br.com.artemis.poctcc.controller.dto.relatorio.ResumoCadastroDataDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioDoadorService {

    public static ByteArrayInputStream montarRelatorioDoador(
        List<RelatorioDoadorDTO> relatorioDoadorDTO,
        ResumoCadastroDataDTO resumoCadastroDataDTO
    ){
       try{
           Document document = new Document(PageSize.A4);

           ByteArrayOutputStream out = new ByteArrayOutputStream();

           PdfWriter.getInstance( document, out);

           Paragraph quantidadeDoadores = new Paragraph("Quantidade de doadores cadastrados");

           PdfPTable table = montarTabelaDoador(quantidadeDoadores, resumoCadastroDataDTO);

           Paragraph doadorLista = new Paragraph("Lista de Doadoes");
           doadorLista.setSpacingAfter(20);

           PdfPCell cell2 = new PdfPCell();

           PdfPTable table2 = new PdfPTable(2);

           cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
           table2.addCell("Nome Doador");
           table2.addCell(new Phrase("Idade"));

           relatorioDoadorDTO.forEach(doador -> {
               table2.addCell(doador.getNomeDoador());
               table2.addCell(doador.getDtNasc());
           });

           for (int i = 0; i < 6; i++){
               cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
           }

           document.addAuthor("Doe+");
           document.addTitle("Relatorio De Doadores");

           Paragraph paragraph = new Paragraph("Relatorio De Doadores");
           paragraph.setAlignment(1);

           document.open();
           document.add(paragraph);
           document.add( Chunk.NEWLINE);
           document.add(quantidadeDoadores);
           document.add(table);
           document.add(doadorLista);
           document.add(table2);

           document.close();
           return new ByteArrayInputStream(out.toByteArray());
       } catch (DocumentException de ){
           System.err.println(de.getMessage());
       }
       return null;
    }

    private static PdfPTable montarTabelaDoador(Paragraph quantidadeDoadores, ResumoCadastroDataDTO resumoCadastroDataDTO) {
        quantidadeDoadores.setSpacingAfter(20);

        PdfPTable table = new PdfPTable(2);
        table.addCell("Doadores Cadastrados");
        table.addCell(new Phrase("Quantidade"));

        PdfPCell cell1 = new PdfPCell();
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        Map<String, Integer> qtdDoador = new HashMap<>();

        qtdDoador.put("Semana", resumoCadastroDataDTO.getSemana().intValue());
        qtdDoador.put("Mês", resumoCadastroDataDTO.getMês().intValue());
        qtdDoador.put("Ano", resumoCadastroDataDTO.getMês().intValue());


        for (int i = 0; i < qtdDoador.size(); i++){
            table.addCell(new ArrayList<>(qtdDoador.keySet()).get(i));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setPhrase((new Phrase((new ArrayList<>(qtdDoador.values()).get(i).toString()))));
            table.addCell(cell1);
        }

        table.setSpacingAfter(20);
        return table;
    }
}
