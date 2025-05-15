package com.movimientos.nttdatamovimientos.service.impl;

import com.movimientos.nttdatamovimientos.client.ClienteWebClientService;
import com.movimientos.nttdatamovimientos.dto.ClienteDTO;
import com.movimientos.nttdatamovimientos.dto.MovimientoDTO;
import com.movimientos.nttdatamovimientos.dto.ReporteEstadoCuentaDTO;
import com.movimientos.nttdatamovimientos.exception.ResourceNotFoundException;
import com.movimientos.nttdatamovimientos.model.Cuenta;
import com.movimientos.nttdatamovimientos.model.EResponse;
import com.movimientos.nttdatamovimientos.model.Movimiento;
import com.movimientos.nttdatamovimientos.repository.CuentaRepository;
import com.movimientos.nttdatamovimientos.repository.MovimientoRepository;
import com.movimientos.nttdatamovimientos.service.IMovimientoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final ClienteWebClientService clienteWebClientService;
 
    public MovimientoServiceImpl(CuentaRepository cuentaRepository,
     MovimientoRepository movimientoRepository,
     ClienteWebClientService clienteWebClientService) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
        this.clienteWebClientService = clienteWebClientService;
    }

    @Override
    @Transactional
public EResponse<Movimiento> registrarMovimiento(MovimientoDTO dto) {
    var respuesta = new EResponse<Movimiento>();
    try {
        Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        BigDecimal saldoInicial = BigDecimal.valueOf(cuenta.getSaldoInicial());
        BigDecimal saldoActual = saldoInicial;
        BigDecimal valor = BigDecimal.valueOf(dto.getValor());

        if ("retiro".equalsIgnoreCase(dto.getTipoMovimiento())) {
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new ResourceNotFoundException("El valor de retiro debe ser positivo");
            }
            if (saldoActual.compareTo(valor) < 0) {
                throw new ResourceNotFoundException("Saldo no disponible");
            }
            saldoActual = saldoActual.subtract(valor);
            valor = valor.negate(); // guardar como negativo
        } else if ("deposito".equalsIgnoreCase(dto.getTipoMovimiento())) {
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new ResourceNotFoundException("El valor del depósito debe ser positivo");
            }
            saldoActual = saldoActual.add(valor);
        } else {
            throw new RuntimeException("Tipo de movimiento inválido");
        }

        cuenta.setSaldoInicial(saldoActual.doubleValue()); // guarda como double si la entidad lo requiere
        cuentaRepository.save(cuenta);

        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(valor.doubleValue());
        movimiento.setSaldo(saldoActual.doubleValue());
        movimiento.setSaldoInicial(saldoInicial.doubleValue());
        movimiento.setCuenta(cuenta);

        Movimiento movim = movimientoRepository.save(movimiento);

        respuesta.setBody(movim);
        respuesta.setCode("000");
        respuesta.setError("ok");
        return respuesta;

    } catch (Exception e) {
        respuesta.setCode("500");
        respuesta.setError("error en el movimiento: " + e.getMessage());
        return respuesta;
    }
}

   /*  @Transactional
    public EResponse<Movimiento>  registrarMovimiento(MovimientoDTO dto) {
         var respuesta = new EResponse<Movimiento>();
         try{
            Cuenta cuenta = cuentaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        
        double saldoInicial =  cuenta.getSaldoInicial();
        Double saldoActual = cuenta.getSaldoInicial();
        Double valor = dto.getValor();
    
        if ("retiro".equalsIgnoreCase(dto.getTipoMovimiento())) {
            if (valor <= 0) {
                throw new ResourceNotFoundException("El valor de retiro debe ser positivo");
            }
            if (saldoActual < valor) {
                throw new ResourceNotFoundException("Saldo no disponible");
            }
            saldoActual -= valor;
            valor = -valor; // ✅ Registrar como negativo en el movimiento
        } else if ("deposito".equalsIgnoreCase(dto.getTipoMovimiento())) {
            if (valor <= 0) {
                throw new ResourceNotFoundException("El valor del depósito debe ser positivo");
            }
            saldoActual += valor;
        } else {
            throw new RuntimeException("Tipo de movimiento inválido");
        }
    
        cuenta.setSaldoInicial(saldoActual);
        cuentaRepository.save(cuenta);
    
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(valor); // ✅ Guardar con signo correcto
        movimiento.setSaldo(saldoActual);
        movimiento.setSaldoInicial(saldoInicial);
        movimiento.setCuenta(cuenta);

        Movimiento movim = movimientoRepository.save(movimiento);
    
        respuesta.setBody(movim);
        respuesta.setCode("000");
        respuesta.setError("ok");
        return respuesta;
        //return movimientoRepository.save(movimiento);

        } catch (Exception e) {
        respuesta.setCode("500");
        respuesta.setError("error en el movimiento: " + e.getMessage());
        return respuesta;
    }
        
    }*/

    @Override
    public List<ReporteEstadoCuentaDTO> generarReporte(LocalDate desde, LocalDate hasta) {
        List<Cuenta> cuentas = cuentaRepository.findAll();
    
        return cuentas.stream()
                .flatMap(cuenta -> {
                    Long cuentaId = cuenta.getId();
    
                    List<Movimiento> movimientos = movimientoRepository
                            .findByCuentaIdAndFechaBetween(
                                    cuentaId,
                                    desde.atStartOfDay(),
                                    hasta.atTime(23, 59, 59)
                            );
    
                    if (movimientos.isEmpty()) return Stream.empty();
    
                    ClienteDTO clienteDTO = clienteWebClientService
                            .obtenerClientePorId(Long.parseLong(cuenta.getClienteId()))
                            .block(); // bloquear por simplicidad
    
                    String nombreCliente = clienteDTO != null ? clienteDTO.getNombre() : "Desconocido";
    
                    return movimientos.stream().map(mov -> {
                        ReporteEstadoCuentaDTO dto = new ReporteEstadoCuentaDTO();
                        dto.setFecha(mov.getFecha().toLocalDate());
                        dto.setCliente(nombreCliente);
                        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
                        dto.setTipo(cuenta.getTipoCuenta().name());
                        dto.setSaldoInicial(mov.getSaldoInicial());
                        dto.setEstado(cuenta.getEstado());
                        dto.setMovimiento(mov.getValor());
                        dto.setSaldoDisponible(mov.getSaldo());
                        return dto;
                    });
                })
                .collect(Collectors.toList());
    }    
}
