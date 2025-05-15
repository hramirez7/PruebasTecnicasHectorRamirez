package com.movimientos.nttdatamovimientos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
    private Double SaldoInicial;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

}
