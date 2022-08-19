/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.servicio;

import examen.clientes.examen.dto.CuentaDto;
import examen.clientes.examen.entidad.Cuenta;
import examen.clientes.examen.mapper.CuentaMapper;
import examen.clientes.examen.repository.CuentaCrudRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tony_
 */
@Service
public class CuentaServicioImpl implements ICuentaServicio{
    
    @Autowired
    CuentaCrudRepository cuentaCrudRepository;
    
    @Autowired
    CuentaMapper cuentaMapper;

    @Override
    public Optional<CuentaDto> obtenerCuenta(String numero) {
        return cuentaCrudRepository.findById(numero).map(cliente -> cuentaMapper.toCuentaDto(cliente));
    }

    @Override
    public CuentaDto guardar(CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaMapper.toCuenta(cuentaDto);
        return cuentaMapper.toCuentaDto(cuentaCrudRepository.save(cuenta));
    }

    @Override
    public boolean eliminar(String numero) {
        return obtenerCuenta(numero).map(cuenta -> {
            cuentaCrudRepository.deleteById(numero);
            return true;
        }).orElse(false);
    }
    
}
