package com.devficha.ficharapida.controller;

import com.devficha.ficharapida.business.service.FichaService;
import com.devficha.ficharapida.business.entities.FichaAtendimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fichas")
public class FichaAtendimentoController {
    @Autowired
    private FichaService fichaService;

    @PostMapping
    public ResponseEntity<FichaAtendimento> criarFicha(@RequestBody FichaAtendimento fichaAtendimento){
        FichaAtendimento nova = fichaService.salvar(fichaAtendimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @GetMapping
    public ResponseEntity<List<FichaAtendimento>> listarFicha(){
        return ResponseEntity.ok(fichaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaAtendimento> findById(@PathVariable Long id) {
        return fichaService.buscarPorId(id)
                .map(ficha -> ResponseEntity.ok().body(ficha))
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFichaPorId(@PathVariable Long id){
        fichaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
