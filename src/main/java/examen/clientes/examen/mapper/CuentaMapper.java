/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.mapper;

import examen.clientes.examen.dto.CuentaDto;
import examen.clientes.examen.entidad.Cuenta;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author tony_
 */
@Mapper(componentModel = "spring")
public interface CuentaMapper {

    @Mappings({
        @Mapping(source = "numero", target = "numero"),
        @Mapping(source = "tipo", target = "tipo"),
        @Mapping(source = "saldoInicial", target = "saldoInicial"),
        @Mapping(source = "estado", target = "estado"),
        @Mapping(source = "cliente", target = "cliente"),
        @Mapping(source = "clienteId", target = "clienteId"),})
    CuentaDto toCuentaDto(Cuenta cuenta);

    @InheritInverseConfiguration
    @Mapping(target = "movimientosList", ignore = true)
    Cuenta toCuenta(CuentaDto cuentaDto);

}
