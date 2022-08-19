/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen.clientes.examen.mapper;

import examen.clientes.examen.dto.MovimientosDto;
import examen.clientes.examen.entidad.Movimientos;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author tony_
 */
@Mapper(componentModel = "spring")
public interface MovimientosMapper {

    @Mappings({
        @Mapping(source = "movimientoId", target = "movimientoId"),
        @Mapping(source = "tipoMovimiento", target = "tipoMovimiento"),
        @Mapping(source = "monto", target = "monto"),
        @Mapping(source = "estado", target = "estado"),
        @Mapping(source = "numero", target = "numero"),
        @Mapping(source = "cuenta", target = "cuenta"),
        @Mapping(source = "saldoInicial", target = "saldoInicial"),
        @Mapping(source = "fecha", target = "fecha"),
    @Mapping(source = "saldoDisponible", target = "saldoDisponible"),})
    MovimientosDto toMovimientoDto(Movimientos movimientos);

    @InheritInverseConfiguration
    Movimientos toMovimientos(MovimientosDto movimientosDto);

}
