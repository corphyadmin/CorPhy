package com.corphy.coordenadas;

/**
 * <p>Clase que permite representar una coordenada Esf&eacute;rica.</p>
 * <p>Esta clase permite:</p>
 * <ul>
 * <li>Convertir la coordenada Esf&eacute;rica a Cartesiana 3D.</li>
 * <li>Convertir la coordenada Esf&eacute;rica a Cil&iacute;ndrica.</li>
 * </ul>
 *
 * @author CorPhy
 * @version 1.0
 */
public class CEsferica {
    // Atributos

    /**
     * Valor que representa el radio de la coordenada.
     */
    private double radio;

    /**
     * Valor que representa el &aacute;ngulo θ (Theta) de la coordenada.
     */
    private double anguloTheta;

    /**
     * Valor que representa el &aacute;ngulo φ (Phi) de la coordenada.
     */
    private double anguloPhi;

    // Constructor

    /**
     * Constructor de la clase el cual define a una coordenada Esf&eacute;rica.
     *
     * @param radio       Valor del radio que puede ser entero o decimal con signo positivo. Distancia entre el origen y
     *                    el punto.
     * @param anguloTheta Valor del &aacute;ngulo &theta; (Theta) el cual debe tomar valores positivos de 0&deg; a 360&deg;.
     * @param anguloPhi   Valor del &aacute;ngulo &phi; (Phi) el cual debe tomar valores positivos de 0&deg; a 180&deg;.
     */
    public CEsferica(double radio, double anguloTheta, double anguloPhi) {
        this.radio = radio;
        this.anguloTheta = anguloTheta;
        this.anguloPhi = anguloPhi;
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
     * Obtiene el valor del &aacute;ngulo &theta; (Theta) de la coordenada.
     *
     * @return Valor del &aacute;ngulo &theta; (Theta).
     */
    public double getAnguloTheta() {
        return anguloTheta;
    }

    /**
     * Modifica el valor del &aacute;ngulo &theta; (Theta) del objeto de la coordenada.
     *
     * @param anguloTeta Nuevo valor del &aacute;ngulo &theta; (Theta).
     */
    public void setAnguloTeta(double anguloTeta) {
        this.anguloTheta = anguloTeta;
    }

    /**
     * Obtiene el valor del &aacute;ngulo &phi; (Phi) de la coordenada.
     *
     * @return Valor del &aacute;ngulo &phi; (Phi).
     */
    public double getAnguloPhi() {
        return anguloPhi;
    }

    /**
     * Modifica el valor del &aacute;ngulo &phi; (Phi) del objeto de la coordenada.
     *
     * @param anguloPhi Nuevo valor del &aacute;ngulo &phi; (Phi).
     */
    public void setAnguloPhi(double anguloPhi) {
        this.anguloPhi = anguloPhi;
    }

    /**
     * Permite convertir una coordenada Esf&eacute;rica a Cartesiana 3D.
     *
     * @param radio       Distancia entre el origen y el punto.
     * @param anguloTheta &Aacute;ngulo formado en el plano XY que parte en el eje X. Sus valores van de 0&deg; a 360&deg;.
     * @param anguloPhi   &Aacute;ngulo formado en el plano Z. Este &aacute;ngulo parte del eje Z positivo y va m&aacute;ximo
     *                    hasta el eje Z negativo. Toma valores de 0&deg; a 180&deg;.
     * @return Coordenada Cartesiana 3D.
     */
    public static CCartesiana convertirACartesiana(double radio, double anguloTheta, double anguloPhi, boolean esRadian) {
        double x, y, z;
        if (esRadian) {
            x = radio * Math.sin(anguloPhi) * Math.cos(anguloTheta);
            y = radio * Math.sin(anguloPhi) * Math.sin(anguloTheta);
            z = radio * Math.cos(anguloPhi);
        } else {
            x = radio * Math.sin(Math.toRadians(anguloPhi)) * Math.cos(Math.toRadians(anguloTheta));
            y = radio * Math.sin(Math.toRadians(anguloPhi)) * Math.sin(Math.toRadians(anguloTheta));
            z = radio * Math.cos(Math.toRadians(anguloPhi));
        }
        return new CCartesiana(x, y, z);
    }

    /**
     * Permite convertir una Coordenada Esf&eacute;rica a Cil&iacute;ndrica.
     *
     * @param radio       Distancia entre el origen y el punto.
     * @param anguloTheta &Aacute;ngulo formado en el plano XY que parte en el eje X. Sus valores van de 0&deg; a 360&deg;.
     * @param anguloPhi   &Aacute;ngulo formado en el plano Z. Este &aacute;ngulo parte del eje Z positivo y va m&aacute;ximo
     *                    hasta el eje Z negativo. Toma valores de 0&deg; a 180&deg;.
     * @return Coordenada Cil&iacute;ndrica.
     */
    public static CCilindrica convertirACilindrica(double radio, double anguloTheta, double anguloPhi, boolean esRadian) {
        double radioOut, thetaOut, z;
        if (esRadian) {
            radioOut = radio * Math.sin(anguloPhi);
            thetaOut = anguloTheta;
            z = radio * Math.cos(anguloPhi);
        } else {
            radioOut = radio * Math.sin(Math.toRadians(anguloPhi));
            thetaOut = anguloTheta;
            z = radio * Math.cos(Math.toRadians(anguloPhi));
        }
        return new CCilindrica(radioOut, thetaOut, z);
    }
}