package com.corphy.graficador;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Clase que contiene la interfaz gr&aacute;fica de los controles del graficador de funciones y extiende a la clase
 * {@link JFrame}.</p>
 *
 * @author CorPhy
 * @version 1.0
 */

public class GraficaFrame extends JFrame {
    // Atributos

    // Paneles principales

    /**
     * Panel que contiene todas las zonas que componen la gr&aacute;fica de la funci&oacute;n.
     */
    private JPanel panelZonaGrafica;

    /**
     * Panel que contiene la gr&aacute;fica de la funci&oacute;n.
     */
    private JPanel panelGrafica;

    /**
     * Panel que contiene los controles del graficador
     */
    private JPanel panelGeneral;

    /**
     * Panel que contiene el subcontrol de la funci&oacute;n a graficar.
     */
    private JPanel panelFuncion;

    /**
     * Panel que contiene el subcontrol para derivar la función ingresada por el usuario.
     */
    private JPanel panelMetodo;

    /**
     * Panel que contiene el subcontrol para cambiar el color de la funci&oacute;n graficada y mostrar la ayuda del graficador.
     */
    private JPanel panelOpciones;

    /**
     * Los paneles <strong>panelFuncionA, panelFuncionB y panelFuncionC</strong> son paneles auxiliares del subcontrol
     * Funci&oacute;n.
     */
    private JPanel panelFuncionA;
    private JPanel panelFuncionB;
    private JPanel panelFuncionC;

    /**
     * Los paneles <strong>panelMetodoA y panelMetodoB</strong> son paneles auxiliares del subcontrol Métodos.
     */
    private JPanel panelMetodoA;
    private JPanel panelMetodoB;

    /**
     * Los paneles <strong>panelOpcionesA y panelOpcionesB</strong> son paneles auxiliares del subcontrol Opciones.
     */
    private JPanel panelOpcionesA;
    private JPanel panelOpcionesB;

    /**
     * Campo de texto en el cual se digita la función a graficar y derivar.
     */
    private JTextField txtFuncion;

    /**
     * Campo de texto en el cual se digita el intervalo inicial de la funci&oacute;n en el eje X.
     */
    private JTextField txtIntervaloA;

    /**
     * Campo de texto en el cual se digita el intervalo final de la funci&oacute;n en el eje X.
     */
    private JTextField txtIntervaloB;

    /**
     * Indica que se debe ingresar una funci&oacute;n F(x).
     */
    private JLabel lbEntrada;

    /**
     * Indica que se debe ingresar el intervalo inicial en el eje X.
     */
    private JLabel lbIntervaloA;

    /**
     * Indica que se debe ingresar el intervalo final en el eje X.
     */
    private JLabel lbIntervaloB;

    /**
     * Indica que se puede escojer un color para la función graficada.
     */
    private JLabel lbColorFuncion;

    /**
     * Las labels <strong>lbVacioA y lbVacioB</strong> est&aacute;n vac&iacute;as y son elementos auxiliares o de relleno
     * para el subcontrol Opciones.
     */
    private JLabel lbVacioA;
    private JLabel lbVacioB;

    /**
     * Bot&oacute;n que permite graficar la funci&oacute;n ingresada por el usuario.
     */
    private JButton btnGraficar;

    /**
     * Bot&oacute;n que permite derivar la funci&oacute;n ingresada por el usuario.
     */
    private JButton btnDerivar;

    /**
     * Bot&oacute;n que perite cambiar el color de la funci&oacute;n graficada.
     */
    private JButton btnElegirColor;

    /**
     * Bot&oacute;n que perite mostrar el cuadro de ayuda del graficador de funciones.
     */
    private JButton btnInfo;

    /**
     * TextArea para el resultado de la derivada de la funci&oacute;n ingresada por el usuario.
     */
    private JTextArea resultadoDerivada;

    /**
     * Fuente general aplicada a los elementos del graficador de funciones.
     */
    private Font fuenteGeneral;

