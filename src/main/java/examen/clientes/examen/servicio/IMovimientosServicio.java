/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen.clientes.examen.servicio;

import examen.clientes.examen.dto.EstadoCuentaCliente;
import examen.clientes.examen.dto.MovimientosDto;
import examen.clientes.examen.dto.ReporteDto;
import examen.clientes.examen.utilitario.ExcepcionControlada;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author tony_
 */
public interface IMovimientosServicio {

    public Optional<MovimientosDto> obtenerMovimiento(Integer movimientoId);

    public MovimientosDto guardar(MovimientosDto movimientosDto) throws ExcepcionControlada;

    public boolean eliminar(Integer movimientoId);

    public List<ReporteDto> movimientosPorTipo(String tipoMovimiento, LocalDate fechaInicio, LocalDate fechaFin);

    public List<EstadoCuentaCliente> movimientosPorCliente(Integer ClienteId, LocalDate fechaInicio, LocalDate fechaFin);
}
