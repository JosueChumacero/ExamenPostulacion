/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.servicio;

import examen.clientes.examen.dto.ClienteDto;
import examen.clientes.examen.entidad.Cliente;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import examen.clientes.examen.repository.ClienteCrudRepository;
import examen.clientes.examen.mapper.ClienteMapper;

/**
 *
 * @author tony_
 */
@Service
public class ClienteServicioImpl implements IClienteServicio{
    
    @Autowired
    private ClienteCrudRepository clienteCrudRepository;
    
    @Autowired
    private ClienteMapper clienteMaper;
    
    @Override
    public Optional<ClienteDto> obtenerCliente(Integer clienteId) {
        return clienteCrudRepository.findById(clienteId).map(cliente -> clienteMaper.toClienteDto(cliente));
    }

    @Override
    public ClienteDto guardar(ClienteDto clienteDto) {
        Cliente cliente = clienteMaper.toCliente(clienteDto);
        return clienteMaper.toClienteDto(clienteCrudRepository.save(cliente));
    }

    @Override
    public boolean eliminar(Integer clienteId) {
        return obtenerCliente(clienteId).map(cliente -> {
            clienteCrudRepository.deleteById(clienteId);
            return true;
        }).orElse(false);
    }
}