    /**
     * Fuente para los t&iacute;tulos de los subcontroles del graficador de funciones.
     */
    private Font fuenteTitledBorder;

    /**
     * Elemento auxiliar para la graficar la funci&oacute;n ingresada por el usuario.
     */
    private JScrollPane scrollPane;

    /**
     * Clase para los eventos de los botones y campos de texto.
     */
    private Eventos event;

    /**
     * Color elegido por el usuario para que sea aplicado a la funci&oacute;n graficada.
     */
    private Color colorFuncion;

    // Constructor

    /**
     * Constructor de la clase que inicializa los componentes de la interfaz gr&aacute;fica del graficador de funciones.
     */
    public GraficaFrame() {
        // Paneles
        panelZonaGrafica = new Grafica(this); //zona grafica
        panelGrafica = new JPanel();
        panelGeneral = new JPanel();
        panelFuncion = new JPanel();
        panelMetodo = new JPanel();
        panelOpciones = new JPanel();
        panelFuncionA = new JPanel();
        panelFuncionB = new JPanel();
        panelFuncionC = new JPanel();
        panelMetodoA = new JPanel();
        panelMetodoB = new JPanel();
        panelOpcionesA = new JPanel();
        panelOpcionesB = new JPanel();

        // Campos de texto
        txtFuncion = new JTextField("(sin(x^2)/1-cos(x))-1");//(x+2)^2-1 //cos(x)
        txtIntervaloA = new JTextField("-2");
        txtIntervaloB = new JTextField();

        // Labels
        lbEntrada = new JLabel("Entrada: F(x) = ");
        lbIntervaloA = new JLabel("X inicial = ");
        lbIntervaloB = new JLabel("X final = ");
        lbColorFuncion = new JLabel("Color de la función:");
        // Labels vacias
        lbVacioA = new JLabel();
        lbVacioB = new JLabel();

        // Buttons
        btnGraficar = new JButton("Graficar");
        btnDerivar = new JButton("Derivar función");
        btnElegirColor = new JButton();
        btnInfo = new JButton("Ayuda");

        // Fuentes
        fuenteGeneral = new Font("Ebrima", Font.PLAIN, 14);
        fuenteTitledBorder = new Font("Dosis", Font.PLAIN, 14);

        resultadoDerivada = new JTextArea();
        resultadoDerivada.setEditable(false);
        resultadoDerivada.setText("Ctrl+c para copiar el resultado.\nPara derivar la función seno se debe escribir como sin(x).");
        resultadoDerivada.setFont(fuenteTitledBorder);

        // Color inicial de la función
        colorFuncion = new Color(0, 102, 153);

        // Elementos auxiliares
        scrollPane = new JScrollPane(panelZonaGrafica);

//------------------------------------------------------------------------------
        // Fuentes de los elementos
        txtFuncion.setFont(fuenteGeneral);
        txtIntervaloA.setFont(fuenteGeneral);
        txtIntervaloB.setFont(fuenteGeneral);

        lbEntrada.setFont(fuenteGeneral);
        lbIntervaloA.setFont(fuenteGeneral);
        lbIntervaloB.setFont(fuenteGeneral);
        lbColorFuncion.setFont(fuenteGeneral);

        btnGraficar.setFont(fuenteGeneral);
        btnDerivar.setFont(fuenteGeneral);
        btnElegirColor.setFont(fuenteGeneral);
        btnInfo.setFont(fuenteGeneral);

        // Cursor de los JButtones
        btnGraficar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDerivar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnElegirColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Borde y fondo del botón del JFileChooser que representa el color de la funcion del graficador
        Border colorBorde = BorderFactory.createLineBorder(new Color(0, 102, 153));
        btnElegirColor.setBackground(new Color(0, 102, 153));
        btnElegirColor.setBorder(colorBorde);

        // Agregar tooltips a los JButtones
        btnGraficar.setToolTipText("Graficar función");
        btnDerivar.setToolTipText("Derivar función");
        btnElegirColor.setToolTipText("Elige un color");
        btnInfo.setToolTipText("Ayuda!");

        //Quitar focus de los JButtones
        btnGraficar.setFocusable(false);
        btnDerivar.setFocusable(false);
        btnElegirColor.setFocusable(false);
        btnInfo.setFocusable(false);

//------------------------------------------------------------------------------
        panelGrafica.setLayout(new BorderLayout());
        panelGrafica.add(scrollPane, BorderLayout.CENTER);//panelZonaGrafica

        // Se agregan los elementos a los paneles hijos del panel función
        panelFuncionA.setLayout(new GridLayout(1, 2));
        panelFuncionB.setLayout(new GridLayout(1, 4));
        panelFuncion.setLayout(new FlowLayout());
        panelFuncionA.add(lbEntrada);
        panelFuncionA.add(txtFuncion);
        panelFuncionB.add(lbIntervaloA);
        panelFuncionB.add(txtIntervaloA);
        panelFuncionB.add(lbIntervaloB);
        panelFuncionB.add(txtIntervaloB);
        panelFuncionC.add(btnGraficar);
        //----------------------------------------------------------------------

        // Se agregan los elementos a los paneles hijos del panel métodos
        panelMetodoA.setLayout(new FlowLayout());
        panelMetodoB.setLayout(new GridLayout(1, 1));
        panelMetodoA.add(btnDerivar);
        panelMetodoB.add(resultadoDerivada);
        //----------------------------------------------------------------------

        // Se agregan los elementos a los paneles hijos del panel opciones
        panelOpcionesA.setLayout(new GridLayout(1, 2));
        panelOpcionesB.setLayout(new GridLayout(1, 1));
        panelOpcionesA.add(lbColorFuncion);
        panelOpcionesA.add(btnElegirColor);
        panelOpcionesB.add(btnInfo);
        panelOpcionesB.add(lbVacioA);
        panelOpcionesB.add(lbVacioB);

        //----------------------------------------------------------------------
        // Panel General
        panelGeneral.setLayout(new GridLayout(1, 3));

        // Se agregan los paneles hijos al panel funcion
        panelFuncion.setLayout(new GridLayout(3, 1));
        panelFuncion.add(panelFuncionA);
        panelFuncion.add(panelFuncionB);
        panelFuncion.add(panelFuncionC);

        // Se agregan los paneles hijos al panel metodos
        panelMetodo.setLayout(new GridLayout(2, 1));
        panelMetodo.add(panelMetodoA);
        panelMetodo.add(panelMetodoB);

        // Se agregan los paneles hijos al panel opciones
        panelOpciones.setLayout(new GridLayout(2, 1));
        panelOpciones.add(panelOpcionesA);
        panelOpciones.add(panelOpcionesB);

        //----------------------------------------------------------------------
        // Cuadro que agrupa los paneles y les da su respectivo nombre
        TitledBorder rotulo;

        rotulo = BorderFactory.createTitledBorder(" Función ");
        rotulo.setTitleColor(new Color(0, 0, 128));
        rotulo.setTitleFont(fuenteTitledBorder);
        panelFuncion.setBorder(rotulo);

        rotulo = BorderFactory.createTitledBorder(" Métodos ");
        rotulo.setTitleColor(new Color(0, 0, 128));
        rotulo.setTitleFont(fuenteTitledBorder);
        panelMetodo.setBorder(rotulo);

        rotulo = BorderFactory.createTitledBorder(" Opciones ");
        rotulo.setTitleColor(new Color(0, 0, 128));
        rotulo.setTitleFont(fuenteTitledBorder);
        panelOpciones.setBorder(rotulo);
        //----------------------------------------------------------------------

        // Eventos a los JButtones y campos de texto
        event = new Eventos(this);
        btnGraficar.addActionListener(event);
        btnDerivar.addActionListener(event);
        btnInfo.addActionListener(event);
        txtFuncion.addActionListener(event);
        txtIntervaloA.addActionListener(event);
        txtIntervaloB.addActionListener(event);
        btnElegirColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Elige un color", new Color(0, 102, 153));
                btnElegirColor.setBackground(color);
                Border colorBorde = BorderFactory.createLineBorder(color);
                btnElegirColor.setBorder(colorBorde);
                colorFuncion = color;
                panelZonaGrafica.repaint();
            }
        });
        //----------------------------------------------------------------------

        panelGrafica.setPreferredSize(new Dimension(1300, 485));
        panelGeneral.add(panelFuncion);
        panelGeneral.add(panelMetodo);
        panelGeneral.add(panelOpciones);

