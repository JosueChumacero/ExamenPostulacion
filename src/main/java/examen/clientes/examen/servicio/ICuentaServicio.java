/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.servicio;

import examen.clientes.examen.dto.CuentaDto;
import java.util.Optional;

/**
 *
 * @author tony_
 */
public interface ICuentaServicio {
    
    public Optional<CuentaDto> obtenerCuenta(String numero);
    
    public CuentaDto guardar(CuentaDto cuentaDto);
    
    public boolean eliminar(String numero);
    
}
