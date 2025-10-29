package com.projeto_engenharia.demo.controller;

import com.projeto_engenharia.demo.dto.DashboardStatsResponse;
import com.projeto_engenharia.demo.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:5173") // Permite requisições do React
public class DashboardController {

    private final ProdutoService service;

    public DashboardController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsResponse> getStatistics() {
        DashboardStatsResponse stats = service.getDashboardStatistics();
        return ResponseEntity.ok(stats);
    }
}