//        addComponentListener(new ComponentAdapter() {
//            public void componentMoved(ComponentEvent ce) {
//                System.out.println("a = " + getWidth() + ", h = " + getHeight());
//                 alto = getWidth();
//                ancho = getHeight();
//                panelZonaGrafica.repaint();
//            }
//        });
        this.setLayout(new BorderLayout());
        this.add("North", panelGrafica);
        this.add("South", panelGeneral);
        this.setSize(1300, 650);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/com/corphy/img/logo.png"));
        java.awt.Image image = imageIcon.getImage();
        this.setIconImage(image);
        this.setTitle("CorPhy - Graficador de funciones");
        this.setResizable(false);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    /**
     * Obtiene el campo de texto del subcontrol Funci&oacute;n.
     *
     * @return Campo de texto en el cual se digita la función a graficar.
     */
    JTextField getTxtFuncion() {
        return txtFuncion;
    }

    /**
     * Obtiene el campo de texto del subcontrol Funci&oacute;n.
     *
     * @return Campo de texto en el cual se digita el intervalo inical de la funci&oacute;n en el eje X.
     */
    JTextField getTxtIntervaloA() {
        return txtIntervaloA;
    }

    /**
     * Obtiene el campo de texto del subcontrol Funci&oacute;n.
     *
     * @return Campo de texto en el cual se digita el intervalo final de la funci&oacute;n en el eje X.
     */
    JTextField getTxtIntervaloB() {
        return txtIntervaloB;
    }

    /**
     * Obtiene el bot&oacute;n del subcontrol Funci&oacute;n.
     *
     * @return Bot&oacute;n que permite graficar la funci&oacute;n ingresada por el usuario.
     */
    JButton getBtnGraficar() {
        return btnGraficar;
    }

    /**
     * Obtiene la fuente general aplicada a los elementos del graficador de funciones.
     *
     * @return Fuente general.
     */
    Font getFuenteGeneral() {
        return fuenteGeneral;
    }

    /**
     * Obtiene el panel global de la zona de graficaci&oacute;n.
     *
     * @return Zona global de graficaci&oacute;n.
     */
    JPanel getpanelZonaGrafica() {
        return panelZonaGrafica;
    }

    /**
     * Obtiene el bot&oacute;n del subcontrol M&eacute;todos.
     *
     * @return Bot&oacute;n que pemite calcular la derivada de la funci&oacute;n ingresada por el usaurio.
     */
    JButton getBtnDerivar() {
        return btnDerivar;
    }

    /**
     * Obtiene el color elegido por el usuario para que sea aplicado a la funci&oacute;n graficada.
     *
     * @return Color elegido por el usuario.
     */
    Color getColorFuncion() {
        return colorFuncion;
    }

    /**
     * Obtiene el textArea del subcontrol M&eacute;todos.
     *
     * @return TextArea con el resultado de la derivada de la funci&oacute;n ingresada por el usuario.
     */
    JTextArea getResultadoDerivada() {
        return resultadoDerivada;
    }

    /**
     * Obtiene el bot&oacute;n del subcontrol Opciones.
     *
     * @return Bot&oacute;n que permite mostrar el cuadro de ayuda del graficador de funciones.
     */
    JButton getBtnInfo() {
        return btnInfo;
    }
}
