package com.corphy.graficador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * <p>Clase que implementa la interfaz {@link ActionListener} para agregar los eventos a los botones y campos de texto
 * del graficador de funciones para que ejecuten alguna acci&oacute;n.</p>
 *
 * @author CorPhy
 * @version 1.0
 */

class Eventos implements ActionListener {
    // Atributos

    /**
     * Clase {@link GraficaFrame} que contiene la interfaz gr&aacute;fica de los controles del graficador de funciones
     * la cual extiende a la clase {@link JFrame}.
     */
    private GraficaFrame gfr;

    /**
     * Clase {@link Derivada} que permite calcular la derivada de una funci&oacute;n.
     */
    private Derivada derive;

    // Constructor

    /**
     * Constructor de la clase que inicializa los objetos de los atributos.
     *
     * @param jf Clase que extiende a la clase {@link JFrame}.
     */
    Eventos(JFrame jf) {
        gfr = (GraficaFrame) jf;
        derive = new Derivada();
    }

    /**
     * Invocado cuando alguna acci&oacute;n ocurre.
     *
     * @param evt Tipo de evento.
     */
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();

        // Actualiza la grafica si se oprime un botón o enter  en un campo de texto
        if (source == gfr.getBtnGraficar() || source == gfr.getTxtFuncion() || source == gfr.getTxtIntervaloA() || source == gfr.getTxtIntervaloB()) {
            gfr.getpanelZonaGrafica().repaint();
        }

        // Deriva la función ingresada por el usuario
        if (source == gfr.getBtnDerivar()) {
            gfr.getResultadoDerivada().setText(derive.derivar(gfr.getTxtFuncion().getText()));
        }

        // Muestra el cuadro de ayuda del graficador de funciones
        if (source == gfr.getBtnInfo()) {
            JDialog dialog = new JDialog();
            dialog.setTitle("Ayuda");
            Ayuda help = new Ayuda();
            dialog.add(help.getContentPane());
            dialog.setModal(true);
            dialog.setSize(help.getSize());
            dialog.setResizable(false);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            dialog.dispose();
        }
    }
}