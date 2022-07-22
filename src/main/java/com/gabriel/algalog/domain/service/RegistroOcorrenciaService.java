package com.gabriel.algalog.domain.service;

import com.gabriel.algalog.api.exception.DomainException;
import com.gabriel.algalog.domain.model.Entrega;
import com.gabriel.algalog.domain.model.Ocorrencia;
import com.gabriel.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrarOcorrencia(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscarEntrega(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }

}
