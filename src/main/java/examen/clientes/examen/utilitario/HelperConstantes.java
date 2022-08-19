/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen.clientes.examen.utilitario;

import java.math.BigDecimal;

/**
 *
 * @author tony_
 */
public class HelperConstantes {

    public final static BigDecimal LIMITE_DIARIO_DEITO = new BigDecimal("1000");

    public static abstract class tipoMovimiento {

        public final static String DEBITO = "DEBITO";
        public final static String CREDITO = "CREDITO";
    }

}
