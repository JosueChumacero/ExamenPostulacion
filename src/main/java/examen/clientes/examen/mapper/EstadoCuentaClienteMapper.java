/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen.clientes.examen.mapper;

import examen.clientes.examen.dto.EstadoCuentaCliente;
import examen.clientes.examen.dto.ReporteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author tony_
 */
@Mapper(componentModel = "spring")
public interface EstadoCuentaClienteMapper {

    @Mappings({
        @Mapping(source = "fecha", target = "fecha"),
        @Mapping(source = "cuenta.cliente.nombre", target = "nombre"),
        @Mapping(source = "cuenta.numero", target = "cuenta"),
        @Mapping(source = "cuenta.tipo", target = "tipo"),
        @Mapping(source = "saldoInicial", target = "saldoInicial"),
        @Mapping(source = "estado", target = "estado"),
        @Mapping(source = "monto", target = "montoMovimiento"),
        @Mapping(source = "saldoDisponible", target = "saldoDisponible"),})
    EstadoCuentaCliente toEstadoCuentaCliente(ReporteDto cuenta);
}
