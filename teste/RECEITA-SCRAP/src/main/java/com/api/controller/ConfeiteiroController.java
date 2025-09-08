package com.api.controller;

import com.api.business.ConfeiteiroService;
import com.api.infrastructure.entitys.ConfeiteiroEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/confeiteiro")
@RequiredArgsConstructor
public class ConfeiteiroController {

    private final ConfeiteiroService confeiteiroService;

    @PostMapping
    public ResponseEntity<ConfeiteiroEntity> criar(@RequestBody ConfeiteiroEntity confeiteiro) {
        return ResponseEntity.ok(confeiteiroService.salvar(confeiteiro));
    }

    @GetMapping
    public ResponseEntity<List<ConfeiteiroEntity>> listarTodos() {
        return ResponseEntity.ok(confeiteiroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Integer id) {
        ConfeiteiroEntity confeiteiro = confeiteiroService.buscarPorId(id);
        return (confeiteiro.getId() == null)
                ? ResponseEntity.badRequest().body("ID faltando")
                : ResponseEntity.ok(confeiteiro);
    }

    @PostMapping("/importar")
    public ResponseEntity<ConfeiteiroEntity>importarConfeiteiro(@RequestBody ConfeiteiroEntity confeiteiro) throws IOException {
        return ResponseEntity.ok(confeiteiroService.importarOuCriarConfeiteiro(confeiteiro));
    }



}
