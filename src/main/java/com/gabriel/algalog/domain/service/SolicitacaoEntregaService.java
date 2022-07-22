package com.gabriel.algalog.domain.service;

import com.gabriel.algalog.domain.model.Cliente;
import com.gabriel.algalog.domain.model.Entrega;
import com.gabriel.algalog.domain.model.StatusEntrega;
import com.gabriel.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class SolicitacaoEntregaService {

    private EntregaRepository repository;
    private CatalogoClienteService clienteService;
    @Transactional
    public Entrega solicitarEntrega(Entrega entrega) {
        Cliente cliente = clienteService.buscarPorId(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return repository.save(entrega);
    }


}
