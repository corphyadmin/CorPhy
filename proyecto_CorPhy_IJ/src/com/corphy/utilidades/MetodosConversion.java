package com.corphy.utilidades;

import com.corphy.coordenadas.CCartesiana;
import com.corphy.coordenadas.CCilindrica;
import com.corphy.coordenadas.CEsferica;
import com.corphy.coordenadas.CPolar;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>Clase que implementa los m&eacute;todos de conversi&oacute;n de las clases que representan los sistemas de
 * coordenadas (Coordenadas Cartesianas, Cil&iacute;ndricas, Esf&eacute;ricas y Polares).</p>
 * <p>Esta clase permite:</p>
 * <ul>
 * <li>Identificar la pareja de coordenadas seleccionada por el usuario.</li>
 * <li>Realizar las diferentes conversiones entre las coordenadas.</li>
 * </ul>
 *
 * @author CorPhy
 * @version 1.0
 */

public class MetodosConversion {
    // Atributos

    /**
     * Clase {@link Utilidades} que contiene los métodos generales de la aplicación.
     */
    private Utilidades general;

    // Constructor

    /**
     * Constructor de la clase que inicializa el objeto de la clase {@link Utilidades}.
     */
    public MetodosConversion() {
        general = new Utilidades();
    }

    /**
     * Identifica la selecci&oacute;n de las coordenadas por parte del usuario. Estas est&aacute;n dadas en parejas.
     *
     * @param coorNombrePr Nombre de la coordenada seleccionada en el comboBox principal.
     * @param coorNombreSc Nombre de la coordenada seleccionada en el comboBox secundario.
     * @return N&uacute;mero de la pareja de coordenadas seleccionada por el usuario.
     */
    public byte validarConversion(String coorNombrePr, String coorNombreSc) {
        ObservableList<String> nameCoor = general.getListCoorNombrePr();
        byte opcion = 0;
        if (coorNombrePr.equals(nameCoor.get(0)) && coorNombreSc.equals(nameCoor.get(4))) {
            opcion = 0;
        } else if (coorNombrePr.equals(nameCoor.get(1)) && coorNombreSc.equals(nameCoor.get(2))) {
            opcion = 1;
        } else if (coorNombrePr.equals(nameCoor.get(1)) && coorNombreSc.equals(nameCoor.get(3))) {
            opcion = 2;
        } else if (coorNombrePr.equals(nameCoor.get(2)) && coorNombreSc.equals(nameCoor.get(1))) {
            opcion = 3;
        } else if (coorNombrePr.equals(nameCoor.get(2)) && coorNombreSc.equals(nameCoor.get(3))) {
            opcion = 4;
        } else if (coorNombrePr.equals(nameCoor.get(3)) && coorNombreSc.equals(nameCoor.get(1))) {
            opcion = 5;
        } else if (coorNombrePr.equals(nameCoor.get(3)) && coorNombreSc.equals(nameCoor.get(2))) {
            opcion = 6;
        } else if (coorNombrePr.equals(nameCoor.get(4)) && coorNombreSc.equals(nameCoor.get(0))) {
            opcion = 7;
        }
        return opcion;
    }

