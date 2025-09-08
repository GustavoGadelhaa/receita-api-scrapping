package com.api.controller;

import com.api.business.DTO.ReceitaRequestDTO;
import com.api.business.ReceitaService;
import com.api.infrastructure.entitys.ReceitaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/receita")
@RestController
@RequiredArgsConstructor
public class ReceitaController {
    private final ReceitaService receitaService;


    @PostMapping("/importar")
    public ResponseEntity<?> importarReceita(@RequestBody ReceitaRequestDTO receita) {
        try {
            ReceitaEntity salva = receitaService.importaOuCriarReceita(receita);
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
    public ResponseEntity<ReceitaEntity> criarReceita(@RequestBody ReceitaEntity receita) {
        return ResponseEntity.ok(receitaService.salvar(receita));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReceitaEntity>> buscarPorTitulo(@RequestParam String titulo){
        return ResponseEntity.ok(receitaService.buscarPorTitulo(titulo));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReceitaEntity>buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(receitaService.BuscarPorId(id));
    }


   @GetMapping
   public ResponseEntity<List<ReceitaEntity>>listarReceitas(){
        return ResponseEntity.ok(receitaService.listarReceitas());

   }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletarPorId(@PathVariable Integer id){
        receitaService.deletarPorId(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaEntity> atualizarReceita(
            @PathVariable Integer id,
            @RequestBody ReceitaEntity receita) {
        ReceitaEntity receitaAtualizada = receitaService.atualizarReceitaPorId(id, receita);
        return ResponseEntity.ok(receitaAtualizada);
    }


}





