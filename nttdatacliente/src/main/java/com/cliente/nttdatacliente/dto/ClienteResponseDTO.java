package com.cliente.nttdatacliente.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {
    private Long clienteId;
    private String nombre;
    private String identificacion;
    private Boolean estado;
    
}