    /**
     * Recibe las componentes de la coordenada Cartesiana 2D para obtener la nueva coordenada Polar con sus componentes
     * en el cual sus decimales se pueden aproximar din&aacute;micamente.
     *
     * @param x          Valor de la coordenada en el eje X.
     * @param y          Valor de la coordenada en el eje Y.
     * @param nDecimales N&uacute;mero de decimales que tendr&aacute;n cada uno de los valores de la coordenada resultante.
     * @return Valores con los que se expresa la coordenada Polar.
     */
    public double[] calcularCoorCar2DPo(double x, double y, boolean esRadianes, int nDecimales) {
        double[] coor = new double[2];
        if (x == 0 && y == 0) {
            coor[0] = 0;
            coor[1] = 0;
        } else {
            CPolar cp = CCartesiana.convertirAPolar(x, y, esRadianes);
            coor[0] = BigDecimal.valueOf(cp.getRadio()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
            coor[1] = BigDecimal.valueOf(cp.getAngulo()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        }
        return coor;
    }

    /**
     * Recibe las componentes de la coordenada Cartesiana 3D para obtener la nueva coordenada Cil&iacute;ndrica con sus
     * componentes en el cual sus decimales se pueden aproximar din&aacute;micamente.
     *
     * @param x          Valor de la coordenada en el eje X.
     * @param y          Valor de la coordenada en el eje Y.
     * @param z          Valor de la coordenada en el eje Z.
     * @param nDecimales N&uacute;mero de decimales que tendr&aacute;n cada uno de los valores de la coordenada resultante.
     * @return Valores con los que se expresa la coordenada Cil&iacute;ndrica.
     */
    public double[] calcularCoorCar3DCil(double x, double y, double z, boolean esRadianes, int nDecimales) {
        double[] coor = new double[3];
        if (x == 0 && y == 0 && z == 0) {
            coor[0] = 0;
            coor[1] = 0;
            coor[2] = 0;
        } else {
            CCilindrica ccil = CCartesiana.convertirACilindrica(x, y, z, esRadianes);
            coor[0] = BigDecimal.valueOf(ccil.getRadio()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
            coor[1] = BigDecimal.valueOf(ccil.getAngulo()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
            coor[2] = BigDecimal.valueOf(ccil.getZ()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        }
        return coor;
    }

    /**
     * Recibe las componentes de la coordenada Cartesiana 3D para obtener la nueva coordenada Esf&eacute;rica con sus
     * componentes en el cual sus decimales se pueden aproximar din&aacute;micamente.
     *
     * @param x          Valor de la coordenada en el eje X.
     * @param y          Valor de la coordenada en el eje Y.
     * @param z          Valor de la coordenada en el eje Z.
     * @param nDecimales N&uacute;mero de decimales que tendr&aacute;n cada uno de los valores de la coordenada resultante.
     * @return Valores con los que se expresa la coordenada Esf&eacute;rica.
     */
    public double[] calcularCoorCar3DEsf(double x, double y, double z, boolean esRadianes, int nDecimales) {
        double[] coor = new double[3];
        if (x == 0 && y == 0 && z == 0) {
            coor[0] = 0;
            coor[1] = 0;
            coor[2] = 0;
        } else {
            CEsferica ce = CCartesiana.convertirAEsferica(x, y, z, esRadianes);
            coor[0] = BigDecimal.valueOf(ce.getRadio()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
            coor[1] = BigDecimal.valueOf(ce.getAnguloPhi()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
            coor[2] = BigDecimal.valueOf(ce.getAnguloTheta()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        }
        return coor;
    }

    /**
     * Recibe las componentes de la coordenada Cil&iacute;ndrica para obtener la nueva coordenada Cartesiana 3D con sus
     * componentes en el cual sus decimales se pueden aproximar din&aacute;micamente.
     *
     * @param radio      Valor de la coordenada que representa el radio.
     * @param theta      Valor de la coordenada que representa el &aacute;ngulo &theta; (Theta).
     * @param z          Valor de la coordenada en el eje Z.
     * @param nDecimales N&uacute;mero de decimales que tendr&aacute;n cada uno de los valores de la coordenada resultante.
     * @return Valores con los que se expresa la coordenada Cartesiana 3D.
     */
    public double[] calcularCoorCilCar3D(double radio, double theta, double z, boolean esRadian, int nDecimales) {
        double[] coor = new double[3];
        CCartesiana cc = CCilindrica.convertirACartesiana(radio, theta, z, esRadian);
        coor[0] = BigDecimal.valueOf(cc.getX()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        coor[1] = BigDecimal.valueOf(cc.getY()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        coor[2] = BigDecimal.valueOf(cc.getZ()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        return coor;
    }

    /**
     * Recibe las componentes de la coordenada Cil&iacute;ndrica para obtener la nueva coordenada Esf&eacute;rica con sus
     * componentes en el cual sus decimales se pueden aproximar din&aacute;micamente.
     *
     * @param radio      Valor de la coordenada que representa el radio.
     * @param theta      Valor de la coordenada que representa el &aacute;ngulo &theta; (Theta).
     * @param z          Valor de la coordenada en el eje Z.
     * @param nDecimales N&uacute;mero de decimales que tendr&aacute;n cada uno de los valores de la coordenada resultante.
     * @return Valores con los que se expresa la coordenada Esf&eacute;rica.
     */
    public double[] calcularCoorCilEsf(double radio, double theta, double z, boolean esRadian, int nDecimales) {
        double[] coor = new double[3];
        if (radio == 0 && theta == 0 && z == 0) {
            coor[0] = 0;
            coor[1] = 0;
            coor[2] = 0;
        } else {
            CEsferica ce = CCilindrica.convertirAEsferica(radio, theta, z, esRadian);
            coor[0] = BigDecimal.valueOf(ce.getRadio()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
            coor[1] = BigDecimal.valueOf(ce.getAnguloTheta()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
            coor[2] = BigDecimal.valueOf(ce.getAnguloPhi()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        }
        return coor;
    }

    /**
     * Recibe las componentes de la coordenada Esf&eacute;rica para obtener la nueva coordenada Cartesiana 3D con sus
     * componentes en el cual sus decimales se pueden aproximar din&aacute;micamente.
     *
     * @param radio      Valor de la coordenada que representa el radio.
     * @param theta      Valor de la coordenada que representa el &aacute;ngulo &theta; (Theta).
     * @param phi        Valor de la coordenada que representa el &aacute;ngulo &phi; (Phi).
     * @param nDecimales N&uacute;mero de decimales que tendr&aacute;n cada uno de los valores de la coordenada resultante.
     * @return Valores con los que se expresa la coordenada Cartesiana 3D.
     */
    public double[] calcularCoorEsfCar3D(double radio, double theta, double phi, boolean esRadian, int nDecimales) {
        double[] coor = new double[3];
        CCartesiana cc = CEsferica.convertirACartesiana(radio, theta, phi, esRadian);
        coor[0] = BigDecimal.valueOf(cc.getX()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        coor[1] = BigDecimal.valueOf(cc.getY()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        coor[2] = BigDecimal.valueOf(cc.getZ()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        return coor;
    }

    /**
     * Recibe las componentes de la coordenada Esf&eacute;rica para obtener la nueva coordenada Cil&iacute;ndrica con sus
     * componentes en el cual sus decimales se pueden aproximar din&aacute;micamente.
     *
     * @param radio      Valor de la coordenada que representa el radio.
     * @param theta      Valor de la coordenada que representa el &aacute;ngulo &theta; (Theta).
     * @param phi        Valor de la coordenada que representa el &aacute;ngulo &phi; (Phi).
     * @param nDecimales N&uacute;mero de decimales que tendr&aacute;n cada uno de los valores de la coordenada resultante.
     * @return Valores con los que se expresa la coordenada Cil&iacute;ndrica.
     */
    public double[] calcularCoorEsfCil(double radio, double theta, double phi, boolean esRadian, int nDecimales) {
        double[] coor = new double[3];
        CCilindrica ccil = CEsferica.convertirACilindrica(radio, theta, phi, esRadian);
        coor[0] = BigDecimal.valueOf(ccil.getRadio()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        coor[1] = BigDecimal.valueOf(ccil.getAngulo()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        coor[2] = BigDecimal.valueOf(ccil.getZ()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        return coor;
    }

    /**
     * Recibe las componentes de la coordenada Polar para obtener la nueva coordenada Cartesiana 2D con sus
     * componentes en el cual sus decimales se pueden aproximar din&aacute;micamente.
     *
     * @param radio      Valor de la coordenada que representa el radio.
     * @param theta      Valor de la coordenada que representa el &aacute;ngulo &theta; (Theta).
     * @param nDecimales N&uacute;mero de decimales que tendr&aacute;n cada uno de los valores de la coordenada resultante.
     * @return Valores con los que se expresa la coordenada Cartesiana 2D.
     */
    public double[] calcularCoorPoCar2D(double radio, double theta, boolean esRadian, int nDecimales) {
        double[] coor = new double[2];
        CCartesiana cc = CPolar.convertirACartesiana(radio, theta, esRadian);
        coor[0] = BigDecimal.valueOf(cc.getX()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        coor[1] = BigDecimal.valueOf(cc.getY()).setScale(nDecimales, RoundingMode.HALF_UP).doubleValue();
        return coor;
    }
}