/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.controlador;

import examen.clientes.examen.dto.ClienteDto;
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
import examen.clientes.examen.servicio.IClienteServicio;

/**
 *
 * @author tony_
 */
@RestController
@RequestMapping("/clientes")
public class ClienteControlador {
    
    @Autowired
    private IClienteServicio iClienteService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerCliente(@PathVariable("id") int clienteid) {
        
        return iClienteService.obtenerCliente(clienteid)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/guardar")
    public ResponseEntity<ClienteDto> guardar(@RequestBody ClienteDto cliente) {
        return new ResponseEntity<>(iClienteService.guardar(cliente), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable("id") int clienteid) {
        return iClienteService.eliminar(clienteid)?new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
