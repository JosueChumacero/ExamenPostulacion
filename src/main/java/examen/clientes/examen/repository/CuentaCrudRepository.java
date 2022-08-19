/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package examen.clientes.examen.repository;

import examen.clientes.examen.entidad.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tony_
 */
public interface CuentaCrudRepository extends JpaRepository<Cuenta, String>{
       
}
