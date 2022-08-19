/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen.clientes.examen.servicio;

import examen.clientes.examen.dto.ClienteDto;
import java.util.Optional;

/**
 *
 * @author tony_
 */
public interface IClienteServicio {
    
    public Optional<ClienteDto> obtenerCliente(Integer clienteId);
    
    public ClienteDto guardar(ClienteDto cliente);
    
    public boolean eliminar(Integer clienteId);
    
}
