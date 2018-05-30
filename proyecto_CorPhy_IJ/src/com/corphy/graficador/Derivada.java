package com.corphy.graficador;

import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

/**
 * <p>Clase que permite calcular la derivada de una función ingresada por el usuario.</p>
 *
 * @author CorPhy
 * @version 1.0
 */

class Derivada {
    /**
     * Calcula la derivada de una funci&oacute;n.
     *
     * @param funcion Funci&oacute;n digitada por el usuario.
     * @return Derivada de la funci&oacute;n ingresada.
     */
    String derivar(String funcion) {
        String derivada = "";
        DJep Derivar = new DJep();
        Derivar.addStandardFunctions();
        Derivar.addStandardConstants();
        Derivar.addComplex();
        Derivar.setAllowAssignment(true);
        Derivar.setAllowUndeclared(true);
        Derivar.setImplicitMul(true);
        Derivar.addStandardDiffRules();

        try {
            Node node = Derivar.parse(funcion);
            Node diff = Derivar.differentiate(node, "x");
            Node sim = Derivar.simplify(diff);
            derivada = Derivar.toString(sim);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return derivada;
    }
}
