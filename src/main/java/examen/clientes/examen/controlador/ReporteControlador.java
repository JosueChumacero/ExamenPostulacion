/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.controlador;

import examen.clientes.examen.dto.EstadoCuentaCliente;
import examen.clientes.examen.servicio.IMovimientosServicio;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tony_
 */
@RestController
@RequestMapping("/reportes")
public class ReporteControlador {

    @Autowired
    private IMovimientosServicio iMovimientosServicio;

    @GetMapping("/estadoCuenta")
    public ResponseEntity<List<EstadoCuentaCliente>> obtieneEstadoCuenta(@RequestParam(value = "ClienteId") Integer ClienteId,
            @RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin) {
        List<EstadoCuentaCliente> estadoCuentaCliente = iMovimientosServicio.movimientosPorCliente(ClienteId, LocalDate.parse(fechaInicio), LocalDate.parse(fechaFin));
        
        return !estadoCuentaCliente.isEmpty()? new ResponseEntity<>(estadoCuentaCliente, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
