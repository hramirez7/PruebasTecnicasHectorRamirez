package com.movimientos.nttdatamovimientos.service.impl;

import com.movimientos.nttdatamovimientos.dto.CuentaRequest;
import com.movimientos.nttdatamovimientos.dto.CuentaResponse;
import com.movimientos.nttdatamovimientos.exception.ResourceNotFoundException;
import com.movimientos.nttdatamovimientos.mapper.CuentaMapper;
import com.movimientos.nttdatamovimientos.model.Cuenta;
import com.movimientos.nttdatamovimientos.model.EResponse;
import com.movimientos.nttdatamovimientos.repository.CuentaRepository;
import com.movimientos.nttdatamovimientos.service.ICuentaService;
import com.movimientos.nttdatamovimientos.service.IValidator;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements ICuentaService {

    private final CuentaRepository cuentaRepository;
    	private final IValidator<CuentaRequest> _iValidator;


    public CuentaServiceImpl(CuentaRepository cuentaRepository,IValidator<CuentaRequest> iValidator) {
        this.cuentaRepository = cuentaRepository;
        _iValidator = iValidator;
    }

    @Override
public EResponse<CuentaResponse> crearCuenta(CuentaRequest request) {
     
    var respuesta = new EResponse<CuentaResponse>(); 
    try {

        var result = _iValidator.validate(request);
		if (!result.isEmpty()) {
			return new EResponse<CuentaResponse>(new CuentaResponse(), result.get(0).getCode(), result.get(0).getMessage());
		}
               
        Cuenta cuenta = CuentaMapper.toEntity(request);
        // Guardamos la cuenta en el repositorio
        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);

        if (cuentaGuardada != null) {
            
         respuesta.setBody(CuentaMapper.toDto(cuentaGuardada));
          respuesta.setCode("000");
          respuesta.setError("ok");
        }else{         
          respuesta.setCode("500");
          respuesta.setError("error al guardar registro");
        }
         
        return respuesta;
    } catch (Exception e) {
        // Aquí lanzamos la excepción con el mensaje de error general
          respuesta.setCode("500");
          respuesta.setError("error al guardar registro" + e.getMessage());
        return respuesta;
    }
}


    @Override
public EResponse<CuentaResponse> actualizarCuenta(Long id, CuentaRequest request) {
    var respuesta = new EResponse<CuentaResponse>();
    try {
        Cuenta cuenta = cuentaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));

        cuenta.setNumeroCuenta(request.getNumeroCuenta());
        cuenta.setTipoCuenta(Cuenta.TipoCuenta.valueOf(request.getTipoCuenta().toUpperCase()));
        cuenta.setSaldoInicial(request.getSaldoInicial());
        cuenta.setEstado(request.getEstado());
        cuenta.setClienteId(request.getClienteId());

        Cuenta cuentaActualizada = cuentaRepository.save(cuenta);

        respuesta.setBody(CuentaMapper.toDto(cuentaActualizada));
        respuesta.setCode("000");
        respuesta.setError("ok");
        return respuesta;

    } catch (Exception e) {
        respuesta.setCode("500");
        respuesta.setError("error al actualizar la cuenta: " + e.getMessage());
        return respuesta;
    }
}

    
    @Override
    public void eliminarCuenta(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cuenta no encontrada con id: " + id);
        }
        cuentaRepository.deleteById(id);
    }

    @Override
    public CuentaResponse obtenerCuentaPorId(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));
        return CuentaMapper.toDto(cuenta);
    }

    @Override
    public List<CuentaResponse> listarCuentas() {
        return cuentaRepository.findAll()
                .stream()
                .map(CuentaMapper::toDto)
                .collect(Collectors.toList());
    }
}
