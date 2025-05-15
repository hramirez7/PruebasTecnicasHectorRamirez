package com.cliente.nttdatacliente.model;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Persona {

    private String nombre;
    private String genero;
    private Integer edad;

    @Column(unique = true)
    private String identificacion;
    
    private String direccion;
    private String telefono;
}
