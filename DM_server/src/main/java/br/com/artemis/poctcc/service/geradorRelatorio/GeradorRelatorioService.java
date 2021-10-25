package br.com.artemis.poctcc.service.geradorRelatorio;

import br.com.artemis.poctcc.controller.dto.relatorio.RelatorioOngDTO;
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
import java.util.stream.Collectors;

public class GeradorRelatorioService {

    public static ByteArrayInputStream montarRelatorioOngs(
            List<RelatorioOngDTO> relatorioOngDTO,
            ResumoCadastroDataDTO resumoCadastroDataDTO
    ) {

        try{
            Document document = new Document(PageSize.A4);
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance( document, out);

            Paragraph quantidadeOng = new Paragraph("Quantidade de Ongs Cadastradas");

            PdfPTable table = montarTabelaOng(quantidadeOng, resumoCadastroDataDTO);

            //Lista De Ongs

            Paragraph ongLista = new Paragraph("Lista de Ongs");
            ongLista.setSpacingAfter(20);

            PdfPCell cell2 = new PdfPCell();

            PdfPTable table2 = new PdfPTable(3);

            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell("Nome Fantasia");
            table2.addCell(new Phrase("Razão Social"))   ;
            table2.addCell(new Phrase("Categoria"));



            relatorioOngDTO.forEach(ong -> {
                table2.addCell(ong.getNomeOng());
                table2.addCell(new Phrase(ong.getRazaoSocial()));
                table2.addCell(new Phrase(ong.getCategoria()));

            });
            for (int i = 0; i < 12; i++){
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
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

    private static PdfPTable montarTabelaOng(Paragraph quantidadeOng, ResumoCadastroDataDTO resumoCadastroDataDTO) {
        quantidadeOng.setSpacingAfter(20);

        PdfPTable table = new PdfPTable(2);
        table.addCell("Ongs Cadastradas");
        table.addCell(new Phrase("Quantidade"));

        PdfPCell cell1 = new PdfPCell();
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        Map<String,Long> qtdOng = new HashMap<>();


        qtdOng.put("Semana", resumoCadastroDataDTO.getSemana());
        qtdOng.put("Mês", resumoCadastroDataDTO.getMês());
        qtdOng.put("Ano", resumoCadastroDataDTO.getAno());


        for (int i = 0; i < qtdOng.size(); i++) {
            table.addCell(new ArrayList<>(qtdOng.keySet()).get(i));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setPhrase(new Phrase(new ArrayList<>(qtdOng.values()).get(i).toString()));
            table.addCell(cell1);

        }


        table.setSpacingAfter(20);
        return table;
    }
}
