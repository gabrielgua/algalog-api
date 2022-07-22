package com.gabriel.algalog.domain.service;

import com.gabriel.algalog.api.exception.DomainException;
import com.gabriel.algalog.domain.model.Cliente;
import com.gabriel.algalog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

        if (emailExistente) {
            throw new DomainException("Email já existente");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void remover(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new DomainException("Cliente não existente, id: #" + id));
    }



}
