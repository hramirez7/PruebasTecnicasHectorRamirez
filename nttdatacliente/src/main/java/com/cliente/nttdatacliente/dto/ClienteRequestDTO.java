package com.cliente.nttdatacliente.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String contrasena;
    private Boolean estado;
}