package com.corphy.graficador;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Clase que contiene la información de ayuda del graficador de funciones y extiende a la clase {@link JFrame}.</p>
 *
 * @author CorPhy
 * @version 1.0
 */
class Ayuda extends JFrame {
    // Atributos

    /**
     * Panel que contiene la agrupaci&oacute;n de los objetos que componen el cuadro de ayuda.
     */
    private JPanel panelContenedor;

    /**
     * Label o etiqueta que contiene el t&iacute;tulo del cuadro de ayuda.
     */
    private JLabel lbTitulo;

    /**
     * &Aacute;rea de texto que contiene toda la informaci&oacute;n del cuadro de ayuda.
     */
    private JTextArea areaTexto;

    /**
     * Barra de desplazamiento para el &aacute;rea de texto que contiene la informaci&oacute;n de la ayuda.
     */
    private JScrollPane scroll;

    /**
     * Estilo de fuente para el &aacute;rea de texto del cuadro de ayuda.
     */
    private Font fuenteGeneral;

    /**
     * Estilo de fuente para el t&iacute;tulo del cuadro de ayuda.
     */
    private Font funeteTitulo;

    // Constructor

    /**
     * Constructor de la clase que inicializa los atributos para crear el cuadro de ayuda.
     */
    Ayuda() {
        setSize(600, 450);
        panelContenedor = new JPanel();
        areaTexto = new JTextArea();
        scroll = new JScrollPane(areaTexto);
        lbTitulo = new JLabel("Graficación de funciones, derivadas y color de línea");
        fuenteGeneral = new Font("Ebrima", Font.PLAIN, 16);
        funeteTitulo = new Font("Dosis", Font.BOLD, 18);
        panelContenedor.setLayout(null);
        setContentPane(panelContenedor);

        // Agregar el label al panel
        lbTitulo.setBounds(65, 25, 480, 23);
        lbTitulo.setFont(funeteTitulo);
        panelContenedor.add(lbTitulo);

        // Configuramos el textArea
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setFont(fuenteGeneral);
        areaTexto.setEditable(false);
        areaTexto.setForeground(Color.BLACK);
        areaTexto.setBounds(10, 70, 575, 320);

        areaTexto.append("• Respecto a la función\n");
        areaTexto.append("La función solo acepta como variable a la letra X.\n "
                + "En lo posible, es recomendable usar paréntesis que protejan las expresiones. Todos los valores ingresados después de un exponente\n"
                + "afectaran a la expresión, pero no al exponente. Ejemplo: (x^2 + 3) son dos términos, el primer x^2 y el segundo 3, donde 3 afecta a \n"
                + "toda la función, pero no al exponente, por lo que no queda como x^5. Si se desea agregar una expresión como exponente se debe proteger \n"
                + "con paréntesis.\n\n");

        areaTexto.append("Tipo de funciones permitidas:\n");
        areaTexto.append("  • potencial (x^3, x^(1/2))\n"
                + "  • polinómica (x^2+1)\n"
                + "  • trigonométrica (solo las principales: sin(x), cos(x), tan(x))\n"
                + "  • logarítmicas (ln(x) o log(x^2 + 2)\n"
                + "  • exponencial (e^x, 2^x)\n"
                + "  • racional ((5x-1)/x^2)\n\n");

        areaTexto.append("Los campos intervalo A y B sirven para definir el intervalo a graficar respeto a X.\n"
                + "Para graficar la función con o sin los intervalos se da clic en el botón graficar o se presiona la tecla enter.\n\n");
        areaTexto.append("Respecto a la derivada\n");
        areaTexto.append("Para derivar una función se debe ingresar la función en el campo entrada y luego dar clic en el botón derivar.\n"
                + " El resultado se mostrará debajo del botón.\n\n");
        areaTexto.append("Respecto a las opciones\n");
        areaTexto.append("El campo \u201ccolor de la funci\u00f3n\u201d sirve para cambiar el color de la gr\u00e1fica. Cuando se da clic en el color actual\n "
                + "(por defecto azul) aparecerá un selector de colores. Solo se debe escoger un color y dar aceptar.\n\n");
        areaTexto.append("Desplazamiento y manipulación de la grafica\n");
        areaTexto.append("Para mover la gráfica hacia el lado deseado, se debe seleccionar (mantener clic derecho sobre la gráfica) y"
                + "\n mover el mouse hacia donde se desee. Si tiene un pc portátil es semejante.\n");
        areaTexto.append("Para realizar un zoom, si se tiene ratón solo basta como mover la rueda de este hacia arriba o abajo sobre la gráfica.\n");
        areaTexto.append("Si se tiene un pc portátil con touchpad puede que se pueda o no realizar Zoom. Si tu touchpad permite hacer gestos con los \n"
                + "dedos, entonces, hacer con dos dedos el gesto de ampliación (como se amplía una foto en un teléfono inteligente) o juntar dos dedos\n"
                + " de la mano y hacer el gesto de deslizarlos hacia arriba o abajo según se desee el zoom. Si no funciona, debes ponerle un mouse externo.");
        areaTexto.setCaretPosition(0);

        // Agregar el scroll al panel
        scroll.setBounds(10, 70, 575, 320);
        panelContenedor.add(scroll);
    }
}
