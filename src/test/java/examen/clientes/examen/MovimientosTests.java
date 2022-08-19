/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen;

import examen.clientes.examen.dto.CuentaDto;
import examen.clientes.examen.dto.MovimientosDto;
import examen.clientes.examen.entidad.Cuenta;
import examen.clientes.examen.mapper.CuentaMapper;
import examen.clientes.examen.repository.MovimientosRepository;
import examen.clientes.examen.servicio.ICuentaServicio;
import examen.clientes.examen.servicio.IMovimientosServicio;
import examen.clientes.examen.utilitario.ExcepcionControlada;
import examen.clientes.examen.utilitario.HelperConstantes;
import java.math.BigDecimal;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

/**
 *
 * @author tony_
 */
@SpringBootTest
class MovimientosTests {

    @MockBean
    ICuentaServicio iCuentaServicio;
    @MockBean
    MovimientosRepository movimientosRepository;

    @Autowired
    IMovimientosServicio iMovimientosServicio;

    @Autowired
    CuentaMapper cuentaMapper;

    Throwable excepcion = null;
    
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Test
    @Description("Prueba de contexto")
    void test01() {
        Assertions.assertThat(movimientosRepository).isNotNull();
        Assertions.assertThat(iCuentaServicio).isNotNull();
    }

    @Test
    @Description("Valida Cuenta no existe")
    void test02() {
        MovimientosDto movimiento = new MovimientosDto();
        movimiento.setNumero("5821");
        try {
            ///when(iCuentaServicio.obtenerCuenta(movimiento.getNumero())).thenReturn(Optional.of(cuentaDto));
            Mockito.when(iCuentaServicio.obtenerCuenta(movimiento.getNumero())).thenReturn(Optional.empty());
            iMovimientosServicio.guardar(movimiento);
        } catch (Exception e) {
            excepcion = e;
        }
        assertNotNull(excepcion);
        assertSame(ExcepcionControlada.class, excepcion.getClass());
        assertEquals("Cuenta no existe", excepcion.getMessage());

    }

    @Test
    @Description("Monto no correspode a tipo de movimiento")
    void test03() {
        MovimientosDto movimiento = new MovimientosDto();
        movimiento.setNumero("5821");
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(movimiento.getNumero());
        movimiento.setCuenta(cuenta);
        movimiento.setTipoMovimiento(HelperConstantes.tipoMovimiento.CREDITO);
        movimiento.setMonto(new BigDecimal("-1"));
        Optional<CuentaDto> cuentaDto = Optional.of(cuentaMapper.toCuentaDto(cuenta));
        try {
            Mockito.when(iCuentaServicio.obtenerCuenta(movimiento.getNumero())).thenReturn(cuentaDto);
            iMovimientosServicio.guardar(movimiento);
        } catch (Exception e) {
            excepcion = e;
        }
        assertNotNull(excepcion);
        assertSame(ExcepcionControlada.class, excepcion.getClass());
        assertEquals("Monto no correspode a tipo de movimiento", excepcion.getMessage());

    }
    @Test
    @Description("Monto no correspode a tipo de movimiento")
    void test04() {
        MovimientosDto movimiento = new MovimientosDto();
        movimiento.setNumero("5821");
        Cuenta cuenta = new Cuenta();
        cuenta.setNumero(movimiento.getNumero());
        movimiento.setCuenta(cuenta);
        movimiento.setTipoMovimiento(HelperConstantes.tipoMovimiento.DEBITO);
        movimiento.setMonto(new BigDecimal("1"));
        Optional<CuentaDto> cuentaDto = Optional.of(cuentaMapper.toCuentaDto(cuenta));
        try {
            Mockito.when(iCuentaServicio.obtenerCuenta(movimiento.getNumero())).thenReturn(cuentaDto);
            iMovimientosServicio.guardar(movimiento);
        } catch (Exception e) {
            excepcion = e;
        }
        assertNotNull(excepcion);
        assertSame(ExcepcionControlada.class, excepcion.getClass());
        assertEquals("Monto no correspode a tipo de movimiento", excepcion.getMessage());

    }
}
