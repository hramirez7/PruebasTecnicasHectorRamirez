package com.movimientos.nttdatamovimientos.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long clienteId;
    private String nombre;
    private String identificacion;
    private Boolean estado;

}
