package com.gabriel.algalog.api.controller;

import com.gabriel.algalog.api.assembler.EntregaAssembler;
import com.gabriel.algalog.api.model.EntregaModel;
import com.gabriel.algalog.api.model.request.EntregaRequest;
import com.gabriel.algalog.domain.model.Entrega;
import com.gabriel.algalog.domain.repository.EntregaRepository;
import com.gabriel.algalog.domain.service.FinalizacaoEntregaService;
import com.gabriel.algalog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("entregas")
public class EntregaController {

    private SolicitacaoEntregaService service;
    private EntregaRepository repository;
    private EntregaAssembler assembler;
    private FinalizacaoEntregaService finalizacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitarEntrega(@Valid @RequestBody EntregaRequest entregaRequest) {
        Entrega novaEntrega = assembler.toEntity(entregaRequest);
        Entrega entregaSolicitada = service.solicitarEntrega(novaEntrega);
        return assembler.toModel(entregaSolicitada);
    }

    @GetMapping
    public List<EntregaModel> listarEntregas() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> buscarEntregaPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(entrega -> ResponseEntity.ok(assembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizarEntrega(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizarEntrega(entregaId);
    }

}
