package com.gustavo_case.controller;

import com.gustavo_case.business.ConfeiteiroService;
import com.gustavo_case.infrastructure.entitys.Confeiteiro;
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
    public ResponseEntity<Confeiteiro> criar(@RequestBody Confeiteiro confeiteiro) {
        return ResponseEntity.ok(confeiteiroService.salvar(confeiteiro));
    }

    @GetMapping
    public ResponseEntity<List<Confeiteiro>> listarTodos() {
        return ResponseEntity.ok(confeiteiroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Integer id) {
        Confeiteiro confeiteiro = confeiteiroService.buscarPorId(id);
        return (confeiteiro.getId() == null)
                ? ResponseEntity.badRequest().body("ID faltando")
                : ResponseEntity.ok(confeiteiro);
    }

    @PostMapping("/importar")
    public ResponseEntity<Confeiteiro>importarConfeiteiro(@RequestBody Confeiteiro confeiteiro) throws IOException {
        return ResponseEntity.ok(confeiteiroService.importarConfeiteiro(confeiteiro));
    }



}
