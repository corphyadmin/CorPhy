package com.corphy.graficador;

import org.nfunk.jep.JEP;
import org.nfunk.jep.type.Complex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

/**
 * Clase que extiende a la clase {@link JPanel} para mostrar el plano cartesiano que dibuja las funciones del graficador
 * e implementa las interfaces {@link MouseListener}, {@link MouseMotionListener} y {@link MouseWheelListener} para agregarle
 * los eventos.
 *
 * @author CorPhy
 * @version 1.0
 */

class Grafica extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private GraficaFrame gfr;
    private int offsetX;
    private int offsetY;
    private boolean dragging;

    // Variables para evaluar las funciones
    private JEP miEvaluador;

    // Dimesiones de la zona de graficación
    private int graficaAlto;
    private int graficaAncho;

    // Origen
    private int X0;
    private int Y0;
    private int escalaX;
    private int escalaY;
    private int aumento;

    // Variables para el grosor de las líneas
    private BasicStroke grosor = new BasicStroke(1.5f);

    /**
     * Constructor de la clase que inicializa los elemntos que permiten graficar una funci&oacute;n ingresada por el usuario.
     * @param jf Clase que extiende a la clase {@link JFrame}.
     */

    Grafica(JFrame jf) {
        gfr = (GraficaFrame) jf;

        setBackground(Color.WHITE);
        offsetX = X0;
        offsetY = Y0;

        graficaAncho = 1300;
        graficaAlto = 485;

        //Evaluador de funciones
        miEvaluador = new JEP();
        miEvaluador.addStandardFunctions();  // Agrega las funciones matematicas comunes
        miEvaluador.addStandardConstants();
        miEvaluador.addComplex();
        miEvaluador.addFunction("sen", new org.nfunk.jep.function.Sine()); // Habilitar 'sen'
        miEvaluador.addVariable("x", 0);
        miEvaluador.setImplicitMul(true); // Permite 2x en vez de 2*x

        escalaX = 30;
        escalaY = 30;
        X0 = graficaAncho / 2;
        Y0 = graficaAlto / 2;
        aumento = 7;

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        if (dragging) {
            return;
        }
        int x = evt.getX();  // Clic inicial
        int y = evt.getY();
        offsetX = x - X0;
        offsetY = y - Y0;
        dragging = true;
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        dragging = false;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        if (!dragging) {
            return;
        }
        int x = evt.getX(); // Posición del mouse
        int y = evt.getY();
        X0 = x - offsetX; // Mover origen de la grafica
        Y0 = y - offsetY;
        repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent evt) {
        int zoom = evt.getWheelRotation();
        escalaY += -zoom;
        escalaX += -zoom;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graficar(g, X0, Y0);
    }

    /**
     * Método que pinta toda la grafica de la función ingresada por el usuario.
     */
    private void Graficar(Graphics ap, int xg, int yg) {
        setBackground(new Color(238, 238, 238)); // Color de fondo de la grafica
        int xi, yi, xi1, yi1;
        int cxmin, cxmax, cymin, cymax;
        double valxi, valxi1, valyi, valyi1;

        // Convertimos el objeto ap en un objeto Graphics2D para usar los métodos Java2D
        Graphics2D g = (Graphics2D) ap;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // g.setFont(ft10);
        g.setPaint(Color.BLACK);// Color ejes
        // Pintamos el eje X y el eje Y
        g.draw(new Line2D.Double(xg, 10, xg, graficaAlto - 10)); //EJE Y
        g.draw(new Line2D.Double(10, yg, graficaAncho - 10, yg));//EJE X

        double xmin = -1.0 * xg / escalaX;
        double xmax = (1.0 * (graficaAncho - xg) / escalaX);
        cxmin = (int) Math.round(xmin); // Pantalla
        cxmax = (int) Math.round(xmax); // Pantalla

        int intervaloA;
        if (gfr.getTxtIntervaloA().getText().equals("")) {
            intervaloA = cxmin;
        } else {
            intervaloA = Integer.parseInt(gfr.getTxtIntervaloA().getText());
        }

        int intervaloB;
        if (gfr.getTxtIntervaloB().getText().equals("")) {
            intervaloB = cxmax;
        } else {
            intervaloB = Integer.parseInt(gfr.getTxtIntervaloB().getText());
        }

        int puntosInt = 99;
        // Intervalos para pintar función
        cymin = (int) Math.round(1.0 * (yg - graficaAlto) / escalaY);
        cymax = (int) Math.round(1.0 * yg / escalaY);

        //numPuntos = graficaAncho; //num pixels
        g.setFont(gfr.getFuenteGeneral());

        // Pintamos todos los ejes para formar la cuadricula
        if (escalaX > 5) {
            for (int i = cxmin; i <= cxmax; i++) { // Ejes paralelos al eje Y
                g.setPaint(new Color(153, 153, 153)); // Color de la cuadricula
                g.draw(new Line2D.Double(xg + i * escalaX, yg - 2, xg + i * escalaX, yg + 2));
                if ((xg + i * escalaX) != xg) {
                    g.draw(new Line2D.Double(xg + i * escalaX, 10, xg + i * escalaX, graficaAlto - 10));
                }

                if (i > 0) {
                    g.setPaint(Color.BLACK);// Color de los números positivos
                    g.drawString("" + i, xg + i * escalaX - aumento, yg + 12);
                }
                if (i < 0) {
                    g.setPaint(Color.BLACK);// Color de los números negativos
                    g.drawString("" + i, xg + i * escalaX - 8, yg + 12);
                }
            }
        }

        if (escalaY > 5) {
            for (int i = cymin + 1; i < cymax; i++) { // Ejes paralelos al eje X
                g.setPaint(new Color(153, 153, 153)); // Color de la cuadricula
                g.draw(new Line2D.Double(xg - 2, yg - i * escalaY, xg + 2, yg - i * escalaY));
                if ((yg - i * escalaY) != yg) {
                    g.draw(new Line2D.Double(10, yg - i * escalaY, graficaAncho - 10, yg - i * escalaY));
                }
                if (i > 0) {
                    g.setPaint(Color.BLACK); // Color de los números positivos
                    g.drawString("" + i, xg - 12, yg - i * escalaY + 8);
                }
                if (i < 0) {
                    g.setPaint(Color.BLACK); // Color de los números negativos
                    g.drawString("" + i, xg - 14, yg - i * escalaY + 8);
                }
            }
        }

        //g.setPaint(new Color(0, 102, 153)); // Color de la función
        g.setPaint(gfr.getColorFuncion());
        g.setStroke(grosor);

        miEvaluador.parseExpression(gfr.getTxtFuncion().getText());
        // Si hay error de sintaxis en la función
        boolean errorEnExpresion = miEvaluador.hasError();

        if (!errorEnExpresion) {
            gfr.getTxtFuncion().setForeground(Color.black);

            // Ciclo que pinta la función ingresada por el usuario
            for (int i = (xg + intervaloA * escalaX); i < (xg + intervaloB * escalaY) - 1; i++)//numPuntos
            {
                valxi = xmin + i * 1.0 / escalaX;
                valxi1 = xmin + (i + 1) * 1.0 / escalaX;
                miEvaluador.addVariable("x", valxi);
                valyi = miEvaluador.getValue();
                miEvaluador.addVariable("x", valxi1);
                valyi1 = miEvaluador.getValue();
                xi = (int) Math.round(escalaX * (valxi));
                yi = (int) Math.round(escalaY * valyi);
                xi1 = (int) Math.round(escalaX * (valxi1));
                yi1 = (int) Math.round(escalaY * valyi1);

                // Control de puntos de la función
                if (i % (100 - puntosInt) == 0) {
                    Complex valC = miEvaluador.getComplexValue();
                    //System.out.println("valc "+valC);
                    double imgx = Math.abs(valC.im());
                    if (dist(valxi, valyi, valxi1, valyi1) < 1000 && imgx == 0) {
                        g.draw(new Line2D.Double(xg + xi, yg - yi, xg + xi1, yg - yi1));
                    }
                }
            }
        } else {
            gfr.getTxtFuncion().setForeground(Color.red);
        }
    }

    /**
     * Calcula el n&uacute;mero de puntos a dibujar para la funci&oacute;n ingresada por el usuario.
     *
     * @return N&uacute;mero decimal que representa los puntos a dibujar.
     */
    private double dist(double xA, double yA, double xB, double yB) {
        return (xA - xB) * (xA - xB) + (yA - yB) * (yA - yB);
    }

    // Métodos de las interfaces que no se usan
    @Override
    public void mouseMoved(MouseEvent evt) {
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
    }

    @Override
    public void mouseEntered(MouseEvent evt) {
    }

    @Override
    public void mouseExited(MouseEvent evt) {
    }
}
