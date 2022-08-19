/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.controlador;

import examen.clientes.examen.dto.MovimientosDto;
import examen.clientes.examen.servicio.IMovimientosServicio;
import examen.clientes.examen.utilitario.ExcepcionControlada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tony_
 */
@RestController
@RequestMapping("/movimientos")
public class MovimientoControlador {

    @Autowired
    private IMovimientosServicio iMovimientosServicio;

    @GetMapping("/{id}")
    public ResponseEntity<MovimientosDto> obtenerMovimiento(@PathVariable("id") Integer movimientoId) {

        return iMovimientosServicio.obtenerMovimiento(movimientoId)
                .map(movimiento -> new ResponseEntity<>(movimiento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/guardar")
    public ResponseEntity guardar(@RequestBody MovimientosDto movimientosDto) {
        try {
            return new ResponseEntity<>(iMovimientosServicio.guardar(movimientosDto), HttpStatus.CREATED);
        } catch (ExcepcionControlada e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            return new ResponseEntity<>("Error al registrar movimiento, comuniquese con TI", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable("id") Integer movimientoId) {
        return iMovimientosServicio.eliminar(movimientoId)?new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
