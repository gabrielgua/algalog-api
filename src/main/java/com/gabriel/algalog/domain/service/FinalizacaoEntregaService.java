package com.gabriel.algalog.domain.service;

import com.gabriel.algalog.api.exception.DomainException;
import com.gabriel.algalog.domain.model.Entrega;
import com.gabriel.algalog.domain.model.StatusEntrega;
import com.gabriel.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class FinalizacaoEntregaService {

    private BuscaEntregaService service;
    private EntregaRepository repository;

    @Transactional
    public void finalizarEntrega(Long entregaId) {
        Entrega entrega = service.buscarEntrega(entregaId);

        entrega.finalizar();
        repository.save(entrega);

    }

}
