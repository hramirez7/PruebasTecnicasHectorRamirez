package com.movimientos.nttdatamovimientos.repository;

import com.movimientos.nttdatamovimientos.model.Movimiento;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime desde, LocalDateTime hasta);
}
