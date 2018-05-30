package com.corphy.coordenadas;

/**
 * <p>Tipo de coordenada que tiene dos parametros, radio y &aacute;ngulo &theta; (Theta).</p>
 * <p>Esta clase permite la conversi&oacute;n de coordenadas Polares a Cartesianas 2D.</p>
 *
 * @author CorPhy
 * @version 1.0
 */
public class CPolar {
    // Atributos

    /**
     * Valor que representa el radio de la coordenada.
     */
    private double radio;
    /**
     * Valor que representa el ángulo &theta; (Theta) de la coordenada.
     */
    private double angulo;

    // Constructor

    /**
     * Constructor de la clase que permite la definir una coordenada Polar.
     *
     * @param radio  Valor del radio que puede ser entero o decimal con signo positivo.
     * @param angulo Valor del &aacute;ngulo &theta; (Theta) el cual debe tomar valores positivos de 0&deg; a 360&deg;.
     */
    public CPolar(double radio, double angulo) {
        this.radio = radio;
        this.angulo = angulo;
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
     * @return Valor del &aacute;ngulo.
     */
    public double getAngulo() {
        return angulo;
    }

    /**
     * Modifica el valor del &aacute;ngulo &theta; (Theta) del objeto de la coordenada.
     *
     * @param angulo Nuevo valor del &aacute;ngulo.
     */
    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    /**
     * Permite la conversi&oacute;n de coordenadas Polares a Cartesianas 2D.
     *
     * @param radio  Valor del radio de la coordenada.
     * @param angulo &Aacute;ngulo &theta; (Theta) de la coordenada.
     * @return Coordenada Cartesiana 2D.
     */
    public static CCartesiana convertirACartesiana(double radio, double angulo, boolean esRadian) {
        double x, y;
        if (esRadian){
            x = radio * Math.cos(angulo);
            y = radio * Math.sin(angulo);
        }else{
            x = radio * Math.cos(Math.toRadians(angulo));
            y = radio * Math.sin(Math.toRadians(angulo));
        }
        return new CCartesiana(x, y);
    }
}