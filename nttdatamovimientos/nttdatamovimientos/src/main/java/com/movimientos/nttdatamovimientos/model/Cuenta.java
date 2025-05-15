package com.movimientos.nttdatamovimientos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroCuenta;

    @Enumerated(EnumType.STRING) // Guarda "AHORRO" o "CORRIENTE" en la DB
    private TipoCuenta tipoCuenta;

    private Double saldoInicial;
    private Boolean estado;

    private String clienteId; // Se recibe del otro microservicio

    
    public enum TipoCuenta {
        AHORROS,
        CORRIENTE,
        // otros valores...
    }
}
