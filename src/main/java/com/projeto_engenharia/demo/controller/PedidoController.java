package com.projeto_engenharia.demo.controller;

import com.projeto_engenharia.demo.dto.ItemPedidoRequest;
import com.projeto_engenharia.demo.dto.ItemPedidoResponse;
import com.projeto_engenharia.demo.dto.PedidoRequest;
import com.projeto_engenharia.demo.dto.PedidoResponse;
import com.projeto_engenharia.demo.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoResponse> listarTodos() {
        return pedidoService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> findById(@PathVariable Long id) {
        PedidoResponse response = pedidoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<PedidoResponse> criar(@RequestBody @Valid PedidoRequest request) {
        PedidoResponse response = pedidoService.realizarVenda(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
