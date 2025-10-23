package com.devficha.ficharapida.controller;

import com.devficha.ficharapida.business.entities.FichaAtendimento;
import com.devficha.ficharapida.business.service.FichaService;
import com.devficha.ficharapida.business.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fichas")
public class FichaAtendimentoController {

    @Autowired
    private FichaService fichaService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> gerarPdfFicha(@PathVariable Long id) {
        try {
            FichaAtendimento ficha = fichaService.buscarPorId(id);
            
            if (ficha == null) {
                return ResponseEntity.notFound().build();
            }

            byte[] pdfBytes = pdfService.gerarPdfFicha(ficha);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "ficha_atendimento_" + id + ".pdf");
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<FichaAtendimento> criarFicha(@RequestBody FichaAtendimento ficha) {
        FichaAtendimento fichaCreated = fichaService.salvar(ficha);
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaAtendimento> buscarFicha(@PathVariable Long id) {
        FichaAtendimento ficha = fichaService.buscarPorId(id);
        
        if (ficha == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(ficha);
    }
}
