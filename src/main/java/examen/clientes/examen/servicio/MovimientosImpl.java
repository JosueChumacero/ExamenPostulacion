/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.servicio;

import examen.clientes.examen.dto.CuentaDto;
import examen.clientes.examen.dto.EstadoCuentaCliente;
import examen.clientes.examen.dto.MovimientosDto;
import examen.clientes.examen.dto.ReporteDto;
import examen.clientes.examen.entidad.Movimientos;
import examen.clientes.examen.mapper.EstadoCuentaClienteMapper;
import examen.clientes.examen.mapper.MovimientosMapper;
import examen.clientes.examen.repository.MovimientosRepository;
import examen.clientes.examen.utilitario.ExcepcionControlada;
import examen.clientes.examen.utilitario.HelperConstantes;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tony_
 */
@Service
public class MovimientosImpl implements IMovimientosServicio {

    @Autowired
    MovimientosRepository movimientosRepository;

    @Autowired
    ICuentaServicio iCuentaServicio;

    @Autowired
    MovimientosMapper movimientosMapper;

    @Autowired
    EstadoCuentaClienteMapper estadoCuentaClienteMapper;
    
    public static final Logger log = LoggerFactory.getLogger(MovimientosImpl.class);

    @Override
    public Optional<MovimientosDto> obtenerMovimiento(Integer movimientoId) {
        return movimientosRepository.findById(movimientoId).map(movimiento -> movimientosMapper.toMovimientoDto(movimiento));
    }

    @Override
    public MovimientosDto guardar(MovimientosDto movimientosDto) throws ExcepcionControlada {
        try {
            Optional<CuentaDto> cuenta = iCuentaServicio.obtenerCuenta(movimientosDto.getNumero());
            if (!cuenta.isPresent()) {
                throw new ExcepcionControlada("Cuenta no existe");
            }
            if (movimientosDto.getTipoMovimiento().equalsIgnoreCase(HelperConstantes.tipoMovimiento.CREDITO)) {
                if (movimientosDto.getMonto().compareTo(BigDecimal.ZERO) < 0) {
                    throw new ExcepcionControlada("Monto no correspode a tipo de movimiento");
                }
            } else if (movimientosDto.getTipoMovimiento().equalsIgnoreCase(HelperConstantes.tipoMovimiento.DEBITO)) {
                if (movimientosDto.getMonto().compareTo(BigDecimal.ZERO) > 0) {
                    throw new ExcepcionControlada("Monto no correspode a tipo de movimiento");
                }

                List<ReporteDto> reporte = movimientosPorTipo(movimientosDto.getTipoMovimiento(), movimientosDto.getFecha(), movimientosDto.getFecha());

                BigDecimal totalDebito = new BigDecimal(BigInteger.ZERO);
                for (ReporteDto reporteDto : reporte) {
                    totalDebito = totalDebito.add(reporteDto.getMonto());
                }
                totalDebito = totalDebito.multiply(new BigDecimal("-1"));
                if (totalDebito.compareTo(HelperConstantes.LIMITE_DIARIO_DEITO) > 0) {
                    throw new ExcepcionControlada("Cupo Diario Excedito");
                }
            } else {
                throw new ExcepcionControlada("Tipo de movimiento incorrecto");
            }
            movimientosDto.setSaldoInicial(cuenta.get().getSaldoInicial());
            cuenta.get().setSaldoInicial(cuenta.get().getSaldoInicial().add(movimientosDto.getMonto()));

            if (cuenta.get().getSaldoInicial().compareTo(BigDecimal.ZERO) < 0) {
                throw new ExcepcionControlada("Cuenta no cuenta con saldo disponible");
            }
            iCuentaServicio.guardar(cuenta.get());
            movimientosDto.setSaldoDisponible(cuenta.get().getSaldoInicial());
            Movimientos movimiento = movimientosMapper.toMovimientos(movimientosDto);
            return movimientosMapper.toMovimientoDto(movimientosRepository.save(movimiento));
        } catch (ExcepcionControlada e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage() + "-Causa-"+e.getCause());
            throw new ExcepcionControlada("Error al registrar movimiento, comuniquese con TI");
        }
    }

    @Override
    public boolean eliminar(Integer movimientoId) {
        return obtenerMovimiento(movimientoId).map(movimiento -> {
            movimientosRepository.deleteById(movimientoId);
            return true;
        }).orElse(false);
    }

    @Override
    public List<ReporteDto> movimientosPorTipo(String tipoMovimiento, LocalDate fechaInicio, LocalDate fechaFin) {
        return movimientosRepository.findByTipoMovimientoAndFechaBetween(tipoMovimiento, fechaInicio, fechaFin);
    }

    @Override
    public List<EstadoCuentaCliente> movimientosPorCliente(Integer ClienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        return movimientosRepository.findByCuentaClienteClienteIdAndFechaBetween(ClienteId, fechaInicio, fechaFin).
                stream().map(repotedto -> estadoCuentaClienteMapper.toEstadoCuentaCliente(repotedto)).collect(Collectors.toList());
    }

}
