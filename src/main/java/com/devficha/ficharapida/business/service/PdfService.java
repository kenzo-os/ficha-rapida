package com.devficha.ficharapida.business.service;

import com.devficha.ficharapida.business.entities.FichaAtendimento;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import java.io.ByteArrayOutputStream;

@Service
public class PdfService {
    public byte[] gerarPdfFicha(FichaAtendimento fichaAtendimento){
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);


        }
    }
}
