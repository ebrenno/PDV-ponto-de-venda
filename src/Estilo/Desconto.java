/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estilo;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author brenno
 */
public class Desconto {

    public static double calcularDezPorCento(double total) {

        return total - (total * 10 / 100);
    }

    public static double calcularDescontoSobreQuantidade(double total, int percentual) {
        //formula: total - (total * percentual / 100);
        BigDecimal bigTotal = BigDecimal.valueOf(total);
        BigDecimal bigPercentual = BigDecimal.valueOf(percentual);
        bigPercentual = bigPercentual.divide(BigDecimal.valueOf(100));
        return bigTotal.subtract(bigTotal.multiply(bigPercentual)).setScale(2, RoundingMode.DOWN).doubleValue();

    }
}
