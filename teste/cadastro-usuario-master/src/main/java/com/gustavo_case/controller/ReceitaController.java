package com.gustavo_case.controller;

import com.gustavo_case.business.ReceitaService;
import com.gustavo_case.infrastructure.entitys.Receita;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.List;


@RequestMapping("api/receita")
@RestController
@RequiredArgsConstructor
public class ReceitaController {
    private final ReceitaService receitaService;


    @PostMapping("/importar")
    public ResponseEntity<?> importarReceita(@RequestBody Receita receita) {
        try {
            Receita salva = receitaService.importaReceita(receita);
            return ResponseEntity.ok(salva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(502).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<Receita> criarReceita(@RequestBody Receita receita) {
        return ResponseEntity.ok(receitaService.salvar(receita));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Receita>> buscarPorTitulo(@RequestParam String titulo){
        return ResponseEntity.ok(receitaService.buscarPorTitulo(titulo));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Receita>buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(receitaService.BuscarPorId(id));
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletarPorId(@PathVariable Integer id){
        receitaService.deletarPorId(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizarReceita(
            @PathVariable Integer id,
            @RequestBody Receita receita) {
        Receita receitaAtualizada = receitaService.atualizarReceitaPorId(id, receita);
        return ResponseEntity.ok(receitaAtualizada);
    }


}





