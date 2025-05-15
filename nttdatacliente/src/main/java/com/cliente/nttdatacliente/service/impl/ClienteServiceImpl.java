package com.cliente.nttdatacliente.service.impl;


import  com.cliente.nttdatacliente.dto.ClienteRequestDTO;
import  com.cliente.nttdatacliente.dto.ClienteResponseDTO;
import  com.cliente.nttdatacliente.mapper.ClienteMapper;
import  com.cliente.nttdatacliente.model.Cliente;
import  com.cliente.nttdatacliente.repository.ClienteRepository;
import  com.cliente.nttdatacliente.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClienteResponseDTO crear(ClienteRequestDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        return ClienteMapper.toDTO(repository.save(cliente));
    }

    @Override
    public ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto) {
        Cliente existente = repository.findById(id).orElseThrow();
        existente.setNombre(dto.getNombre());
        // ... actualizar otros campos
        return ClienteMapper.toDTO(repository.save(existente));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ClienteResponseDTO obtenerPorId(Long id) {
        return ClienteMapper.toDTO(repository.findById(id).orElseThrow());
    }

    @Override
    public List<ClienteResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
