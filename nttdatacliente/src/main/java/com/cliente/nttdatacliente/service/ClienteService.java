package com.cliente.nttdatacliente.service;


import com.cliente.nttdatacliente.dto.ClienteRequestDTO;
import com.cliente.nttdatacliente.dto.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    ClienteResponseDTO crear(ClienteRequestDTO dto);
    ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto);
    void eliminar(Long id);
    ClienteResponseDTO obtenerPorId(Long id);
    List<ClienteResponseDTO> listarTodos();
}
