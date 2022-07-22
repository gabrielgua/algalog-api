package com.gabriel.algalog.api.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DestinatarioRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

}
