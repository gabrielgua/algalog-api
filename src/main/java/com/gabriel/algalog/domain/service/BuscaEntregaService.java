package com.gabriel.algalog.domain.service;

import com.gabriel.algalog.api.exception.DomainException;
import com.gabriel.algalog.api.exception.ObjetoNaoEncontradoException;
import com.gabriel.algalog.domain.model.Entrega;
import com.gabriel.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BuscaEntregaService {

    private EntregaRepository repository;

    public Entrega buscarEntrega(Long entregaId) {
        return repository.findById(entregaId)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Entrega não encontrada"));
    }
}
