package com.corphy.coordenadas;

/**
 * <p>Clase que representa a una coordenada Cil&iacute;ndrica, la cual se caracteriza por tener sus proyecciones semejantes
 * a un cilindro. Esta tiene un radio, un &aacute;ngulo de apertura en el plano XY y una altura o profundidad (eje Z).</p>
 *
 * <ul>
 * <li>Convertir la coordenada 	Cil&iacute;ndrica a coordenada Cartesiana 3D.</li>
 * <li>Convertir la coordenada 	Cil&iacute;ndrica a coordenada Esf&eacute;rica.</li>
 * </ul>
 *
 * @author CorPhy
 * @version 1.0
 */
public class CCilindrica {
    // Atributos

    /**
     * Valor que representa el radio de la coordenada.
     */
    private double radio;

    /**
     * Valor que representa el &aacute;ngulo de la coordenada.
     */
    private double angulo;

    /**
     * Valor que representa la altura o profundidad de la coordenada.
     */
    private double z;

    // Constructor

    /**
     * Constructor de la clase para definir una coordenada Cil&iacute;ndrica.
     *
     * @param radio  Valor del radio que puede ser entero o decimal positivo.
     * @param angulo Valor del &aacute;ngulo el cual debe tomar valores positivos de 0&deg; a 360&deg;.
     * @param z      Valor de la altura que puede ser entero o decimal, negativo o positivo.
     */
    public CCilindrica(double radio, double angulo, double z) {
        this.radio = radio;
        this.angulo = angulo;
        this.z = z;
    }

    /**
     * Obtiene el valor del radio de la coordenada.
     *
     * @return Valor del radio.
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Modifica el valor del radio del objeto de la coordenada.
     *
     * @param radio Nuevo valor del radio.
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }

    /**
     * Obtiene el valor del &aacute;ngulo de la coordenada.
     *
     * @return Valor del &aacute;ngulo.
     */
    public double getAngulo() {
        return angulo;
    }

    /**
     * Modifica el valor del &aacute;ngulo del objeto de la coordenada.
     *
     * @param angulo Nuevo valor del &aacute;ngulo.
     */
    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    /**
     * Obtiene el valor de la altura o profundidad de la coordenada.
     *
     * @return Valor de la altura o profundidad.
     */
    public double getZ() {
        return z;
    }

    /**
     * Modifica el valor de la altura o profundidad del objeto de la coordenada.
     *
     * @param z Nuevo valor de la altura o profundidad.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Convierte una coordenada Cil&iacute;ndrica a una coordenada Cartesiana 3D.
     *
     * @param radio Distancia desde el origen hasta un punto en el eje X.
     * @param theta &Aacute;ngulo de apertura en el plano XY desde el eje X hasta m&aacute;ximo 360&deg;, donde se vuelve
     *              a la posici&oacute;n inicial.
     * @param z     Distancia movida en el eje Z, es decir la altura o profundidad.
     * @return Coordenada Cartesiana 3D.
     */
    public static CCartesiana convertirACartesiana(double radio, double theta, double z, boolean esRadian) {
        double x, y;
        if (esRadian) {
            x = radio * Math.cos(theta);
            y = radio * Math.sin(theta);
        } else {
            x = radio * Math.cos(Math.toRadians(theta));
            y = radio * Math.sin(Math.toRadians(theta));
        }
        return new CCartesiana(x, y, z);
    }

    /**
     * Convierte una coordenada Cil&iacute;ndrica a Esf&eacute;rica.
     *
     * @param radio Distancia desde el origen hasta un punto en el eje X.
     * @param theta &Aacute;ngulo de apertura en el plano XY desde el eje X hasta m&aacute;ximo 360&deg;, donde se vuelve
     *              a la posici&oacute;n inicial.
     * @param z     Distancia movida en el eje Z, es decir la altura o profundidad.
     * @return Coordenada Esf&eacute;rica.
     */

    public static CEsferica convertirAEsferica(double radio, double theta, double z, boolean esRadian) {
        double radioOut, phi;
        radioOut = Math.sqrt(Math.pow(radio, 2) + Math.pow(z, 2));

        if (esRadian) {
            if (z != 0) {
                phi = Math.atan(radio / z);
                if (!isPositive(phi)) {
                    phi = Math.PI + phi;
                }
            } else {
                phi = Math.PI/2;
            }
        } else {
            if (z != 0) {
                phi = Math.toDegrees(Math.atan(radio / z));
                if (!isPositive(phi)) {
                    phi = 180 + phi;
                }
            }else{
                phi = 90;
            }
        }
        return new CEsferica(radioOut, theta, phi);
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