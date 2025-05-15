package com.movimientos.nttdatamovimientos.repository;

import com.movimientos.nttdatamovimientos.model.Cuenta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);

    Optional<Cuenta> findByIdAndNumeroCuenta(Long id, String numeroCuenta);
}
