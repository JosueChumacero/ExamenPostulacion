/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen.clientes.examen.repository;

import examen.clientes.examen.dto.ReporteDto;
import examen.clientes.examen.entidad.Movimientos;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tony_
 */
public interface MovimientosRepository extends JpaRepository<Movimientos, Object> {

    List<ReporteDto> findByTipoMovimientoAndFechaBetween(String tipoMovimiento, LocalDate fechaInicio, LocalDate fechaFin);
    
    List<ReporteDto> findByCuentaClienteClienteIdAndFechaBetween(Integer clienteId, LocalDate fechaInicio, LocalDate fechaFin);

}
