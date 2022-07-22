package com.gabriel.algalog.api.controller;

import com.gabriel.algalog.api.assembler.OcorrenciaAssembler;
import com.gabriel.algalog.api.model.OcorrenciaModel;
import com.gabriel.algalog.api.model.request.OcorrenciaRequest;
import com.gabriel.algalog.domain.model.Entrega;
import com.gabriel.algalog.domain.model.Ocorrencia;
import com.gabriel.algalog.domain.service.BuscaEntregaService;
import com.gabriel.algalog.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/entregas/{entregaId}/ocorrencias")
@RestController
@AllArgsConstructor
public class OcorrenciaController {

    private RegistroOcorrenciaService service;
    private OcorrenciaAssembler assembler;
    private BuscaEntregaService buscaEntregaService;

    @GetMapping
    public List<OcorrenciaModel> listarOcorrencias(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscarEntrega(entregaId);
        return assembler.toCollectionModel(entrega.getOcorrencias());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrarOcorrencia(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaRequest ocorrenciaRequest) {
        Ocorrencia ocorrencia = service.registrarOcorrencia(entregaId, ocorrenciaRequest.getDescricao());
        return assembler.toModel(ocorrencia);
    }

}
