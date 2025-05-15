package com.cliente.nttdatacliente.mapper;

import com.cliente.nttdatacliente.dto.ClienteRequestDTO;
import com.cliente.nttdatacliente.dto.ClienteResponseDTO;
import com.cliente.nttdatacliente.model.Cliente;

public class ClienteMapper {

    public static Cliente toEntity(ClienteRequestDTO dto) {
        Cliente c = new Cliente();
        c.setNombre(dto.getNombre());
        c.setGenero(dto.getGenero());
        c.setEdad(dto.getEdad());
        c.setIdentificacion(dto.getIdentificacion());
        c.setDireccion(dto.getDireccion());
        c.setTelefono(dto.getTelefono());
        c.setContrasena(dto.getContrasena());
        c.setEstado(dto.getEstado());
        return c;
    }

    public static ClienteResponseDTO toDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setClienteId(cliente.getClienteId());
        dto.setNombre(cliente.getNombre());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setEstado(cliente.getEstado());
        return dto;
    }
}
