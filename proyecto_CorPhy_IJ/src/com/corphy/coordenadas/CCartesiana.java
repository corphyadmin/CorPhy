package com.corphy.coordenadas;

/**
 * <p>Clase que representa una coordenada Cartesiana en dos o tres dimensiones.</p>
 * <p>Esta coordenada puede ser convertida a polar cuando es 2D y a esf&eacute;rica o cil&iacute;ndrica cuando es 3D.</p>
 *
 * @author CorPhy
 * @version 1.0
 */

public class CCartesiana {
    // Atributos

    /**
     * Valor de la coordenada en el eje X.
     */
    private double x;

    /**
     * Valor de la coordenada en el eje Y.
     */
    private double y;

    /**
     * Valor de la coordenada en el eje Z.
     */
    private double z;

    // Constructor

    /**
     * Constructor de la clase para coordenadas Cartesianas 2D.
     *
     * @param x Valor en el eje X que puede ser entero o decimal, negativo o positivo.
     * @param y Valor en el eje Y que puede ser entero o decimal, negativo o positivo.
     */
    public CCartesiana(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Constructor

    /**
     * Contructor de la clase para coordenadas Cartesianas 3D.
     *
     * @param x Valor en el eje X.
     * @param y Valor en el eje Y.
     * @param z Valor en el eje Z, define la altura.
     */
    public CCartesiana(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Obtiene el valor de la coordenada en el eje X.
     *
     * @return Valor de la componente X.
     */
    public double getX() {
        return x;
    }

    /**
     * Modifica la componente <strong>X</strong> del objeto de la cordenada.
     *
     * @param x Nuevo valor de la componente.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Obtiene el valor de la coordenada en el eje Y.
     *
     * @return Valor de la componente Y.
     */
    public double getY() {
        return y;
    }

    /**
     * Modifica la componente <strong>Y</strong> del objeto de la cordenada.
     *
     * @param y Nuevo valor de la componente.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Obtiene el valor de la coordenada en el eje Z.
     *
     * @return Valor de la componente Z.
     */
    public double getZ() {
        return z;
    }

    /**
     * Modifica la componente <strong>Z</strong> del objeto de la cordenada.
     *
     * @param z Nuevo valor de la componente.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Realiza la conversi&oacute;n de coordenadas cartesianas 2D a coordenadas Polares.
     *
     * @param x Valor de la componente en el eje X.
     * @param y Valor de la componente en el eje Y.
     * @return Coordenada Polar.
     */
    public static CPolar convertirAPolar(double x, double y, boolean esRadian) {
        double radio, angulo;
        radio = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (esRadian) {
            angulo = Math.atan((y / x));
            angulo = angle(x, y, angulo, true);
        } else {
            angulo = Math.toDegrees(Math.atan((y / x)));
            angulo = angle(x, y, angulo, false);
        }
        return new CPolar(radio, angulo);
    }

    /**
     * Realiza la conversi&oacute;n de coordenadas cartesianas 3D a coordenadas Cil&iacute;ndricas.
     *
     * @param x Valor de la componente en el eje X.
     * @param y Valor de la componente en el eje Y.
     * @param z Valor de la componente en el eje Z.
     * @return Coordenada Cil&iacute;ndrica.
     */
    public static CCilindrica convertirACilindrica(double x, double y, double z, boolean esRadian) {
        double radio, angulo;
        radio = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (esRadian) {
            angulo = Math.atan(y / x);
            angulo = angle(x, y, angulo, true);
        } else {
            angulo = Math.toDegrees(Math.atan(y / x));
            angulo = angle(x, y, angulo, false);
        }
        return new CCilindrica(radio, angulo, z);
    }

    /**
     * Realiza la conversi&oacute;n de coordenadas cartesianas 3D a coordenadas Esf&eacute;ricas.
     *
     * @param x Valor de la componente en el eje X.
     * @param y Valor de la componente en el eje Y.
     * @param z Valor de la componente en el eje Z.
     * @return Coordenada Esf&eacute;rica.
     */
    public static CEsferica convertirAEsferica(double x, double y, double z, boolean esRadian) {
        double radio, anguloTe, anguloPhy;
        radio = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        if (esRadian) {
            anguloTe = Math.atan(y / x);
            anguloPhy = Math.acos(z / radio);
            anguloTe = angle(x, y, anguloTe, true);
        } else {
            anguloTe = Math.toDegrees(Math.atan(y / x));
            anguloPhy = Math.toDegrees(Math.acos(z / radio));
            anguloTe = angle(x, y, anguloTe, false);
        }
        return new CEsferica(radio, anguloTe, anguloPhy);
    }

    private static double angle(double x, double y, double angulo, boolean esRadian) {
        if (esRadian) { // esRadian
            if (!isPositive(x) || !isPositive(y)) {
                if (!isPositive(x) && !isPositive(y) || !isPositive(x) && isPositive(y)) {
                    angulo = Math.PI + angulo;
                } else {
                    angulo = (2 * Math.PI) + angulo;
                }
            }
        } else { // Grados
            if (!isPositive(x) || !isPositive(y)) {
                if (!isPositive(x) && !isPositive(y) || !isPositive(x) && isPositive(y)) {
                    angulo = 180 + angulo;
                } else {
                    angulo = 360 + angulo;
                }
            }
        }
        return angulo;
    }

    /**
     * Verifica si un n&uacute;mero entero o decimal es positivo.
     *
     * @param num N&uacute;mero que ser&aacute; verificado por una condici&oacute;n.
     * @return Falso o verdadero dependiendo si el n&uacute;mero ingresado cumple o no con la condici&oacute;n.
     */
    private static boolean isPositive(double num) {
        return num >= 0;
    }

}