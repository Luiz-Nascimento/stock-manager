package com.projeto_engenharia.demo.controller;

import com.projeto_engenharia.demo.dto.*;
import com.projeto_engenharia.demo.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }
    @GetMapping
    public List<ProdutoResponse> listarTodos(@RequestParam(name = "filtro", required = false) String filtro) {
        return service.listarTodos(filtro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> acharPorId(@PathVariable Long id) {
        ProdutoResponse response = service.acharPorId(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody @Valid ProdutoRequest request) {
        ProdutoResponse response = service.criarProduto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoUpdate data) {
        ProdutoResponse response = service.atualizarProduto(id, data);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponse> editarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoPatchDTO data) {
        ProdutoResponse response = service.editarProduto(id, data);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Long id) {
        service.deletarProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }

}
