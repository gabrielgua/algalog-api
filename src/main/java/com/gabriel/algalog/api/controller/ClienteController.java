package com.gabriel.algalog.api.controller;

import com.gabriel.algalog.api.assembler.ClienteAssembler;
import com.gabriel.algalog.api.model.ClienteModel;
import com.gabriel.algalog.api.model.request.ClienteRequest;
import com.gabriel.algalog.domain.model.Cliente;
import com.gabriel.algalog.domain.repository.ClienteRepository;
import com.gabriel.algalog.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("clientes")
public class ClienteController {

    private ClienteRepository repository;

    private CatalogoClienteService service;
    private ClienteAssembler assembler;

    @GetMapping
    public List<ClienteModel> listar() {
        return assembler.toCollectionModel(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(cliente -> ResponseEntity.ok(assembler.toModel(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel adicionar(@Valid @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = assembler.toEntity(clienteRequest);
        Cliente novoCliente = service.salvar(cliente);
        return assembler.toModel(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest clienteRequest) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = assembler.toEntity(clienteRequest);
        cliente.setId(id);
        cliente = service.salvar(cliente);
        return ResponseEntity.ok(assembler.toModel(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.remover(id);
        return ResponseEntity.noContent().build();
    }


}
