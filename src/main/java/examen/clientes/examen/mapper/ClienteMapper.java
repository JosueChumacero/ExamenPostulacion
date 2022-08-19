/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.mapper;

import examen.clientes.examen.dto.ClienteDto;
import examen.clientes.examen.entidad.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author tony_
 */
@Mapper(componentModel = "spring")
public interface ClienteMapper {
    
    @Mappings({
        @Mapping(source = "clienteId", target = "clienteId"),
        @Mapping(source = "estado", target = "estado"),
        @Mapping(source = "contraseña", target = "contraseña")
    })
    ClienteDto toClienteDto(Cliente cliente);
    
    @InheritInverseConfiguration
    @Mapping(target = "cuentaList", ignore = true)
    Cliente toCliente(ClienteDto clienteDto);
    
}
