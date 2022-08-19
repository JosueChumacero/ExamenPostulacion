/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen.clientes.examen.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author tony_
 */
public interface ReporteDto {

    LocalDate getFecha();

    CuentaDto getCuenta();
    
    BigDecimal getSaldoInicial();
    
    String getEstado();
    
    BigDecimal getMonto();
    
    BigDecimal getSaldoDisponible();

    interface CuentaDto {

        ClienteDto getCliente();

        String getNumero();

        String getTipo();

        interface ClienteDto {

            String getNombre();
        }
    }

}
